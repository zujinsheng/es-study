package com.study.es.config;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

/**
 * @author zujs
 */
public class EsIndexConfig<T> {
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    private Class<T> clazz;

    public EsIndexConfig(ElasticsearchRestTemplate elasticsearchTemplate, Class<T> clazz) {
        this.elasticsearchRestTemplate = elasticsearchTemplate;
        this.clazz = clazz;
    }

    public ElasticsearchRestTemplate getElasticsearchTemplate() {
        return this.elasticsearchRestTemplate;
    }


    public Class<T> getClazz() {
        return this.clazz;
    }

    public IndexCoordinates getIndexCoordinates() {
        Class clazz = this.clazz;
//        Assert.isTrue(clazz.isAnnotationPresent(Document.class), "");
        Document documentAnnotation = (Document) clazz.getAnnotation(Document.class);
        return IndexCoordinates.of(documentAnnotation.indexName());
    }

}
