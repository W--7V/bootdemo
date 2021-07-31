package springboot.demo.util;

import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.concurrent.*;

public class ConsumerUtil {
    static ExecutorService executorService = new ThreadPoolExecutor(10, 20, 5, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1000));

    public static void poll(){

        Future<ConsumerRecords<String, String>> f = executorService.submit(new MyKafkaConsumer());
        try {
            ConsumerRecords<String, String> records = f.get();
            records.forEach(r -> System.out.println(r.key() + " " + r.value())); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while (true){
            poll();
        }
    }

}
