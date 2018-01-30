package kafka.src.main.java;

import kafka.admin.TopicCommand;

/**
 * Created by weicheng.huang on 2017/3/9.
 */
public class kfkInit {
    public static void main(String[] args) {
        initTopic();
    }

    private static void initTopic() {
        String[] options = new String[]{
                "--create",
                "--zookeeper",
                "172.16.140.2181",
                "--partitions",
                "3",
                "--topic",
                "kafkaTraing",
                "--replication-factor",
                "1"
        };
        TopicCommand.main(options);
        System.out.println("succ");
    }
}
