package com.study.es.controller;

import com.study.es.entity.Book;
import com.study.es.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

    @PostMapping("/batchSaveBook")
    public String batchSaveBook() {
        try {
            for (int i = 0; i < 100; i++) {
                Book bookBean = getDefaultBook();
                bookBean.setBookId("book" + i);
                bookBean.setBookName("book" + i);
                bookBean.setPrice(new BigDecimal("120.88"));
                String bookId = bookService.save(bookBean);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "ok";
    }

    private Book getDefaultBook() {
        Book bookBean = new Book();
        bookBean.setBookId("linux");
        bookBean.setBookName("Linux基础学习篇");
        List<String> tags = new ArrayList<>();
        tags.add("技术");
        tags.add("系统");
        bookBean.setPrice(new BigDecimal("120.88"));
        bookBean.setDescription("学习Linux好书籍，菜鸟必备");
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

    @GetMapping("/deleteIndex")
    public String deleteIndex() {
        try {
            boolean deleteIndex = bookService.deleteIndex("book");
            return deleteIndex ? "删除完成" : "删除失败";
        } catch (Exception ex) {
            log.error(ex.getStackTrace().toString());
        }
        return null;
    }
}
