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
public class AppendFile {
    public static void main(String[] args) throws IOException{
        URI uri = URI.create(WebHdfsFileSystem.SCHEME + "://172.16.2.90:50070");
        Path p = new Path("/tmp/newFile2_webhdfs");

        Configuration conf = new Configuration();
        WebHdfsFileSystem fs = (WebHdfsFileSystem) FileSystem.get(uri,conf);
//        InputStream in = new BufferedInputStream(new FileInputStream());
        OutputStream out = fs.append(p);
        String str_app = "\n试着加行字符串\n";
        InputStream in = new BufferedInputStream(new ByteArrayInputStream(str_app.getBytes("UTF-8")));

//        out.write("test".getBytes("UTF-8"));
        IOUtils.copyBytes(in,out,conf);
        out.close();
        fs.close();

    }
}

