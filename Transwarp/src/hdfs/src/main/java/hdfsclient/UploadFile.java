package hdfs.src.main.java.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;

/**
 * Created by weicheng.huang on 2017/3/5.
 */
public class UploadFile {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        String localFile = "C:\\Users\\weicheng.huang\\Desktop\\sqoop_Jie.sql";
        InputStream in = new BufferedInputStream(new FileInputStream(localFile));
        Path p = new Path("/tmp/newDir2/1.txt");

        FileSystem fs = p.getFileSystem(conf);
        OutputStream out = fs.create(p);
        IOUtils.copyBytes(in,out,conf);
        fs.close();
        IOUtils.closeStream(in);
    }
}
