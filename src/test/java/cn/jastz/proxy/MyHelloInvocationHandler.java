package cn.jastz.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhiwen
 */
public class MyHelloInvocationHandler implements InvocationHandler {
    private Hello hello;

    public MyHelloInvocationHandler(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(hello, args);
        return null;
    }
}
