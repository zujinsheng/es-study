package com.study.es.controller;

import com.study.es.entity.Book;
import com.study.es.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zujs
 */
@RestController
@RequestMapping("book")
@Slf4j(topic = "bookController")
public class BookController {

    @Resource
    private BookService bookService;


    @PostMapping("/save")
    public String save(@RequestBody Book book) {
        try {
            if (!StringUtils.hasText(book.getBookId())) {
                book = getDefaultBook();
            }
            String bookId = bookService.save(book);
            log.info(book.toString());
            return bookId;

        } catch (Exception ex) {
            System.out.println(ex);
            return "异常了";
        }
    }

    private Book getDefaultBook() {
        Book bookBean = new Book();
        bookBean.setBookId("linux");
        bookBean.setBookName("Linux基础学习篇");
        List<String> tags = new ArrayList<>();
        tags.add("技术");
        tags.add("系统");
        bookBean.setBookTag(tags);
        return bookBean;
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") String id) {
        try {
            Book bookBean = bookService.getById(id);
            log.info(bookBean.toString());
            return bookBean.toString();
        } catch (Exception ex) {
            log.error(ex.getStackTrace().toString());
        }
        return null;
    }

    @GetMapping("/findByBookName")
    public String findByBookName(@RequestParam("bookName") String bookName) {
        try {
            Book bookBean = bookService.findByBookName(bookName);
            log.info(bookBean.toString());
            return bookBean.toString();
        } catch (Exception ex) {
            log.error(ex.getStackTrace().toString());
        }
        return null;
    }

}