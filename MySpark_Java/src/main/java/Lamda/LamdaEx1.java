package Lamda;

interface printWOLamda{
    public void printHello();
    public void printName(String name);
}
interface printWithLamdaWithParam{
    public void printName(String name);
}
interface printWithLamdaWOParam{
    public void printHello();
}
public class LamdaEx1 {
    public static void main(String args[]){


        printWOLamda printRef1 = new printWOLamda(){
            public void printHello(){
                System.out.print("Printing Hello \n");
            }
            public void printName(String name){
                System.out.print("Printing Name: "+name+"\n");
            }
        };

        printWithLamdaWithParam printRef2 = (name)->{
                                        System.out.print("Printing Name: "+name+"\n");
                                };

        printWithLamdaWOParam printRef3 = ()->{System.out.println("Printing Hello");};

        printRef1.printHello();
        printRef1.printName("Vijay");

        printRef2.printName("Vizay");

        printRef3.printHello();
    }
}
