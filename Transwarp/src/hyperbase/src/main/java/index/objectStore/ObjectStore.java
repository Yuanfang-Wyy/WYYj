//package index.objectStore;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//
//import java.io.IOException;
//
//public class ObjectStore {
//  public static void main(String[] args) throws IOException {
//
//    Configuration HBASE_CONFIG = new Configuration();
//    HBASE_CONFIG.addResource("core-site.xml");
//    HBASE_CONFIG.addResource("yarn-site.xml");
//    HBASE_CONFIG.addResource("hdfs-site.xml");
//    HBASE_CONFIG.addResource("hbase-site.xml");
//    Configuration conf = HBaseConfiguration.create(HBASE_CONFIG);
////    UserGroupInformation.setConfiguration(conf);
////    UserGroupInformation.loginUserFromKeytab("hbase@TDH", HyperbaseDemo.class.getClassLoader().getResource("hbase.keytab").getPath());
//    HbaseUtil hu = new HbaseUtil();
//    hu.conf = conf;
//
////    hu.init("t3");
////    hu.insert("t3", "E:\\tmp\\HBase空值不存.png", "cf", "cm2");
//    hu.get("t3","E:\\tmp\\","","cf","cm2");
//    hu.close();
//  }
//}
