package flume.src.main.java;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySource2 extends AbstractSource implements Configurable,PollableSource{

    @Override
    public void configure(Context context) {

    }

    @Override
    public Status process() throws EventDeliveryException {
        Status status = null;
        final List<Event> events = new ArrayList<Event>();
        HashMap<String, String> header = new HashMap<String, String>();
        long StartTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - StartTime < 1 * 5 * 1000){
            long timex = System.currentTimeMillis() - StartTime;
            System.out.println(System.currentTimeMillis() + "   " + StartTime + "   " + timex);
            for(int i = 0; i < 3; i++) {
                events.add(EventBuilder.withBody(Integer.toString(i), Charset.forName("UTF-8"),header));
            }
            getChannelProcessor().processEventBatch(events);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            status = Status.BACKOFF;
        }
        return status;
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
