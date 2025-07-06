package OOPStudying.Interfaces;

public interface Test {
    int MAX_CUSTOMER_NUMBER = 100 ;
    default void printName(String Name){
        System.out.println("Name: "+ Name);
    }

}
