package springboot.demo.util;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class MyKafkaProducer {
	private static final String TOPIC = "mytopic";
	private static final String BROKER_LIST = "localhost:9092";
	private static KafkaProducer<String, String> producer = null;

	static ExecutorService executorService = new ThreadPoolExecutor(10, 20, 5, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(1000));

	static {
		Properties configs = initConfig();
		producer = new KafkaProducer<String, String>(configs);
	}

	private static Properties initConfig() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}

	public static void produce(String topic, String key, String value) {
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);

		executorService.submit(() -> {
			Future<?> f = producer.send(record, new Callback() {
				@Override
				public void onCompletion(RecordMetadata arg0, Exception arg1) {
					System.out.println("offset:"+arg0.offset());
					System.out.println("record:"+record);
				}
			});
			return f;
		});

	}

	public static void main(String[] args) throws InterruptedException {
		for (int j = 0; j < 100; j++) {
			MyKafkaProducer.produce(TOPIC, "" + new Random().nextInt(), "" + new Random().nextInt());
		}
		Thread.sleep(1000);
		MyKafkaProducer.executorService.shutdown();
	}
}
