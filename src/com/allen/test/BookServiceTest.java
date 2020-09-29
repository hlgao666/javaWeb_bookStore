package com.allen.test;

import com.allen.pojo.Book;
import com.allen.pojo.Page;
import com.allen.service.BookService;
import com.allen.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Allen
 * @date 2020/9/20 11:25
 */
public class BookServiceTest {

    BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"huyao is handsome","hailonggao",
                new BigDecimal(999), 111111, 0,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(20,"huyao is handsome","hailonggao",
                new BigDecimal(999), 111111, 0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        List<Book> bookList = bookService.queryBooks();
        for(Book book:bookList){
            System.out.println(book);
        }
    }

    @Test
    public void page(){

        System.out.println(bookService.page(1, Page.PAGE_SIZE));

    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 50));
    }
}