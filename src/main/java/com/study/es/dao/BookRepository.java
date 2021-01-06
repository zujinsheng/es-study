package com.study.es.dao;

import com.study.es.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author zujs 2020-07-07
 */
@Component
public interface BookRepository extends ElasticsearchRepository<Book, String> {

    /**
     * Spring Data 的另一个强大功能，是根据方法名称自动实现功能。
     * 比如：你的方法名叫做：findByTitle，那么它就知道你是根据title查询，然后自动帮你完成，无需写实现类。
     * 当然，方法名称要符合一定的约定：https://www.cnblogs.com/ifme/p/12005026.html
     * @param bookName
     * @return
     */
    Book findByBookName(String bookName);

}