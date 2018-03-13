package cn.jastz;

import org.junit.Test;

import javax.xml.ws.Service;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhiwen
 */
public class GuavaStripedTest {
    private GuavaStripedService stripedService = new GuavaStripedService();

    /**
     *
     */
    @Test
    public void testStriped() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 2; i > 0; i--) {
            executorService.submit(() -> {
                stripedService.open("hello");
            });
        }
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                break;
            }
        }

    }

    @Test
    public void testStriped1() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 2; i > 0; i--) {
            executorService.submit(() -> {
                stripedService.open("hello" + UUID.randomUUID());
            });
        }
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                break;
            }
        }

    }
}
