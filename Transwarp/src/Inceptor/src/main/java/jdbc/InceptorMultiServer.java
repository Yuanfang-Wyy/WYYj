package Inceptor.src.main.java.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import java.sql.*;

public class InceptorMultiServer {
    private static Log LOG = LogFactory.getLog(InceptorMultiServer.class);
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

    public InceptorMultiServer(String inceptorServerHost1,String inceptorServerHost2) {
    /* hiveserver2 jdbc url */
        this.url = "jdbc:hive2://" + inceptorServerHost1 + ":10000,"+ inceptorServerHost2 + ":10000/default";
        System.out.println(this.url);
    }

    /* ����Demo������Ҫʹ�õ��ı� */
    void createTable() {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url,"","");
//			conn = DriverManager.getConnection(url,"hive","123456");
            st = conn.createStatement();
            String sql1 = "drop table if exists acidTable";
            String sql2 = "drop table if exists partitionAcidTable";
            String sql3 = "drop table if exists range_int_table";
            String sql4 = "create table acidTable(name varchar(10),age int, degree int) clustered by (age) into 10 buckets stored as orc TBLProperties(\"transactional\"=\"true\")";
            String sql5 = "create table partitionAcidTable(name varchar(10),age int) partitioned by (city string) clustered by (age) into 10 buckets stored as orc TBLProperties(\"transactional\"=\"true\")";
            String sql6 = "create table range_int_table(id int, value int) partitioned by range (id) (partition less1 values less than (1), partition less10 values less than (10) ) clustered by (value) into 3 buckets stored as orc TBLProperties(\"transactional\"=\"true\")";
            st.execute(sql1);
            st.execute(sql2);
            st.execute(sql3);
            st.execute(sql4);
            st.execute(sql5);
            st.execute(sql6);

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeConnectionAndStatement(conn, st);
        }

    }

    /**
     * �ر�connection & statement
     *
     * @param connection
     * @param st
     */
    private void closeConnectionAndStatement(Connection conn, Statement st) {
        if (null != conn) {
            try {
                conn.close();
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
        String sql1 = "set transaction.type=inceptor";
        String sql2 = "Insert into acidTable(name, age,degree) values ('aaa', 12,23)";
        String sql3 = "Insert into partitionAcidTable partition(city='sh') (name, age)values ('aaa', 12)";
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
//			conn = DriverManager.getConnection(url,"hive","123456");
            st = conn.createStatement();
            st.execute(sql1);
            st.execute(sql2);
            st.execute(sql3);
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
        String sql1 = "set transaction.type=inceptor";
        String sql2 = "Batchinsert into  acidTable(name, age,degree) batchvalues(values ('aaa', 12,3), values ('bbb', 22,9))";
        String sql3 = "Batchinsert into  partitionAcidTable partition(city='sh') (name, age) batchvalues(values ('aaa', 12), values ('bbb', 22))";
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
//			conn = DriverManager.getConnection(url, "hive", "123456");
            st = conn.createStatement();
            st.execute(sql1);
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
//			conn = DriverManager.getConnection(url, "hive", "123456");
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
//			conn = DriverManager.getConnection(url, "hive", "123456");
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
     * ��ҳ��ѯ����ѯ��start����end����¼
     *
     * @param start
     *            �ӵ�start����ʼ����
     * @param end
     *            ��������end������
     */
    private void scan(long start, long end) {
        String sql = "select name,degree  from acidtable order by degree desc limit "
                + end + "; ";
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
//			conn = DriverManager.getConnection(url, "hive", "123456");
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            long i = 1;
            while (rs.next()) {
                if (i >= start) {
                    String name = rs.getString("name");
                    int degree = rs.getInt("degree");
                    System.out.println(name +"----------"+i+"----------"+ degree);
                }
                i++;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeConnectionAndStatement(conn, st);
        }

    }

    private void select() {
        String sql = "select * from default.acidtable";
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url,"","");
//			conn = DriverManager.getConnection(url, "hive", "123456");
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            long i = 1;
            while (rs.next()) {
                String name = rs.getString("name");
                int degree = rs.getInt("degree");
                System.out.println(name +"----------"+i+"----------"+ degree);
                i++;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeConnectionAndStatement(conn, st);
        }

    }

    public static void main(String[] args) throws SQLException {
        InceptorMultiServer iss = new InceptorMultiServer("172.16.140.21","172.16.140.23");  //inceptorServerHost1,inceptorServerHost2
        iss.createTable();
        iss.batchInsert();
        iss.update();
        iss.delete();
        iss.scan(1, 3);
        iss.select();
    }



}
