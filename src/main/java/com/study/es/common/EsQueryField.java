package com.study.es.common;

import java.lang.annotation.*;

/**
 * @author zujs
 * es 查询注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EsQueryField {

    String fieldName() default "";

    EsQueryType queryType() default EsQueryType.TermQuery;


}
