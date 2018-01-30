package hyperbase.elasticsearchdemo;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by lee on 2017-4-19.
 */
public class Es_client {
    public static String host = "172.16.1.33";
    public static int port = 9300;

    static Map<String, String> m = new HashMap<String, String>();
    // 设置client.transport.sniff为true来使客户端去嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中，
    static Settings settings =  Settings.settingsBuilder().put(m).put("cluster.name","elasticsearch1").put("client.transport.sniff", true).build();

    // 创建私有对象
    private static TransportClient client;

    static {
        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    // 取得实例
    public static synchronized TransportClient getTransportClient() {
        return client;
    }
}
