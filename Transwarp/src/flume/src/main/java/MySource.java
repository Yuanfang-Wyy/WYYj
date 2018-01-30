package flume.src.main.java;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;

public class MySource extends AbstractSource implements Configurable, PollableSource {
    private String myProp;

    @Override
    public void configure(Context context) {
//        String myProp = context.getString("myProp", "defaultValue");

        // Process the myProp value (e.g. validation, convert to another type, ...)

        // Store myProp for later retrieval by process() method
//        this.myProp = myProp;
    }
    @Override
    public Status process() throws EventDeliveryException {
        try {
            while (true) {
                int max = 20;
                int min = 10;
                Random random = new Random();

                int s = random.nextInt(max) % (max - min + 1) + min;
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("id", Integer.toString(s));
                this.getChannelProcessor()
                        .processEvent(EventBuilder.withBody(Integer.toString(s), Charset.forName("UTF-8"), header));

                Thread.sleep(1000);
            }
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }
}