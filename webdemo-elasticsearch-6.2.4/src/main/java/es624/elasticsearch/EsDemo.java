package es624.elasticsearch;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class EsDemo {

    @Test
    public void test1() throws UnknownHostException, IOException {
        //指定es 集群
        Settings settings = Settings.builder().put("cluster.name", "my-application").build();

        //访问es 服务器的客户端
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.100.206.216"), 9300));

        //数据查询
        GetResponse response = client.prepareGet("myindex", "user", "1").execute().actionGet();

        //得到查询数据
        System.out.println(response.getSourceAsString());

        //关闭客户端
        client.close();
    }

}
