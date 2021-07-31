package local;

public class TestThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("interrupted");
                        return;
                    }


                    try {
                        System.out.println(Thread.currentThread().isInterrupted());
                        Thread.sleep(3000);
                        System.out.println("sleep");
                        System.out.println(Thread.currentThread().isInterrupted());
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().isInterrupted());
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();

        Thread.sleep(2000);
        thread.interrupt();

    }
}
