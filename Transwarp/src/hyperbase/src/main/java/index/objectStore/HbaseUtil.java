//package index.objectStore;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hbase.*;
//import org.apache.hadoop.hbase.client.*;
//import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
//import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
//import org.apache.hadoop.hbase.protobuf.generated.HyperbaseProtos;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.apache.hadoop.hyperbase.client.HyperbaseAdmin;
//import org.apache.hadoop.hyperbase.secondaryindex.IndexedColumn;
//import org.apache.hadoop.hyperbase.secondaryindex.LOBIndex;
//import org.apache.hadoop.hyperbase.secondaryindex.SecondaryIndexUtil;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.UUID;
//
//
//public class HbaseUtil {
//
//  public Configuration conf;
//  private HTableInterface htable;
//
//  @SuppressWarnings("deprecation")
//  public void init(String tableName) {
//
//    try {
//      conf = HBaseConfiguration.create();
//      conf.set("hbase.client.keyvalue.maxsize", "-1");
//      HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
//      HyperbaseAdmin admin = new HyperbaseAdmin(conf);
//
//      HTableDescriptor table = new HTableDescriptor(tableName);
//
//      HColumnDescriptor f1 = new HColumnDescriptor("cf");
//      f1.setValue(HConstants.HREGION_MEMSTORE_SPECIAL_COLUMN_FAMILY_FLUSHSIZE_KEY, 1024 * 1024 * 64 + "");
//
//      boolean withCompress = true;
//      if (withCompress) {
//        f1.setDataBlockEncoding(DataBlockEncoding.PREFIX_TREE);
//        f1.setCompressionType(Algorithm.SNAPPY);
//      }
//
//      int ttl = 24 * 60 * 60 * 365;
//      f1.setTimeToLive(ttl);
//      table.addFamily(f1);
//
//      if (!hBaseAdmin.tableExists(new String(tableName).getBytes())) {
//        hBaseAdmin.createTable(table);
//        hBaseAdmin.close();
//        System.out.println(tableName + " is not exists");
//      } else {
//        System.out.println(tableName + " is exists");
//        admin.close();
//          return;
//      }
//      /*
//       * column 必须存在
//       * zk  没有数据
//       */
//
//      HyperbaseProtos.SecondaryIndex.Builder LOBBuilder = HyperbaseProtos.SecondaryIndex.newBuilder();
//
//      LOBBuilder.setClassName(LOBIndex.class.getName());
//      LOBBuilder.setUpdate(true);
//      LOBBuilder.setDcop(true);
//      IndexedColumn column = new IndexedColumn(new String("cf").getBytes(), Bytes.toBytes("cm1"));
//      LOBBuilder.addColumns(column.toPb());
//      admin.addLob(TableName.valueOf(tableName), new LOBIndex(LOBBuilder.build()), Bytes.toBytes("cm2"), false, 1);
//      htable = new HTable(conf, tableName);
//
//      admin.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//  }
//
//
//  public synchronized void insert(String path, String cf, String cq) {
//    DataDto data = new DataDto();
//    String fileName = new Path(path).getName();
////    data.setRowKey(fileName.getBytes());
//    data.setRowKey( UUID.randomUUID().toString().getBytes() );
//    data.setColumnFamily(cf.getBytes());
//    data.setColumnQualifier(cq.getBytes());
//    data.setValue(FileByteArrayUtil.getByteFromFile(path));
//    insert(data);
//  }
//  public synchronized void insert(String tablename,String path, String cf, String cq) {
//    DataDto data = new DataDto();
//    String fileName = new Path(path).getName();
////    data.setRowKey(fileName.getBytes());
//    data.setRowKey( UUID.randomUUID().toString().getBytes() );
//    data.setColumnFamily(cf.getBytes());
//    data.setColumnQualifier(cq.getBytes());
//    data.setValue(FileByteArrayUtil.getByteFromFile(path));
//    insert(data,tablename);
//  }
//  public synchronized void insert(DataDto data,String tablename) {
//    Put p = new Put(data.getRowKey());
//    p.setDurability(Durability.SKIP_WAL); //设置WAL持久化等级
//    System.out.println(data.getValue());
//    p.add(data.getColumnFamily(), data.getColumnQualifier(), data.getValue());
//    try {
//              conf = HBaseConfiguration.create();
//      conf.set("hbase.client.keyvalue.maxsize", "-1");
//        htable = new HTable(conf,tablename);
//      htable.put(p);
//      System.out.println("put successed");
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  public synchronized void insert(DataDto data) {
//
//
//    Put p = new Put(data.getRowKey());
//    p.setDurability(Durability.SKIP_WAL);
//    System.out.println(data.getValue());
//    p.add(data.getColumnFamily(), data.getColumnQualifier(), data.getValue());
//    try {
//
//      htable.put(p);
//      System.out.println("put successed");
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  public void insert(List<Put> list) {
//    try {
//      htable.put(list);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  public void get(String tablename, String path, String rowkey) {
//    HTable table = null;
//    Configuration conf = HBaseConfiguration.create();
//    try {
//      table = new HTable(conf, Bytes.toBytes(tablename));
//      Scan scan = new Scan();
//
//      byte[] stopkey = Bytes.toBytes(rowkey);
//
//      SecondaryIndexUtil.IncreaseBytes(stopkey);
//
//      scan.setStopRow(stopkey);
//
//      ResultScanner resultSet = table.getScanner(scan);
//      for (Result result : resultSet) {
//        byte[] rowkeybytes = result.getRow();
//        byte[] picbytes = result.getValue(Bytes.toBytes("cf"), Bytes.toBytes("f1"));
//        FileByteArrayUtil.getFileFromByte(picbytes, path + File.separator + new String(rowkeybytes));
//      }
//      table.close();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//  public synchronized void get(String tablename,String path,String rowkey, String cf, String cq) {
//     HTable table = null;
//    Configuration conf = HBaseConfiguration.create();
//    try {
//      table = new HTable(conf, Bytes.toBytes(tablename));
//      Scan scan = new Scan();
//
//      byte[] stopkey = Bytes.toBytes(rowkey);
//
//      SecondaryIndexUtil.IncreaseBytes(stopkey);
//
//      scan.setStopRow(stopkey);
//
//      ResultScanner resultSet = table.getScanner(scan);
//      for (Result result : resultSet) {
//        byte[] rowkeybytes = result.getRow();
//        byte[] picbytes = result.getValue(Bytes.toBytes(cf), Bytes.toBytes(cq));
//        FileByteArrayUtil.getFileFromByte(picbytes, path + File.separator + new String("aaa.png"));
//      }
//      table.close();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//  public void close() {
//    if (htable != null) {
//      try {
//        htable.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//  }
//}
