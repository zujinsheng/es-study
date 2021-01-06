package com.study.es.common;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.lang.reflect.Field;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @author zujs
 */
public class EsUtils {

    /**
     * 构造es查询条件
     */
    public static BoolQueryBuilder getQueryBuilder(Object object) {
        BoolQueryBuilder mustQuery = QueryBuilders.boolQuery();
        mustQuery.must(matchAllQuery());

        Class clazz = object.getClass();
        // 获取所有字段
        for (Field f : clazz.getDeclaredFields()) {
            // 判断这个字段是否有EsQueryField注解
            if (f.isAnnotationPresent(EsQueryField.class)) {
                EsQueryField annotation = f.getAnnotation(EsQueryField.class);
                String esFieldName = annotation.fieldName().length() > 0 ? annotation.fieldName() : f.getName();
                EsQueryType queryType = annotation.queryType();
                //数据类型
                Object fieldValue = getFieldValue(object, clazz, f.getName());
                if (fieldValue != null) {
                    switch (queryType) {
                        case TermQuery:
                            mustQuery.must(QueryBuilders.termQuery(esFieldName, fieldValue));
                            break;
                        case TermsQuery:
                            if (fieldValue.getClass().getTypeName().endsWith("List")) {
                                //这个地方如果不强制转换， 会出现异常.  转化而成的es语法对象里面出现数组里面包含数组的异常。
                                mustQuery.must(QueryBuilders.termsQuery(esFieldName, (List<Object>) fieldValue));
                            }
                            break;
                        case MatchQuery:
                            mustQuery.must(QueryBuilders.matchQuery(esFieldName, fieldValue));
                            break;
                        case WildcardQuery:
                            mustQuery.must(QueryBuilders.wildcardQuery(esFieldName, fieldValue.toString()));
                            break;
                        case RangeQueryFrom:
                            mustQuery.must(QueryBuilders.rangeQuery(esFieldName).from(fieldValue));
                            break;
                        case RangeQueryTo:
                            mustQuery.must(QueryBuilders.rangeQuery(esFieldName).to(fieldValue));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return mustQuery;
    }


    /**
     * 反射获取对象的字段值
     */
    private static Object getFieldValue(Object object, Class clazz, String fieldName) {
        try {
            Field[] fs = clazz.getDeclaredFields();
            for (Field f : fs) {

                //设置些属性是可以访问的
                f.setAccessible(true);
                //得到此属性的值
                if (f.getName().equals(fieldName)) {
                    return f.get(object);
                }
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
}
