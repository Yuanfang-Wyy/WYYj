package kafka_01020.src.main.java;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class MyConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.2.70:9098");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        props.put("auto.offset.reset","earliest");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("test01"));
        System.out.println("The consumer's topic is " + consumer.subscription());
        System.out.println("Poll data is beginning");

//        String[] options = new String[]{
//                "--create",
//                "--zookeeper",
//                "172.16.2.70:2181",
//                "--partitions",
//                "3",
//                "--topic",
//                "test01",
//                "--replication-factor",
//                "1"
//        };
//        TopicCommand.main(options);
//        System.out.println("succ");


        while(true){
            ConsumerRecords<String, String> records = consumer.poll(1000);

//            if (records.isEmpty()){
//                System.out.println("Records is empty");
//            } else {
//                System.out.println("Records is not empty!");
//            }
            for (ConsumerRecord<String,String> record : records)
                System.out.printf("offset = %d  ,key = %s,  value = %s\n", record.offset(), record.key(), record.value());
        }
    }
}
