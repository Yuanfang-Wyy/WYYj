package hdfs.src.main.java.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import java.io.*;

/**
 * Created by weicheng.huang on 2017/3/5.
 */
public class AppendFile {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        Path p = new Path("/tmp/hdfsTraining/file.txt");

        FileSystem fs = p.getFileSystem(conf);
        fs.setReplication(p,(short)1);
        InputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\weicheng.huang\\Desktop\\sqoop_Jie.sql"));
        OutputStream out = fs.append(p);
        IOUtils.copyBytes(in,out,conf);
        fs.close();
        IOUtils.closeStream(in);
    }
}
