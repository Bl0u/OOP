package OOPStudying.Classes;

public abstract class AbstractTest extends Person{
    @Override
    public void whoAmI() {
        System.out.println("Abstract test");
    }
    public abstract void quote() ;
}
