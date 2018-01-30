//package index;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.HColumnDescriptor;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.*;
//import org.apache.hadoop.hbase.expr.ExprFactory;
//import org.apache.hadoop.hbase.expr.ExprInterface;
//import org.apache.hadoop.hbase.expr.result.BytesResult;
//import org.apache.hadoop.hbase.expr.result.CollectionResult;
//import org.apache.hadoop.hbase.filter.ExprFilter;
//import org.apache.hadoop.hbase.filter.Filter;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.apache.hadoop.hyperbase.client.HyperbaseAdmin;
//import org.apache.hadoop.hyperbase.mapreduce.IndexRebuilder;
//import org.apache.hadoop.hyperbase.secondaryindex.SecondaryIndex;
//import org.apache.hadoop.hyperbase.secondaryindex.SecondaryIndexUtil;
//import org.apache.hadoop.security.UserGroupInformation;
//
//import java.io.IOException;
//import java.security.PrivilegedAction;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//
//
//@SuppressWarnings("deprecation")
//public class GlobalIndexDemo {
//  private static Log LOG = LogFactory.getLog(GlobalIndexDemo.class);
//  private String tableName;
//  private String columnFamily;
//  private String columnName;
//  public String C1_INDEX;
//  private String indexName;
//
//  private Configuration conf;
//
//  public GlobalIndexDemo(String tableName, String columnFamily, String columnName, Configuration configuration) {
//    super();
//    this.conf = configuration;
//    this.tableName = tableName;
//    this.columnFamily = columnFamily;
//    this.columnName = columnName;
//
//    this.C1_INDEX = "COMBINE_INDEX|INDEXED=" + columnFamily + ":" + columnName + ":9|rowKey:rowKey:7,UPDATE=true";
//
//    this.indexName = tableName + "_" + columnName;
//  }
//
//  public void init() {
//
//    HBaseAdmin admin = null;
//
//    try {
//      admin = new HBaseAdmin(conf);
//
//      HTableDescriptor table = new HTableDescriptor(tableName);
//      HColumnDescriptor f1 = new HColumnDescriptor("cf");
//      table.addFamily(f1);
//      if (!admin.tableExists(new String(tableName).getBytes())) {
//        admin.createTable(table);
//        LOG.info(tableName + " is not exists");
//      } else {
//        LOG.info(tableName + " is exists");
//      }
//
//    } catch (Exception e) {
//      LOG.error(e.getMessage(), e);
//    } finally {
//      if (null != admin) {
//        try {
//          admin.close();
//        } catch (IOException e) {
//          LOG.error("GlobalIndexAdmin close error");
//        }
//      }
//    }
//  }
//  public void creteGlobalIndex() throws IOException{
//    HyperbaseAdmin hAdmin = null;
//    try {
//      hAdmin = new HyperbaseAdmin(conf);
//      TableName tName = TableName.valueOf(tableName);
//      SecondaryIndex index = SecondaryIndexUtil.createSecondaryIndexFromPattern(C1_INDEX);
//
//      hAdmin.addGlobalIndex(tName, index, Bytes.toBytes("global"), null, false);
//    } catch (Exception e) {
//      LOG.error(e.getMessage(), e);
//    } finally {
//      if (null != hAdmin) {
//        try {
//          hAdmin.close();
//        } catch (IOException e) {
//          LOG.error("GlobalIndexAdmin close error");
//        }
//      }
//    }
//  }
//  public void deleteGlobalIndex() throws IOException {
//    HyperbaseAdmin hAdmin = null;
//    hAdmin = new HyperbaseAdmin(conf);
//    hAdmin.deleteGlobalIndex(TableName.valueOf(tableName), Bytes.toBytes("global"));
//    hAdmin.close();
//  }
//  public void reuildGlobalIndex() throws IOException {
//    IndexRebuilder indexRebuilder = new IndexRebuilder(conf);
//
//    indexRebuilder.rebuildGlobalIndex(TableName.valueOf(tableName), Bytes.toBytes("global"));
//  }
//  public void initData() {
//    IndexHTable hTable = null;
//    try {
//      hTable = new IndexHTable(conf, tableName);
//      hTable.setAutoFlushTo(false);
//      List<Put> puts = new ArrayList<Put>();
//      byte[] familyBytes = columnFamily.getBytes();
//      byte[] qualifier = columnName.getBytes();
//      for (int i = 0; i < 100; i++) {
//        Put put = new Put(Bytes.toBytes("row" + i));
//        put.add(familyBytes, qualifier, ("value" + i).getBytes());
//        puts.add(put);
//      }
//      hTable.put(puts);
//      hTable.flushCommits();
//    } catch (Exception e) {
//      LOG.error(e.getMessage(), e);
//    } finally {
//      try {
//        hTable.close();
//      } catch (IOException e) {
//        LOG.error("htable close error");
//      }
//    }
//
//  }
//  public void scan() {
//    IndexHTable hTable = null;
//    try {
//      hTable = new IndexHTable(conf, tableName);
//      Scan scan = new Scan();
//      scan.setFilter(getInFilter(columnFamily.getBytes(), columnName.getBytes(), "value3".getBytes(), "value9".getBytes()));
//
//      scan.setIndexColumn(Bytes.toBytes(indexName));
//
//      ResultScanner rs = hTable.getScanner(scan);
//      Result result = null;
//      while (null != (result = rs.next())) {
//        System.out.println("================" + Bytes.toString(result.getRow()));
//      }
//
//    } catch (Exception e) {
//    }
//  }
//  public static Filter getInFilter(byte[] family, byte[] qualifier, byte[]... c1) {
//    ExprInterface kobhVarExpr = ExprFactory.createVariableExpr(family, qualifier);
//    Collection<org.apache.hadoop.hbase.expr.result.Result> colls = new ArrayList<org.apache.hadoop.hbase.expr.result.Result>();
//    for (byte[] c : c1) {
//      colls.add(BytesResult.getInstance(c));
//    }
//    org.apache.hadoop.hbase.expr.result.Result result = CollectionResult.getInstance(colls);
//    ExprInterface kkbhValueExpr = ExprFactory.createConstExpr(result);
//    ExprInterface funcExpr = ExprFactory.createInExpr(kobhVarExpr, kkbhValueExpr);
//    ExprFilter kkbhExprFilter = new ExprFilter(funcExpr);
//    return kkbhExprFilter;
//  }
//  public static void main(String[] args) throws IOException {
//      UserGroupInformation ugi = UserGroupInformation.createRemoteUser("hbase");
//    ugi.doAs(new PrivilegedAction<Void>() {
//      @Override
//      public Void run() {
//        try {
//            Configuration HBASE_CONFIG = new Configuration();
//    /*
//     * windows
//     */
//
//    HBASE_CONFIG.addResource("core-site.xml");
//    HBASE_CONFIG.addResource("hbase-site.xml");
//    HBASE_CONFIG.addResource("yarn-site.xml");
//    HBASE_CONFIG.addResource("hdfs-site.xml");
//    HBASE_CONFIG.addResource("mapred-site.xml");
//
//    /*
//     * linux
//     */
////    HBASE_CONFIG.addResource("./conf/core-site.xml");
////    HBASE_CONFIG.addResource("./conf/hbase-site.xml");
////    HBASE_CONFIG.addResource("./conf/yarn-site.xml");
////    HBASE_CONFIG.addResource("./conf/hdfs-site.xml");
//
//    Configuration conf = HBaseConfiguration.create(HBASE_CONFIG);
//            conf.set("mapreduce.framework.name", "yarn");
//            conf.set("mapreduce.app-submission.cross-platform", "true");
////            conf.set("mapred.remote.os", "Linux");
//    GlobalIndexDemo indexDemo = new GlobalIndexDemo("globalindexdemo", "cf", "c1", conf);
////    indexDemo.init();
////    indexDemo.initData();
////    indexDemo.creteGlobalIndex();
////    indexDemo.scan();
////    indexDemo.reuildGlobalIndex();
//    indexDemo.deleteGlobalIndex();
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//        return null;
//      }
//    });
//  }
//}
