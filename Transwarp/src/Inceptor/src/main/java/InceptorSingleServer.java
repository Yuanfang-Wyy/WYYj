package Inceptor.src.main.java;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;

public class InceptorSingleServer {
    private static Log LOG = LogFactory.getLog(InceptorSingleServer.class);
    /* hive server2 driver */
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private String url;
    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
            System.exit(1);
        }
    }

    public InceptorSingleServer(String inceptorServerHost) {

     /* Hive2 JDBC URL with kerberos certification */
   /* this.url = "jdbc:hive2://" + inceptorServerHost + ":10000/default;principal=hive/tdh-17@TDH;"+
    "authentication=kerberos;"+
    "kuser=hive;"+
    "keytab="+InceptorSingleServer.class.getClassLoader().getResource("hive.keytab").getPath()+";"+
    "krb5conf="+InceptorSingleServer.class.getClassLoader().getResource("krb5.conf").getPath()+"";*/

    /* hiveserver2 JDBC URL with LDAP certification */
        //this.url = "jdbc:hive2://" + inceptorServerHost + ":10000/default";

    /* hiveserver2 jdbc url */
        this.url = "jdbc:hive2://" + inceptorServerHost + ":10000/default";

        System.out.println(this.url);
    }

    /* ����Demo������Ҫʹ�õ��ı� */
    void createTable() {
        Connection connection = null;
        Statement st = null;
        try {
            connection = DriverManager.getConnection(url,"","");
            st = connection.createStatement();
            String sql1 = "drop table if exists acidTable";
            String sql2 = "drop table if exists partitionAcidTable";
            String sql3 = "drop table if exists range_int_table";
            String sql7=  "create table user(id int,name varchar(10)) clustered by (id) into 10 buckets stored as orc TBLProperties(\"transactional\"=\"true\")";
            String sql4 = "create table acidTable(name varchar(10),age int, degree int) clustered by (age) into 10 buckets stored as orc TBLProperties(\"transactional\"=\"true\")";
            String sql5 = "create table partitionAcidTable(name varchar(10),age int) partitioned by (city string) clustered by (age) into 10 buckets stored as orc TBLProperties(\"transactional\"=\"true\")";
            String sql6 = "create table range_int_table(id int, value int) partitioned by range (id) (partition less1 values less than (1), partition less10 values less than (10) ) clustered by (value) into 3 buckets stored as orc TBLProperties(\"transactional\"=\"true\")";
            st.execute(sql1);
            st.execute(sql2);
            st.execute(sql3);
            st.execute(sql4);
            st.execute(sql5);
            st.execute(sql6);
            st.execute(sql7);

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeConnectionAndStatement(connection, st);
        }

    }

    /**
     * �ر�connection & statement
     *
     * @param connection
     * @param st
     */
    private void closeConnectionAndStatement(Connection connection, Statement st) {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error("connection close error", e);
            }
        }
        if (null != st) {
            try {
                st.close();
            } catch (SQLException e) {
                LOG.error("statement close error", e);
            }
        }
    }

    /**
     * ��������
     */
    void insert() {
        String sql1 = "Insert into acidTable(name, age,degree) values ('aaa1', 12,23)";
        String sql2 = "Insert into acidTable(name, age,degree) values ('aaa2', 13,24)";
        String sql3 = "Insert into acidTable(name, age,degree) values ('aaa3', 14,25)";
        String sql4 = "Insert into acidTable(name, age,degree) values ('aaa4', 15,26)";
        String sql5 = "Insert into partitionAcidTable partition(city='sh') (name, age)values ('aaa', 12)";
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
            st = conn.createStatement();
            st.execute(sql1);
            st.execute(sql2);
            st.execute(sql3);
            st.execute(sql4);
            st.execute(sql5);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeConnectionAndStatement(conn, st);
        }
    }

    /**
     * ��������acidTable��partitionAcidTable
     */
    void batchInsert() {
        String sql2 = "Batchinsert into  acidTable(name, age,degree) batchvalues(values ('aaa', 12,3), values ('bbb', 22,9))";
        String sql3 = "Batchinsert into  partitionAcidTable partition(city='sh') (name, age) batchvalues(values ('aaa', 12), values ('bbb', 22))";
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
            st = conn.createStatement();
            st.execute(sql2);
            st.execute(sql3);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeConnectionAndStatement(conn, st);
        }
    }

    /**
     * ��acidTable����age>15�ļ�¼��degree�ֶθ���Ϊdegree*2
     */
    void update() {
        String sql1 = "set transaction.type=inceptor";
        String sql2 = "update acidTable set degree=degree*2 where age>15";
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
            st = conn.createStatement();
            st.execute(sql1);
            st.execute(sql2);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeConnectionAndStatement(conn, st);
        }
    }

    /**
     * ɾ��acidTable����name='bbb'�ļ�¼
     */
    void delete() {
        String sql1 = "set transaction.type=inceptor";
        String sql2 = "delete from acidTable where name='bbb'";
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
            st = conn.createStatement();

            st.execute(sql1);
            st.execute(sql2);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeConnectionAndStatement(conn, st);
        }
    }

    /**
     * ��ҳ��ѯ
     *
     * @param
     *            ��Ҫ��ҳ�Ľ����
     * @param pageNum
     *            ��ҳ��Ŀ
     * @param pageSize
     *            ��ҳ��С
     * @throws SQLException
     */
    public ArrayList<Object> getPage(String sql, int pageNum, int pageSize)
            throws SQLException {
        Connection conn = DriverManager.getConnection(url, "", "");
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        statement.setMaxRows(pageNum * pageSize);
        ArrayList<Object> objects = new ArrayList<Object>();
        resultSet.absolute(pageNum * pageSize);
        while (resultSet.next() && objects.size() < pageSize) {
            objects.add(resultSet.getString(1)+" "+resultSet.getInt(2)+" "+resultSet.getInt(3));
        }
        return objects;
    }

    private void select() {
        String sql = "select * from acidtable";
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                int age=rs.getInt("age");
                int degree = rs.getInt("degree");
                System.out.println(name +"---"+age+"---"+ degree);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeConnectionAndStatement(conn, st);
        }

    }

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        InceptorSingleServer iss = new InceptorSingleServer("172.16.2.90");  //inceptorServerHost
//		iss.createTable();
//		iss.batchInsert();
//        iss.update();
//        iss.delete();
//        iss.insert();
//        String sql="select * from acidtable";
//        ArrayList<Object> resultlist=iss.getPage(sql, 1, 2);
//        for(int i=0;i<resultlist.size();i++){
//            System.out.println(resultlist.get(i));
//        }
        iss.select();
    }



}
