package es624.elasticsearch;

import com.google.gson.JsonObject;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ElasticSearch客户端连接服务器测试
 *
 * @author Administrator
 */
public class EsCrudDemo {

    private static String host = "47.100.206.216"; // 服务器地址
    private static int port = 9300; // 端口
    private TransportClient client = null;

    /**
     * 获取连接
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "resource"})
    @Before
    public void getCient() throws Exception {
        //指定es 集群
        Settings settings = Settings.builder().put("cluster.name", "my-application").build();

        //访问es 服务器的客户端
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("47.100.206.216"), 9300));
    }

    /**
     * 关闭连接
     *
     * @param
     */
    @After
    public void close() {
        if (client != null) {
            client.close();
        }
    }

    /**
     * 添加索引
     */
    @Test
    public void testIndex() throws Exception {
        IndexResponse response = client.prepareIndex("twitter", "tweet", "2")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("age", 12)
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();
        System.out.println("索引名称：" + response.getIndex());
        System.out.println("类型：" + response.getType());
        System.out.println("文档ID：" + response.getId()); // 第一次使用是1
        System.out.println("当前实例状态：" + response.status());
    }

    /**
     * 添加索引
     */
    @Test
    public void testIndex2() throws Exception {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        IndexResponse response = client.prepareIndex("weibo", "tweet")
                .setSource(json, XContentType.JSON)
                .get();
        System.out.println("索引名称：" + response.getIndex());
        System.out.println("类型：" + response.getType());
        System.out.println("文档ID：" + response.getId()); // 第一次使用是1
        System.out.println("当前实例状态：" + response.status());
    }

    /**
     * 添加索引
     */
    @Test
    public void testIndex3() throws Exception {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out Elasticsearch");

        IndexResponse response = client.prepareIndex("qq", "tweet")
                .setSource(json)
                .get();
        System.out.println("索引名称：" + response.getIndex());
        System.out.println("类型：" + response.getType());
        System.out.println("文档ID：" + response.getId()); // 第一次使用是1
        System.out.println("当前实例状态：" + response.status());
    }

    /**
     * 添加索引
     */
    @Test
    public void testIndex4() throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user", "kimchy");
        jsonObject.addProperty("postDate", "1989-11-11");
        jsonObject.addProperty("message", "trying out Elasticsearch");

