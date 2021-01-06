package com.study.es.service;


import com.study.es.common.BaseQueryService;
import com.study.es.config.EsIndexConfig;
import com.study.es.dao.BookRepository;
import com.study.es.entity.Book;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author vimin
 * @since 2018-09-18
 */
@Service
public class BookService extends BaseQueryService<Book> {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Resource
    private BookRepository bookRepository;


    @Override
    public EsIndexConfig<Book> esIndexConfig() {
        return new EsIndexConfig<>(elasticsearchRestTemplate, Book.class);
    }

//    @Override
//    public BookBean findById(String id) {
//        //CrudRepository中的方法
//        Optional<BookBean> bookBeanOptional = bookRepository.findById(id);
//        return bookBeanOptional.orElse(null);
//    }

    public Book getById(String id) {
        return elasticsearchRestTemplate.get(id, Book.class);
    }

//    @Override
//    public boolean deleteIndex(String indexName) {
//        return elasticsearchRestTemplate.deleteIndex(indexName);
//    }


    public String save(Book bookBean) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(String.valueOf(bookBean.getBookId()))
                .withObject(bookBean)
                .build();
        return elasticsearchRestTemplate.index(indexQuery, esIndexConfig().getIndexCoordinates());
    }

    public Book findByBookName(String bookName) {
        Book book = bookRepository.findByBookName(bookName);
        return book;
    }

//    @Override
//    public BookBean findByBookName(String bookName) {
//        //CrudRepository中的方法
//        return bookRepository.findByBookName(bookName);
//    }
//
//    @Override
//    public Page<BookBean> search(String authName) {
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("bookTag", authName));
//        nativeSearchQueryBuilder.withPageable(PageRequest.of(0, 5));
//        Page<BookBean> bookBeanPage = bookRepository.search(nativeSearchQueryBuilder.build());
//
//        //termsQuery 相当于 in查询
//        //对string 的 in 查询
//        NativeSearchQueryBuilder nativeSearchQueryBuilder2 = new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder2.withQuery(QueryBuilders.termsQuery("author.name", authName));
//        nativeSearchQueryBuilder2.withPageable(PageRequest.of(0, 5));
//        Page<BookBean> bookBeanPage2 = bookRepository.search(nativeSearchQueryBuilder2.build());
//
//        //对 array 的 in 查询
//        NativeSearchQueryBuilder nativeSearchQueryBuilder3 = new NativeSearchQueryBuilder();
//        List<String> tags = new ArrayList<>();
//        tags.add(authName);
//        tags.add("人物");
//        nativeSearchQueryBuilder3.withQuery(QueryBuilders.termsQuery("bookTag", tags));
//        nativeSearchQueryBuilder3.withPageable(PageRequest.of(0, 5));
//        Page<BookBean> bookBeanPage3 = bookRepository.search(nativeSearchQueryBuilder3.build());
//
//        return bookBeanPage;
//    }

}
