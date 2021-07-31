package springboot.demo.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Callable;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;


public class MyKafkaConsumer implements Callable<ConsumerRecords<String, String>> {
    private static final String TOPIC = "mytopic";
    private static final String BROKER_LIST = "localhost:9092";
    private static KafkaConsumer<String, String> consumer = null;

    static {

        Properties configs = initConfig();
        consumer = new KafkaConsumer<String, String>(configs);
        consumer.subscribe(Arrays.asList(TOPIC));
        Class<? extends KafkaConsumer> aClass = consumer.getClass();
        try {
            Field log = aClass.getDeclaredField("log");
            for (Field f : log.getClass().getDeclaredFields()) {
                System.out.println(f.getName());
            }
            System.out.println(log.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

//		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
//		Logger root = loggerContext.getLogger("root");
//		root.setLevel(Level.INFO);

    }

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//		properties.put(ConsumerConfig.METRICS_RECORDING_LEVEL_CONFIG, "INFO");
        properties.put("group.id", "group1");
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.offset.reset", "earliest");
        return properties;
    }

    public static void consumer() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            records.forEach(r -> System.out.println(r.key() + " " + r.value()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public ConsumerRecords<String, String> call() throws Exception {
		return consumers();
	}

	public static ConsumerRecords<String, String> consumers() {
        ConsumerRecords<String, String> records = consumer.poll(1000);
        return records;
    }

    public static void main(String[] args) {
        MyKafkaConsumer.consumer();
    }
}
