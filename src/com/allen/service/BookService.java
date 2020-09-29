package com.allen.service;

import com.allen.pojo.Book;
import com.allen.pojo.Page;

import java.util.List;

/**
 * @author Allen
 * @date 2020/9/20 11:12
 */
public interface BookService {
    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(Integer pageNo, Integer pageSize);

    Page<Book> pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max);
}
