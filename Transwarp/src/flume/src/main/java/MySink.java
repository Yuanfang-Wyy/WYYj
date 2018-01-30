package flume.src.main.java;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MySink extends AbstractSink implements Configurable {
//    private String myProp;
    private static final Logger logger = LoggerFactory.getLogger(MySink.class);
    private static final String PRO_KEY_ROOTPATH = "fileName";
    private String fileName;
    @Override
    public void configure(Context context) {
//        String myProp = context.getString("myProp", "defaultValue");
        fileName = context.getString(PRO_KEY_ROOTPATH);
        // Process the myProp value (e.g. validation)

        // Store myProp for later retrieval by process() method
//        this.myProp = myProp;
    }

    @Override
    public void start() {
        // Initialize the connection to the external repository (e.g. HDFS) that
        // this Sink will forward Events to ..
    }

    @Override
    public void stop () {
        // Disconnect from the external respository and do any
        // additional cleanup (e.g. releasing resources or nulling-out
        // field values) ..
    }

    @Override
    public Status process() throws EventDeliveryException {
        Status status = null;

        // Start transaction
        Channel ch = getChannel();
        Transaction txn = ch.getTransaction();
        Event event = null;
        txn.begin();
        while (true){
            event = ch.take();
            if (event != null){
                break;
            }
        }
            logger.debug("Get event.");

            String body = new String(event.getBody());
            System.out.println("event.getBody()------" + body);

            String res = body + ":" + System.currentTimeMillis() + "\r\n";
            File file = new File(fileName);
            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(file,true);
                fos.write(res.getBytes());
                fos.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        txn.commit();
        txn.close();
        return status = Status.READY;
    }
}