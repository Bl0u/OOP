package OOPStudying.Classes;

public class Person {
    public  void whoAmI(){
        System.out.println("I'm a human");
    }

    public static void displayName(String first){
        System.out.println("first name: " + first);
    }
    public static void displayName(String first, String last){
        System.out.println("first name: " + first + "\nlast name: " + last + " from Person");
    }

    void test(){
        System.out.println("I'm Test from Person");
    }
}
