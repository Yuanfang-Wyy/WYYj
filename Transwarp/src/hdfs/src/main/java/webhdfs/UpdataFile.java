package hdfs.src.main.java.webhdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.web.WebHdfsFileSystem;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

/**
 * Created by weicheng.huang on 2017/3/6.
 */
public class UpdataFile {
    public static void main(String[] args) throws IOException{
        URI uri = URI.create(WebHdfsFileSystem.SCHEME + "://172.16.2.90:50070");
        Path p = new Path("/tmp/newFile1_webhdfs.txt");

        Configuration conf = new Configuration();
        String localFile = "C:\\Users\\weicheng.huang\\Desktop\\sqoop_Jie.sql";
        InputStream in = new BufferedInputStream(new FileInputStream(localFile));
        WebHdfsFileSystem fs = (WebHdfsFileSystem)FileSystem.get(uri,conf);
        OutputStream out = fs.create(p);
        IOUtils.copyBytes(in,out,conf);
        fs.close();
        IOUtils.closeStream(in);
    }
}
