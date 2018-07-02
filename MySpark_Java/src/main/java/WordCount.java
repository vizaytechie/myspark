import org.apache.commons.collections.Transformer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class WordCount {
    private static final Pattern SPACE = Pattern.compile(",");
    public static void main(String[] args) throws IOException {

        SparkConf sparkConf = new SparkConf()
            .setAppName("Example Spark App")
            .setMaster("local[*]");  // Delete this line when submitting to a cluster
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = sparkContext.textFile("./src/main/java/WordCount.txt");


        JavaRDD<String> words = lines.flatMap(s -> {
            System.out.println("==== SPACE: ===== "+Arrays.toString(SPACE.split(s)));
            System.out.println("=== AS List ==="+Arrays.asList(SPACE.split(s)));

            return Arrays.asList(SPACE.split(s));
        });
        System.out.println("=== Words: ==="+words.collect());

        JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));
        System.out.println("==== Ones: ==="+ones.collect());


        JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

        System.out.println("=== Counts: ===="+counts.collect());

        List<Tuple2<String, Integer>> output = counts.collect();
        for (Tuple2<?,?> tuple : output) {
          System.out.println(tuple._1() + ": " + tuple._2());
        }
        sparkContext.stop();





        }
}
