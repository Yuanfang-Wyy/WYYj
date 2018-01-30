package hdfs.src.main.java.webhdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.web.WebHdfsFileSystem;

import java.io.IOException;
import java.net.URI;

/**
 * Created by weicheng.huang on 2017/3/6.
 */
public class CreateFile {
    public static void main(String[] args) throws IOException{
        URI uri = URI.create(WebHdfsFileSystem.SCHEME + "://172.16.2.90:50070");
        Path p = new Path("/tmp/newFile2_webhdfs");

        Configuration conf = new Configuration();

        WebHdfsFileSystem fs = (WebHdfsFileSystem) FileSystem.get(uri,conf);
        FSDataOutputStream out = fs.create(p);
        out.write("test".getBytes("UTF-8"));
        out.close();
        fs.close();
    }
}
