//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.*;
//import org.apache.hadoop.hbase.client.*;
//import org.apache.hadoop.hbase.filter.PrefixFilter;
//import org.apache.hadoop.hbase.util.Bytes;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class HyperbaseDemo {
//
//	public static Configuration configuration;
//	static {
//    Configuration HBASE_CONFIG = new Configuration();
//    HBASE_CONFIG.addResource("core-site.xml");
//    HBASE_CONFIG.addResource("yarn-site.xml");
//    HBASE_CONFIG.addResource("hdfs-site.xml");
//    HBASE_CONFIG.addResource("hbase-site.xml");
//    configuration = HBaseConfiguration.create(HBASE_CONFIG);
//		System.out.println(HBASE_CONFIG);
//	}
//
//	public static void main(String[] args) {
////		createTable("fulltext2");
//
////		insertData("fulltext2");
//
////		QueryAll("fulltext2");
////		QueryByRowkey("fulltext2","test");
////		QueryByFilter("ss","245081628147497678593");
//		/* 		range范围前闭后开 		 */
////		QueryByRang("ss","2450816281474976785936","2450816281474976785939");
////		QueryByRang("fulltext3","test","test2");
////		QueryByTimestamp("fulltext2","2017/03/17 05:14:00","2017/03/17 09:50:00"	);
////		QueryByVersion("fulltext2","test0",5);
//
////		deleteRow("fulltext2","test");
//        dropTable("fulltext2");
//	}
//
//	/**
//	 * 查询多版本数据
//	 * @param tableName
//	 * @param rowkey
//	 * @param versionNum
//     */
//	private static void QueryByVersion(String tableName, String rowkey, Integer versionNum) {
//		try {
//			HTable hTable = new HTable(configuration,tableName);
//			Get get = new Get(Bytes.toBytes(rowkey));
//			get.setMaxVersions(versionNum);
//			Result result = hTable.get(get);
//			for (KeyValue keyValue : result.list()) {
//				System.out.println("列：" + new String(keyValue.getFamily()) + "  列限定符：" + new String(keyValue.getQualifier())
//						+ "====值:" + new String(keyValue.getValue()) + "  Timestamp:" + keyValue.getTimestamp() );
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * add by weicheng.huang
//	 * 根据时间戳检索
//	 * 这种查询包含历史版本,但是有最新的会拿最新的数据
//	 * @param tableName
//	 * @param startTimeStamp
//	 * @param endTimestamp
//     */
//	private static void QueryByTimestamp(String tableName, String startTimeStamp, String endTimestamp) {
//		try {
//			HTable hTable = new HTable(configuration,tableName);
//			Scan scan = new Scan();
//			scan.setTimeRange(DateToTimestamp(startTimeStamp),DateToTimestamp(endTimestamp));
////			scan.setTimeRange(Long.parseLong("1488354908000"),Long.parseLong("1488354909000"));
//			ResultScanner rs = hTable.getScanner(scan);
//
//			for (Result r : rs) {
//				System.out.println("获得到rowkey:" + new String(r.getRow()));
//				for (KeyValue keyValue : r.raw()) {
//					System.out.println("列：" + new String(keyValue.getFamily()) + "  列限定符：" + new String(keyValue.getQualifier())
//							+ "====值:" + new String(keyValue.getValue()));
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private static long DateToTimestamp(String dateTime) {
//		long unixTimestamp = 0;
//		try {
//			Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateTime);
//			unixTimestamp = date.getTime();
////			System.out.println(unixTimestamp);
//
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return unixTimestamp;
//	}
//
//	/**
//	 * add by weicheng.huang
//	 * 根据rowkey 范围检索
//	 * range为前闭后开的区间
//	 * @param tableName
//	 * @param startRow
//	 * @param endRow
//     */
//	private static void QueryByRang(String tableName, String startRow, String endRow) {
//		HTable hTable = null;
//		try {
//			hTable = new HTable(configuration,tableName);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Scan scan = new Scan();
//		scan.setStartRow(Bytes.toBytes(startRow));
//		scan.setStopRow(Bytes.toBytes(endRow));
////		scan.setCaching(9);
//		ResultScanner rs = null;
//		try {
//			rs = hTable.getScanner(scan);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		for (Result r : rs) {
//			System.out.println("获得到rowkey:" + new String(r.getRow()));
//			for (KeyValue keyValue : r.raw()) {
//				System.out.println("列：" + new String(keyValue.getFamily()) + "  列限定符：" + new String(keyValue.getQualifier())
//						+ "====值:" + new String(keyValue.getValue()));
//			}
//		}
//	}
//
//	/**
//	 * add by weicheng.huang
//	 * @param tableName
//	 * @param prefixOfRowkey
//     */
//	private static void QueryByFilter(String tableName, String prefixOfRowkey) {
//		System.out.println("Start query hbase table by prefixOfRowkey ......");
//		try {
//			HTable hTable = new HTable(configuration,tableName);
//			Scan scan = new Scan();
//			scan.setFilter(new PrefixFilter(Bytes.toBytes(prefixOfRowkey)));
//			ResultScanner rs = hTable.getScanner(scan);
//			for (Result r : rs) {
//				System.out.println("获得到rowkey:" + new String(r.getRow()));
//
//				for (KeyValue keyValue : r.raw()) {
//					System.out.println("列：" + new String(keyValue.getFamily()) + "  列限定符：" + new String(keyValue.getQualifier())
//							+ "====值:" + new String(keyValue.getValue()));
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//
//	}
//
//	/**
//	 * add by weicheng.huang
//	 * 根据rowkey查询记录
//	 * @param tableName
//	 * @param rowkey
//     */
//	private static void QueryByRowkey(String tableName,String rowkey) {
//		System.out.println("Start query hbase table by rowkey ......");
//		try {
//			HTable hTable = new HTable(configuration,tableName);
//			Get get = new Get(Bytes.toBytes(rowkey));
//
//			Result result = hTable.get(get);
//			// 根据column获取value值
//			byte[] value = result.getValue(Bytes.toBytes("column1"),Bytes.toBytes("cq1"));
//			System.out.println(Bytes.toString(value));
//			// 列出所有column的值
//			for (KeyValue keyValue : result.raw()) {
//				System.out.println("列：" + new String(keyValue.getQualifier())
//						+ "====值:" + new String(keyValue.getValue()));
//			}
//			System.out.println("end query hbase table by rowkey ......");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 创建表
//	 * @param tableName
//	 */
//	public static void createTable(String tableName) {
//		System.out.println("start create table ......");
//		try {
//			HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
//			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
//			tableDescriptor.addFamily(new HColumnDescriptor("column1"));
//			tableDescriptor.addFamily(new HColumnDescriptor("column2"));
//			tableDescriptor.addFamily(new HColumnDescriptor("column3"));
//			hBaseAdmin.createTable(tableDescriptor);
//		} catch (MasterNotRunningException e) {
//			e.printStackTrace();
//		} catch (ZooKeeperConnectionException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("end create table ......");
//	}
//
//	/**
//	 * 插入数据
//	 * @param tableName
//	 */
//	public static void insertData(String tableName) {
//		System.out.println("start insert data ......");
//		try {
//		HTable table = new HTable(configuration,tableName);
//		Put put = new Put("test0".getBytes());// 一个PUT代表一行数据，再NEW一个PUT表示第二行数据,每行一个唯一的ROWKEY，此处rowkey为put构造方法中传入的值
//		put.add("column1".getBytes(), "cq1".getBytes(), "aaa3555".getBytes());// 本行数据的第一列
////		put.add("column2".getBytes(), "cq2".getBytes(), "bbb2".getBytes());// 本行数据的第三列
////		put.add("column3".getBytes(), "cq3".getBytes(), "ccc2".getBytes());// 本行数据的第三列
//			table.put(put);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("end insert data ......");
//	}
//
//	/**
//	 * 删除一张表
//	 * @param tableName
//	 */
//	public static void dropTable(String tableName) {
//		try {
//			HBaseAdmin admin = new HBaseAdmin(configuration);
//			admin.disableTable(tableName);
//			admin.deleteTable(tableName);
//		} catch (MasterNotRunningException e) {
//			e.printStackTrace();
//		} catch (ZooKeeperConnectionException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//	/**
//	 * 根据 rowkey删除一条记录
//	 * @param tablename
//	 * @param rowkey
//	 */
//	 public static void deleteRow(String tablename, String rowkey)  {
//		try {
//			HTable table = new HTable(configuration, tablename);
//			List list = new ArrayList();
//			Delete d1 = new Delete(rowkey.getBytes());
//			list.add(d1);
//
//			table.delete(list);
//			System.out.println("删除行成功!");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 查询所有数据
//	 * @param tableName
//	 */
//	public static void QueryAll(String tableName) {
//		try {
//            HTable table = new HTable(configuration,tableName);
//			ResultScanner rs = table.getScanner(new Scan());
//			for (Result r : rs) {
//				System.out.println("获得到rowkey:" + new String(r.getRow()));
//				for (KeyValue keyValue : r.raw()) {
//					System.out.println("列：" + new String(keyValue.getFamily())
//							+ "====值:" + new String(keyValue.getValue()));
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}