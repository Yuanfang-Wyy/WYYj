package kafka.src.main.java;

import kafka.consumer.*;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import org.apache.commons.collections.CollectionUtils;
import pthwch.kafka_01020.src.main.java.Consumer;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by weicheng.huang on 2017/4/23.
 */

public class KfkConsumerDemo {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
//        properties.put("zookeeper.connect", "172.16.1.33:2181,172.16.1.34:2181,172.16.1.35:2181");
        properties.put("zookeeper.connect", "172.16.2.70:2181,172.16.2.72:2181,172.16.2.80:2181");
        properties.put("auto.commit.enable", "true");
        properties.put("auto.commit.interval.ms", "1000");
//    properties.put("group.id", "test-group");
        properties.put("group.id", "groupid");

    /*
     * begin
     */
//   AuthenticationManager.setAuthMethod("kerberos");
//   AuthenticationManager.login("kafka@TDH", KfkProducerDemo.class.getClassLoader().getResource("kafka.keytab").getPath());
    /*
     * end
     */

        ConsumerConfig consumerConfig = new ConsumerConfig(properties);

        ConsumerConnector javaConsumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

        // topic的过滤器
        Whitelist whitelist = new Whitelist("topic5");
        List<KafkaStream<byte[], byte[]>> partitions = javaConsumerConnector.createMessageStreamsByFilter(whitelist);

        if (CollectionUtils.isEmpty(partitions)) {
            System.out.println("empty!");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(partitions.size());
        // 消费消息
        for (KafkaStream<byte[], byte[]> partition : partitions) {
            System.out.println("begin !");
            ConsumerIterator<byte[], byte[]> iterator = partition.iterator();
            while (iterator.hasNext()) {
                MessageAndMetadata<byte[], byte[]> next = iterator.next();
                System.out.println("partiton:" + next.partition());
                System.out.println("offset:" + next.offset());
                System.out.println("message:" + new String(next.message(), "utf-8"));
            }
            System.out.println("end !");
        }
    }
}

