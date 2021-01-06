package com.study.es.common;

import org.elasticsearch.search.sort.SortOrder;

/**
 * @author zujs
 */
public class EsSort {

    /**
     * 排序字段
     */
    private String fieldName;

    /**
     * 排序方式
     */
    private SortOrder sortType;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public SortOrder getSortType() {
        return sortType;
    }

    public void setSortType(SortOrder sortType) {
        this.sortType = sortType;
    }

    public EsSort(String fieldName, SortOrder sortType) {
        this.fieldName = fieldName;
        this.sortType = sortType;
    }
}
