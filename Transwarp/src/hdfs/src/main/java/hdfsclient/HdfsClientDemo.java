package hdfs.src.main.java.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by weicheng.huang on 2017/3/5.
 */
public class HdfsClientDemo {
    public static void main(String[] args) throws IOException{
        Configuration conf = new Configuration();

//        UserGroupInformation.setConfiguration(conf);

        FileSystem fs = FileSystem.get(conf);

        fs.createNewFile(new Path("/tmp/pthwch"));
//        fs.delete(new Path("/tmp/pthwch"));
        fs.close();
    }
}