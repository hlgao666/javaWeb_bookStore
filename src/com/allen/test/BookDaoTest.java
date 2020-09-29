package com.allen.test;

import com.allen.dao.BookDao;
import com.allen.dao.impl.BookDaoImpl;
import com.allen.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Allen
 * @date 2020/9/20 10:58
 */
public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"huyao is handsome","hailonggao",
                new BigDecimal(999), 111111, 0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void update() {
        bookDao.updateBook(new Book(21,"hailong is handsome","hailonggao",
                new BigDecimal(999), 111111, 0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookDao.queryBooks());
    }

    @Test
    public void queryItemTotalByPrice() {
        System.out.println(bookDao.queryItemTotalByPrice(10,100));
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> items = bookDao.queryForPageItemsByPrice(4,4, 10, 100);
        for(Book item:items){
            System.out.println(item);
        }

    }
}