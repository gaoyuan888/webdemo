package yyz.elastic.elasticsearch;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class Test {

    private static String host="192.168.5.128"; // 服务器地址
    private static int port=9300; // 端口

    public static void main(String[] args) throws Exception{
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(Test.host), Test.port));
        System.out.println(client);
        client.close();
    }
}
