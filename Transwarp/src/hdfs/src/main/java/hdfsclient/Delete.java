package hdfs.src.main.java.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by weicheng.huang on 2017/3/5.
 */
public class Delete {
    public static void main(String [] args) throws IOException{
        String rootPath = "hdfs://172.16.2.90:8020";
        Path p = new Path(rootPath + "/tmp/hdfsTraining");

        Configuration conf = new Configuration();
        FileSystem fs = p.getFileSystem(conf);
        boolean b = fs.delete(p,true);
        System.out.println(b);
        fs.close();

    }
}
