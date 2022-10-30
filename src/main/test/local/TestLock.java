package local;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    static int val = 0;

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                val++;
                lock.unlock();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
//        Thread t3 = new Thread(r);

        t1.start();
        t2.start();
//        t3.start();

        t1.join();
        t2.join();
//        t3.join();

        System.out.println(val);
    }
}
