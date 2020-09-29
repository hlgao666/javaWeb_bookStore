package com.allen.dao;

import com.allen.pojo.Book;

import java.util.List;

/**
 * @author Allen
 * @date 2020/9/20 10:28
 */
public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Integer queryItemTotal();

    List<Book> queryForPageItems(int begin, Integer pageSize);

    Integer queryItemTotalByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, Integer pageSize, Integer min, Integer max);
}
