package kafka_01020.src.main.java;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyProducer {
    public static void main(String[] args) {
        final KafkaProducer<Integer, String> producer;
        final String topic = "test0828";

        Properties props = new Properties();
        props.put("bootstrap.servers", "172.16.140.27:9092");
        props.put("client.id", "DemoProducer");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class","MyPartitioner");
        producer = new KafkaProducer<>(props);

        int messageNo = 1;
        for (int i = 0; i < 10; i++) {
            String messageStr = "Message_" + messageNo;
            long startTime = System.currentTimeMillis();
            producer.send(new ProducerRecord<Integer, String>(topic,messageNo,messageStr));
            ++messageNo;
        }
        producer.close();
    }
}
