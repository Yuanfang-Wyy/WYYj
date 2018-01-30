package hyperbase.elasticsearchdemo;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

/**
 * Created by lee on 2017-4-17.
 */
public class Esdemo {

    public static void main(String[] args)throws Exception{

//        Settings settings = Settings.settingsBuilder().put("cluster.name", "elasticsearch1").build();
//        Client client = TransportClient.builder().settings(settings).build()
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.16.1.33"), 9300))
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.16.1.34"), 9300))
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.16.1.35"), 9300));
        //集群节点IP

         BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.should(QueryBuilders.matchQuery("column1:cq1", "3"));
//            boolQueryBuilder.should(QueryBuilders.matchQuery("trade_type", "1"));

//            boolQueryBuilder.must(QueryBuilders.rangeQuery("trade_type").from("1").to("2"));
        QueryBuilders.queryStringQuery("");
        SearchResponse response = Es_client.getTransportClient().prepareSearch("elasticsearch_fulltext")//索引名称
        .setTypes("default")
        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
        .setQuery(boolQueryBuilder)             // Query
        .setFrom(0).setSize(10).setExplain(true)
//        .addSort("")
        .execute()
        .actionGet();


        SearchHits searchHits = response.getHits();
        System.out.println("total size : " + searchHits.getTotalHits());

        for (SearchHit hit:searchHits)
        {
            System.out.println(hit.getSourceAsString());
        }

    }


}
