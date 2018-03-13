package cn.jastz.mybatis.demo;

import org.junit.Test;

/**
 * @author zhiwen
 */
public class RuntimeTest {

    @Test
    public void availableProcessors() {
        System.out.println("可用处理器：" + Runtime.getRuntime().availableProcessors());
        System.out.println("空闲内存：" + Runtime.getRuntime().freeMemory());
        System.out.println("总内存：" + Runtime.getRuntime().totalMemory());
    }
}
