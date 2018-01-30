package kafka.src.main.java;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import pthwch.kafka_01020.src.main.java.Producer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by weicheng.huang on 2017/4/23.
 */
public class KfkProducerDemo {
    public static String KAFKA_TOPIC_NAME = null;

    public static void main(String[] args) throws IOException {
    /*
     * get kafka producer
     */

    /*
     * kerberos   authentication
     * begin
     */
//   AuthenticationManager.setAuthMethod("kerberos");
//   AuthenticationManager.login("kafka@TDH", KfkProducerDemo.class.getClassLoader().getResource("kafka.keytab").getPath());
    /*
     * end
     */
        InputStream is = KfkProducerDemo.class.getClassLoader().getResourceAsStream("kfk-test.properties");
        Properties p = new Properties();
        Producer<String, byte[]> producer = null;
        try {
            p.load(is);
            KAFKA_TOPIC_NAME = p.getProperty("kfk.topic").trim();
            producer = new Producer<String, byte[]>(new ProducerConfig(p));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    /*
     * 在运行代码前要先创建kafka topic 在/usr/lib/kafka/bin下运行如下命令： ./kafka-create-topic.sh
     * --zookeeper 172.16.2.65:2181 --partition 3 --topic kfktest --broker
     * 172.16.2.65 172.16.2.66 172.16.2.67
     */

//        String message = "send message to kafkaserver";
        String message = "send message with type 0";
        String hashKey = "message's  hashkey";
        System.out.println(KfkProducerDemo.KAFKA_TOPIC_NAME);
        KeyedMessage<String, byte[]> msg = new KeyedMessage<String, byte[]>(KfkProducerDemo.KAFKA_TOPIC_NAME,"0", message.getBytes());
        producer.send(msg);
    }
}
