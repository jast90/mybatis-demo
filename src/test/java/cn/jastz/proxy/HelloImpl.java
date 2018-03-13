package cn.jastz.proxy;

/**
 * @author zhiwen
 */
public class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("Hello !!");
    }
}
