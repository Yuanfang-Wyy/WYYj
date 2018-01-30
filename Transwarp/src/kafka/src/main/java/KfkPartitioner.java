package kafka.src.main.java;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class KfkPartitioner implements Partitioner{

    public KfkPartitioner(VerifiableProperties props) {
        // TODO Auto-generated constructor stub
    }
//    @Override
    public int partition(Object key, int numPartitions) {
        // TODO Auto-generated method stub
        return Math.abs(key.hashCode())%numPartitions;
    }
}
