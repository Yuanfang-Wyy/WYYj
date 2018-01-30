//import org.apache.flume.Context;
//import org.apache.flume.EventDeliveryException;
//import org.apache.flume.PollableSource;
//import org.apache.flume.source.AbstractSource;
//import org.apache.hadoop.conf.Configuration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.ScheduledExecutorService;
//
///**
// * Created by weicheng.huang on 2017/3/31.
// */
//public class MyPollabeSource extends AbstractSource implements Configuration,PollableSource {
//    //输出日志
//    private static final Logger logger = LoggerFactory.getLogger(MyPollabeSource.class);
//    //本地文件路径
//    private String localPath;
//    //任务监控执行线程
//    private ScheduledExecutorService monitor;
//    //重复扫描次数
//    private int DELAY;
//    //
//    private long lastCheckTime = 0;
//    private boolean isRecurse = false;
//    private int batchSize = 100;
//
//    @Override
//    public void configuration(Context context){
//        this.localPath = context.getString("loocal.path");
//        this.DELAY = Integer.parseInt(context.getInteger("delay",10) + "");
//    }
//
//    @Override
//    public void start(){
//    }
//
//    @Override
//    public void stop(){
//    }
//
//    public Status process() throws EventDeliveryException {
//        Status status = null;
//        return null;
//    }
//}
