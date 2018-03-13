package cn.jastz;

import org.junit.Test;

/**
 * @author zhiwen
 */
public class FinallyReturnTest {
    @Test
    public void finallyReturn() {
        System.out.println(returnString());
    }

    private String returnString() {
        try {
            return "I'm return";
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("I'm finally");
        }
    }
}
