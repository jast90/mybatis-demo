package cn.jastz.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author zhiwen
 */
public class ProxyTest {

    @Test
    public void jdkProxy() {
        Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class}, new MyHelloInvocationHandler(new HelloImpl()));
        hello.sayHello();
        hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class}, new MyHelloInvocationHandler(new HelloImpl2()));
        hello.sayHello();

    }
}
