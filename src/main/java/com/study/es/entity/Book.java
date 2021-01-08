package com.study.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
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
    @Field(type = FieldType.Keyword)
    private String bookName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 标签
     */
    private List<String> bookTag;

    /**
     * 描述
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String description;
}