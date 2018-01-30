package kafka_01020.src.main.java;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class MyPartitioner implements Partitioner {
    private static Logger LOG = LoggerFactory.getLogger(MyPartitioner.class);
    public  MyPartitioner(){
    }

    @Override
    /**
     * s is Topic Name
     * o is ProducerRecord's Key
     * Cluster is kafka's cluster, we use it for the num of partition
     */
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        System.out.println("The s is " + s + " and o is " + o.toString());
        List<PartitionInfo> partitions = cluster.partitionsForTopic(s);
        int numPartitions = partitions.size();
        int Key = 0;
        Key = Integer.parseInt(o == null ? "" : o.toString());
        int a = Math.abs(Key % numPartitions);
        System.out.println(a);
        return a;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
