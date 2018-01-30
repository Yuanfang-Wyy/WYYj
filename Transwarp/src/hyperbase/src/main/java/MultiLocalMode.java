package hyperbase.src.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MultiLocalMode {
  
  private static List<Worker> threads = new ArrayList<Worker>();
  private static String hive2DriverName = "org.apache.hive.jdbc.HiveDriver";
  private static int port = 10000;
  private static String userName = "";
  private static String passWord = "";
  private static String sqlString = "select * from batchinsertwithoutstructrowkey";
  private static int threadnum= 3;
  private static String[] hosts = {"172.16.2.90","172.16.2.91","172.16.2.92"};
  private static String[] hostNameStrings={"transwarp-1","transwarp-2","transwarp-3"};
  
  public static void main(String[] args) {
    //SqlConstants
    
    for (int i=0; i<threadnum; ++i) {
      threads.add(new Worker(hosts[i % hosts.length], port,hostNameStrings[i%hostNameStrings.length]));
    }
    
    for (Worker w : threads) {
      w.start();
    }
  }
  
  static class Worker extends Thread {
    Connection connector = null;
    
    public boolean stopped = true;
    
    public Worker(String host, int port,String hname) {
      try {
        Class.forName(hive2DriverName);
        connector = DriverManager.getConnection("jdbc:hive2://" + host + ":" + port + "/default", userName, passWord);

      } catch (Exception e) {
        e.printStackTrace();
      }
      
      System.err.println("jdbc connect succ.");
      this.stopped = false;
    }
    
    public void run() {
      Statement stat = null;
      
      try {
        stat = connector.createStatement();
        stat.setFetchSize(1);
        String condition = "set ngmr.exec.mode=local;set ngmr.metacache=true";
        String[] conditions = condition.split(";");
        for (String s : conditions) {
          stat.execute(s);
        }
      } catch (SQLException e) {
        e.printStackTrace();
        return;
      }
      
      while (!stopped) {
        long pt = System.currentTimeMillis();
        
        try {
          stat.execute(sqlString);
          stat.getResultSet().next();
        } catch (SQLException e) {
          e.printStackTrace();
        }
        long et = System.currentTimeMillis();
        System.out.println(et-pt);
      }
      try {
        stat.close();
        connector.close();
        System.err.println("jdbc close succ");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
