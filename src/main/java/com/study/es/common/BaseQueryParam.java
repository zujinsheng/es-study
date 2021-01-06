package com.study.es.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zujs
 */
public class BaseQueryParam {

    /**
     * current page from 0
     */
    private int pageIndex;

    /**
     * page size
     */
    private int pageSize = 10;

    /**
     * 排序
     */
    private List<EsSort> sorts;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<EsSort> getSorts() {
        return sorts;
    }

    public void setSorts(List<EsSort> sorts) {
        this.sorts = sorts;
    }

    public void addSort(EsSort esSort) {
        if (sorts == null) {
            sorts = new ArrayList<>();
        }
        sorts.add(esSort);
    }
}
