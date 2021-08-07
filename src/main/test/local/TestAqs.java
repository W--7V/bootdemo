package local;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class TestAqs {

    static Integer c = 0;
    static MyLock lock = new MyLock();

    public static void main(String[] args) throws InterruptedException {


//        Runnable r = () ->{
//            for(int i=0;i<1000;i++){
//                lock.tryAcquire(1);
//                c += 1;
//                lock.tryRelease(0);
//            }
//        };
        Thread t1 = new Thread(() ->add());
        Thread t2 = new Thread(() ->add());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(c);

    }

    public static void add() {
        for (int i = 0; i < 1000; i++) {
            lock.lock();
            c++;
            lock.unlock();
        }
    }

}

class MyLock {

    private Sync sync = new Sync();

    public void lock(){
        sync.acquire(1);
    }

    public void unlock(){
        sync.release(1);
    }

    class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }
    }
}

class ClaculateJob implements Runnable {
    MyLock lock;
    Integer cnt;

    public ClaculateJob(MyLock lock, Integer c) {
        this.lock = lock;
        this.cnt = c;
    }

    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
//            lock.tryAcquire(1);
//            cnt++;
//            lock.tryRelease(0);
        }
    }
}