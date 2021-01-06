package com.study.es.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zujs
 * es集群配置
 */
@Configuration
public class EsRestClientConfig {

    @Value("${elasticsearch.host:}")
    private String elasticsearchServerHost;
    @Value("${elasticsearch.port:9200}")
    private Integer elasticsearchServerPort;
    /**
     * es ip port config
     *
     * @return
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(elasticsearchServerHost, elasticsearchServerPort, "http")));
    }
}
