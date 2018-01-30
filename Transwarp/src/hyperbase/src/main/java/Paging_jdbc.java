package hyperbase.src.main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2017-4-21.
 */
public class Paging_jdbc {
    private static String inceptorDriver = "org.apache.hive.jdbc.HiveDriver";
	private static String inceptorUrl = "jdbc:hive2://172.16.2.90:10000";
	private Connection connection = null;
	private static Statement statement = null;
	public static List<Object> getPage(ResultSet resultSet , int pageNum, int pageSize){
		if(resultSet==null)
			return null;
        List<Object> objects = new ArrayList<Object>();
        if(statement==null)
        	return null;
        try {
//			statement.setMaxRows(pageNum*pageSize);
			resultSet.absolute(pageSize*pageNum);
			List<String> list = new ArrayList<String>(2);
		        while (resultSet.next() && objects.size() < pageSize){
		            objects.add(resultSet.getString(1));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return objects;
    }
	private void init(){
		try {
			Class.forName(inceptorDriver);
			connection = DriverManager.getConnection(inceptorUrl,"","");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void test(){
		ResultSet resultSet=null;
		String sql;
		//show tables;
	    try {
            long start = System.currentTimeMillis();
            System.out.println(start);
            statement = connection.createStatement();
            statement.execute ("set ngmr.exec.mode=local");
//			sql="select * from hbase_test.hbase_test where contains(md5pid, \"wildcard '*8E*'\") limit 20000";
			sql="select * from batchinsertwithoutstructrowkey where c1>'10'";
//			sql="select count(*) from hbase_test.hbase_test where rsapid='7' limit 2";
			resultSet = statement.executeQuery(sql);
			List<Object> list=getPage(resultSet,0,10);
			for(Object o:list)
			{
				System.out.println(o.toString());
			}

            System.out.println("first page " );
			list=getPage(resultSet,1,10);
			for(Object o:list)
			{
				;
				System.out.println(o.toString());
			}

            System.out.println("sencond page"  );
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
			list=getPage(resultSet,2,10);
			for(Object o:list)
			{
				System.out.println(o.toString());
			}

            System.out.println("third page"   );
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
			list=getPage(resultSet,4,10);
			for(Object o:list)
			{
				System.out.println(o.toString());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException {
		Paging_jdbc pj = new Paging_jdbc();
		pj.init();
		pj.test();
	}
}
