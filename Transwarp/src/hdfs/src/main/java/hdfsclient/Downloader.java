package hdfs.src.main.java.hdfsclient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

/**
 * Created by weicheng.huang on 2017/3/5.
 */
public class Downloader {
    private static Log LOG = LogFactory.getLog(Downloader.class);
    private String src;
    /*
    private static Configuration conf = new Configuration();
    public static DistributedFileSystem dfs = new DistributedFileSystem();
    dfs.initialize(new URI(conf.get("fs.defaultFS")), conf);
    */
    private static Configuration conf;
    private static DistributedFileSystem dfs;
    static {
        conf = new HdfsConfiguration();
        dfs = new DistributedFileSystem();
        try {
            dfs.initialize(new URI(conf.get("fs.defaultFS")), conf);
        } catch (Exception e){
            LOG.error(e.getMessage(),e);
        }
    }

    public  Downloader(String src) {this.src = src;}

    private void download(String dest) {
        Path path = new Path(src);
        File file = new File(dest);
        file.mkdirs();
        try{
            if(dfs.isFile(path)){
                innerDownloadFile(src,dest);
            } else {
                innerDownloadDir(src,dest);
            }
        }catch (IOException e){
            LOG.error(e.getMessage(),e);
        }
    }

    private void innerDownloadDir(String src, String dest) {
        Path path = new Path(src);
        File file = new File(dest + File.separator + path.getName());
//        System.out.println("src is " + src + " and path.getName() is " + path.getName());
        file.mkdirs();
        try{
            FileStatus[] fss = dfs.listStatus(path);
//            System.out.println("path is " + path.toString());
//            for(int i = 0;i < fss.length; i++){
//                System.out.println(fss.toString());
//            }
//            System.out.println();
            for(int i = 0;i < fss.length; i++){
                if(fss[i].isFile()){
                    innerDownloadFile(fss[i].getPath().toString(),dest + File.separator + path.getName());
                }else {
                    innerDownloadDir(fss[i].getPath().toString(),file.getAbsolutePath() );
                }
            }
        }catch(Exception e){
            LOG.error(e.getMessage(),e);
        }

    }

    private void innerDownloadFile(String src, String dest) {
        Path path = new Path(src);
//        System.out.println("file src is " + src + " and path.getName() is " + path.getName());
        try{
            if(dfs.exists(path)){
                File file = new File(dest + File.separator + path.getName());
                file.createNewFile();
                InputStream in = dfs.open(path);
                OutputStream out = new FileOutputStream(file);
                IOUtils.copyBytes(in,out,conf);
                in.close();
                out.close();
                IOUtils.closeStream(in);
            }
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
        }
    }

    public static void main(String[] args) throws IOException {
        Downloader download = new Downloader("/tmp/newDir2/1.txt");
        download.download("C:\\Users\\weicheng.huang\\Desktop");
    }
}