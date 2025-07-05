package OOPStudying.Classes;

public class AbstractTestChild extends AbstractTest {
    @Override
    public void quote() {
        System.out.println("\"Quote\"");
    }

    @Override
    public void whoAmI() {
        System.out.println("Abstract TestChild");
    }
}
