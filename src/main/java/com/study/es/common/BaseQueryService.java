package com.study.es.common;

import com.study.es.config.EsIndexConfig;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

/**
 * @author zujs
 */
public abstract class BaseQueryService<T> {

    private EsIndexConfig<T> esIndexConfig = null;

    private EsIndexConfig<T> localIndexConfig() {
        if (esIndexConfig == null) {
            synchronized (BaseQueryService.class) {
                if (esIndexConfig == null) {
                    esIndexConfig = esIndexConfig();
                }
            }
        }
        return esIndexConfig;
    }

    /**
     * es索引
     *
     * @return es索引
     */
    public abstract EsIndexConfig<T> esIndexConfig();

    public Page<T> query(BaseQueryParam param) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //查询参数
        BoolQueryBuilder mustQuery = EsUtils.getQueryBuilder(param);
        nativeSearchQueryBuilder.withQuery(mustQuery);

        //分页参数
        nativeSearchQueryBuilder.withPageable(PageRequest.of(param.getPageIndex(), param.getPageSize()));
        Page result = localIndexConfig().getElasticsearchTemplate().queryForPage(nativeSearchQueryBuilder.build(), localIndexConfig().getClazz(), IndexCoordinates.of());
        return result;
    }


}
