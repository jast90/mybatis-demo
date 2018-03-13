package cn.jastz;

import com.google.common.util.concurrent.Striped;

import java.util.concurrent.locks.Lock;

/**
 * @author zhiwen
 */
public class GuavaStripedService {
    private static final Striped<Lock> striped = Striped.lazyWeakLock(127);

    public void open(String s) {
        Lock lock = striped.get(s);
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + s);
            Thread.sleep(6000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
