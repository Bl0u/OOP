package OOPStudying.Classes;

public class Customer extends Person {
        public void whoAmI(){
            System.out.println("I'm a Customer");
        }
        public void display(){
            System.out.println("I'm private");
        }
    public static void displayName(String first, String last) {
            System.out.println("first name: " + first + "\nlast name: " + last + " from Customer");
    }
        void test(){

            System.out.println("I'm Test from Customer");
        }
}