        IndexResponse response = client.prepareIndex("qq", "tweet")
                .setSource(jsonObject.toString(), XContentType.JSON)
                .get();
        System.out.println("索引名称：" + response.getIndex());
        System.out.println("类型：" + response.getType());
        System.out.println("文档ID：" + response.getId()); // 第一次使用是1
        System.out.println("当前实例状态：" + response.status());
    }

    /**
     * ElasticSearch提供了根据索引名称，类别，文档ID来修改数据，修改的设置数据可以是Map，Json串，自带工具。我们实际开发一般用Json；
     */
    @Test
    public void testUpdate() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user", "锋哥");
        jsonObject.addProperty("postDate", "1989-11-11");
        jsonObject.addProperty("message", "学习Elasticsearch");

        UpdateResponse response = client.prepareUpdate("twitter", "tweet", "1").setDoc(jsonObject.toString(), XContentType.JSON).get();
        System.out.println("索引名称：" + response.getIndex());
        System.out.println("类型：" + response.getType());
        System.out.println("文档ID：" + response.getId()); // 第一次使用是1
        System.out.println("当前实例状态：" + response.status());
    }


    @Test
    public void testUpdate2() throws Exception {
        UpdateRequest request = new UpdateRequest();
        request.index("twitter").type("tweet").id("1").doc(
                XContentFactory.jsonBuilder().startObject().field("user", "sss").endObject()
        );
        UpdateResponse response = client.update(request).get();
        System.out.println(response.status());
    }

    @Test
    public void testUpInseart() throws Exception {
        IndexRequest request1 = new IndexRequest("twitter", "tweet", "2")
                .source(
                        XContentFactory.jsonBuilder().startObject()
                                .field("user", "dds")
                                .field("postDate", "2018-09-09").endObject()
                );

        UpdateRequest request = new UpdateRequest("twitter", "tweet", "2")
                .doc(
                        XContentFactory.jsonBuilder().startObject()
                                .field("user", "ddds")
                                .endObject()
                ).upsert(request1);

        UpdateResponse response = client.update(request).get();
        System.out.println(response.status());
    }

    /**
     * ElasticSearch提供了根据索引名称，类别，文档ID来删除数据
     */
    @Test
    public void testDelete() {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1").get();
        System.out.println("索引名称：" + response.getIndex());
        System.out.println("类型：" + response.getType());
        System.out.println("文档ID：" + response.getId()); // 第一次使用是1
        System.out.println("当前实例状态：" + response.status());
    }

    /**
     * 批量查詢
     */

    @Test
    public void testMget() {
        MultiGetResponse response = client.prepareMultiGet()
                .add("twitter", "tweet", "1")
                .add("twitter", "tweet", "2")
                .get();
        for (MultiGetItemResponse item : response) {
            GetResponse s = item.getResponse();
            if (s != null && s.isExists()) {
                System.out.println(s.getSourceAsString());
            }
        }
    }

    /**
     * bulk批量增刪改
     */

    @Test
    public void testBulk() throws Exception {
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        bulkRequestBuilder.add(
                client.prepareIndex("twitter", "tweet", "1")
                        .setSource(
                                XContentFactory.jsonBuilder()
                                        .startObject()
                                        .field("user", "zhangsan")
                                        .field("postDate", new Date())
                                        .field("age", 12)
                                        .field("interest","changge tiaowu hejiu chouyan")
                                        .field("message", "trying out Elasticsearch")
                                        .endObject()
                        )
        );
        bulkRequestBuilder.add(
                client.prepareIndex("twitter", "tweet", "2")
                        .setSource(
                                XContentFactory.jsonBuilder()
                                        .startObject()
                                        .field("user", "lisi")
                                        .field("postDate", "2010-02-02")
                                        .field("age", 13)
                                        .field("interest","changge tiaowu biancheng hejiu chouyan")
                                        .field("message", "trying out Elasticsearch")
                                        .endObject()
                        )
        );
        bulkRequestBuilder.add(
                client.prepareIndex("twitter", "tweet", "3")
                        .setSource(
                                XContentFactory.jsonBuilder()
                                        .startObject()
                                        .field("user", "wangwu")
                                        .field("postDate", "2000-02-02")
                                        .field("age", 14)
                                        .field("interest","changge tiaowu shuijiao hejiu chouyan")
                                        .field("message", "trying out Elasticsearch")
                                        .endObject()
                        )
        );

        bulkRequestBuilder.add(
                client.prepareIndex("twitter", "tweet", "4")
                        .setSource(
                                XContentFactory.jsonBuilder()
                                        .startObject()
                                        .field("user", "zhaoliu")
                                        .field("postDate","1990-02-02")
                                        .field("age", 15)
                                        .field("interest","changge tiaowu dajia hejiu chouyan")
                                        .field("message", "trying out Elasticsearch")
                                        .endObject()
                        )
        );
        BulkResponse responses = bulkRequestBuilder.get();
        System.out.println(responses.status());
    }

    /**
     * 查找
     */
    @Test
    public void testGet() {
        GetResponse getResponse = client.prepareGet("twitter", "tweet", "2").get();
        System.out.println(getResponse.getSourceAsString());
    }

    /**
     * 条件查询相关
     */
    @Test
    public void testQuery() {

        //term查询
//        QueryBuilder builder=QueryBuilders.termQuery("user","123s");

        //terms查询
//        QueryBuilder builder=QueryBuilders.termsQuery("user","123s","s");

        //multiMatch查询
//        QueryBuilder builder=QueryBuilders.multiMatchQuery("123s","user");

        //match查询
//        QueryBuilder builder = QueryBuilders.matchQuery("user", "123s");

        //range查询
//        QueryBuilder builder=QueryBuilders.rangeQuery("postDate").from("2017-01-01").to("2019-02-02").format("yyyy-MM-dd");

        //prefix查询
//        QueryBuilder builder=QueryBuilders.prefixQuery("user","123");

        //wildcard查询
//        QueryBuilder builder=QueryBuilders.wildcardQuery("user","123*");

        //fuzzy查询
//        QueryBuilder builder=QueryBuilders.fuzzyQuery("user","kchy");

        //type查询---
//        QueryBuilder builder=QueryBuilders.typeQuery("tweet");

        //ids查询---
//        QueryBuilder builder = QueryBuilders.idsQuery().addIds("1", "3");

        // commonTerms查询
//        QueryBuilder builder=QueryBuilders.commonTermsQuery("user","kimchy");

        // queryString查询----有唱歌但是没有打架
//        QueryBuilder builder=QueryBuilders.queryStringQuery("+hejiu -dajia");

        //constantScore查询--不计算相关度分数
        QueryBuilder builder=QueryBuilders.constantScoreQuery(QueryBuilders.termQuery("user","zhaoliu"));


        // bool组合查询
//          QueryBuilder builder=QueryBuilders.boolQuery()
//                .must(QueryBuilders.matchQuery("interest","changge"))
//                .mustNot(QueryBuilders.matchQuery("interest","biancheng"))
//                .should(QueryBuilders.matchQuery("user","zhangsan"))
//                .filter(QueryBuilders.rangeQuery("postDate").gte("2001-01-01").format("yyyy-MM-dd"));


        SearchResponse response = client.prepareSearch("twitter")
                .setQuery(builder)
                .setSize(3)
                .get();

        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> map = hit.getSourceAsMap();
            for (String key : map.keySet()) {
                System.out.println(key + ":" + map.get(key));
            }
            System.out.println("==============*==============");
        }
    }

    /**
     * 聚合查询相关
     */

    @Test
    public void testAggre() {
        //获取最大值
//        AggregationBuilder agg= AggregationBuilders.max("aggMax").field("age");
//        SearchResponse response=client.prepareSearch("twitter").addAggregation(agg).get();
//        Max max=response.getAggregations().get("aggMax");
//        System.out.println(max.getValue());

        //获取最小值
//        AggregationBuilder agg = AggregationBuilders.min("aggMin").field("age");
//        SearchResponse response = client.prepareSearch("twitter").addAggregation(agg).get();
//        Min min = response.getAggregations().get("aggMin");
//        System.out.println(min.getValue());

        //获取平均值
//        AggregationBuilder agg = AggregationBuilders.avg("aggAvg").field("age");
//        SearchResponse response = client.prepareSearch("twitter").addAggregation(agg).get();
//        Avg avg = response.getAggregations().get("aggAvg");
//        System.out.println(avg.getValue());

        //获取和
//        AggregationBuilder agg = AggregationBuilders.sum("aggSum").field("age");
//        SearchResponse response = client.prepareSearch("twitter").addAggregation(agg).get();
//        Sum sum = response.getAggregations().get("aggSum");
//        System.out.println(sum.getValue());

        //获取和
        AggregationBuilder agg = AggregationBuilders.cardinality("aggCard").field("age");
        SearchResponse response = client.prepareSearch("twitter").addAggregation(agg).get();
        Cardinality card = response.getAggregations().get("aggCard");
        System.out.println(card.getValue());

    }

}
