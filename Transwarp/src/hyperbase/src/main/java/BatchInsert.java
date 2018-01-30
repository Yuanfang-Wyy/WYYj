package hyperbase.src.main.java;

import java.sql.*;


public class BatchInsert {
  //Hive2 Drivers
  private static String driverName = "org.apache.hive.jdbc.HiveDriver";
  //Hive1 Drivers
//  private static String driverName = "org.apaceh.hadoop.hive.jdbc.HiveDriver";
  private  static Connection conn;
  
  public static void main(String[] args) throws Exception {
    try {
      Class.forName(driverName);
      } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
      }
    
    /*
     * Hive2 JDBC URL with kerberos certification
     */
//    String jdbcURL = "jdbc:hive2://172.16.2.170:10000/default;principal=hive/poc-px07@TDH;"+
//    "authentication=kerberos;"+
//    "kuser=hive/poc-px07@TDH;"+
//    "keytab="+HyperbaseBatchInsert.class.getClassLoader().getResource("hive.keytab").getPath()+";"+
//    "krb5conf="+HyperbaseBatchInsert.class.getClassLoader().getResource("krb5.conf").getPath()+"";
//    conn = DriverManager.getConnection(jdbcURL);

    /*
     * hiveserver2 JDBC URL with LDAP certification
     */
//    String jdbcURL = "jdbc:hive2://172.16.2.170:10000/default";
//    conn = DriverManager.getConnection(jdbcURL,"","");
    
    /*
     * hiveserver2 jdbc url
     */
    String jdbcURL = "jdbc:hive2://172.16.2.90:10000/default";
    conn = DriverManager.getConnection(jdbcURL);

    /*
     * hiveserver1 jdbc url
     */
//    String jdbcURL = "jdbc:transwarp://172.16.2.188:10000/default";
//    conn = DriverManager.getConnection(jdbcURL);

    Statement stmt = conn.createStatement();
//    testHBaseBatchInsertWithStructRowKey();
    testHBaseBatchInsertWithoutStructRowKey();
    String sql  = "select c1, c2 from batchinsertwithoutstructrowkey";
    ResultSet rs = stmt.executeQuery(sql);
    while(rs.next()) {
    System.out.println(rs.getInt(1));
    System.out.println(rs.getString(2));
    }
    /*
     * 
     */
    rs.close();
    stmt.close();
    conn.close();
  }
  
  /*
   * grant 'hive' ,'RW'
   */
  public static void testHBaseBatchInsertWithStructRowKey() throws SQLException{
    Statement stmt = conn.createStatement();
    String sqlCreate =
        "create table if not exists batchinsertwithstructrowkey ( " +
            "key struct<c2:int,c1:int>," +
            "c0 string, " +
            "c1 int," +
            "c2 int" +
            ") " +
            "stored by 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' ";
    String sqlDrop   = "drop table if exists batchinsertwithstructrowkey";
    String sqlInsert = "insert into batchinsertwithstructrowkey(c0, c1, c2) values(?,?,?)";
    stmt.execute(sqlDrop);
    stmt.execute(sqlCreate);

    PreparedStatement pst = conn.prepareStatement(sqlInsert);
    for(int i = 1; i <= 100; i++) {
      pst.setString(1, "string-" + i);
      pst.setInt(2, i);
      pst.setInt(3, i+1);
      pst.addBatch();
    }
    pst.executeBatch();
    stmt.execute(sqlDrop);
  }
  
  /*
   * 第一列作为hyperbase  rowkey
   * grant 'hive','RW'
   */
  public static void testHBaseBatchInsertWithoutStructRowKey() throws SQLException{
    Statement stmt = conn.createStatement();
    String sqlCreate =
        "create table if not exists batchinsertwithoutstructrowkey ( " +
            "c1 int," +
            "c2 int " +
            ") "+
            "stored by 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' ";
    String sqlDrop   = "drop table if exists batchinsertwithoutstructrowkey";
    String sqlInsert = "insert into batchinsertwithoutstructrowkey(c1, c2) values(?,?)";
    stmt.execute(sqlDrop);
    stmt.execute(sqlCreate);

    PreparedStatement pst = conn.prepareStatement(sqlInsert);
    for(int i = 1; i <= 100; i++) {
      for (int j = 1; j <= 2; j++) {
        pst.setInt(j, i);
      }
      pst.addBatch();
    }
    pst.executeBatch();
  }
  
}
