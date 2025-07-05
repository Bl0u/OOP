import OOPStudying.Classes.*;
import OOPStudying.Interfaces.Test;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Tester.testOverriding();
//        Tester.testOverLoadingFunction() ;
//        Tester.testAbstractClassInheritance() ;
//        Tester.testInterface() ;
//        Tester.testScope() ;
    }

    public static class Tester{
        public static void testScope(){
            Person person = new Customer();
//            person.display() ; // error

        }
        public static void testInterface(){
            Test test = new InterfaceTest();
            test.printName("Peter");
            System.out.println(Test.MAX_CUSTOMER_NUMBER);
        }

        public static void testOverriding(){
            // person has whoAmI to print Human while customer has it to print Customer
            Person person = new Customer();
            person.whoAmI();
        }

        public static void testOverLoadingFunction(){
            Person person = new Customer();
            Person.displayName("Peter", "Emil");
        }

        public static void testAbstractClassInheritance(){
            Person person = new AbstractTestChild();
            person.whoAmI();
            AbstractTestChild abstractTestChild = new AbstractTestChild();
            abstractTestChild.quote();
        }

    }




}