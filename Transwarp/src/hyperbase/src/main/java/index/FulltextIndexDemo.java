//package index;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hyperbase.client.HyperbaseAdmin;
//import org.apache.hadoop.hyperbase.fulltextindex.metadata.Mapping;
//
///**
// * Created by lee on 2017-4-14.
// */
//public class FulltextIndexDemo {
//    public static String tableName = "ss";
//    public static  Configuration conf ;
//    public static void main(String[] args) {
//        try {
//            conf = new Configuration();
//            conf.addResource("core-site.xml");
//            conf.addResource("hbase-site.xml");
//            conf.addResource("yarn-site.xml");
//            conf.addResource("hdfs-site.xml");
//            conf.addResource("mapred-site.xml");
//            conf.set("mapreduce.framework.name", "yarn");
//            conf.set("mapreduce.app-submission.cross-platform", "true");
//            conf.set("mapred.remote.os", "Linux");
//            HyperbaseAdmin hyperbaseAdmin = new HyperbaseAdmin(conf);
//
//            Mapping mapping = new Mapping(tableName, true);
//
//            mapping.addField(new Mapping.Field("f", "q1").setOptField(Mapping.Field.TYPE, "string").setOptField(Mapping.Field.INDEX, "not_analyzed").setOptField(Mapping.Field.STORE, "true"));
//            mapping.addField(new Mapping.Field("f", "q2").setOptField(Mapping.Field.INDEX, "analyzed").setOptField(Mapping.Field.STORE, "false"));
//
//            //创建全文索引
////            hyperbaseAdmin.addFulltextIndex(TableName.valueOf(tableName), mapping);
////
////
////
////            //rebuild   full text  index
////            IndexRebuilder indexRebuilder = new IndexRebuilder(conf); //org.apache.hadoop.hyperbase.mapreduce.IndexRebuilder;
////            indexRebuilder.rebuildFulltextIndex(TableName.valueOf(tableName));
//
//
//                        //删除索引
//            hyperbaseAdmin.deleteFulltextIndex(TableName.valueOf(tableName));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//}
