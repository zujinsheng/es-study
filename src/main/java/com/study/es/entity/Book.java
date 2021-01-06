package com.study.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;


/**
 * @author zujs
 */
@Document(indexName = "book")
@Data
public class Book {
    /**
     * 主键
     */
    @Id
    private String bookId;

    /**
     * 名称
     */
    private String bookName;

    /**
     * 标签
     */
    private List<String> bookTag;
}