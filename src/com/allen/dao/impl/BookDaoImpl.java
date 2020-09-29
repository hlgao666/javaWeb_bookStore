package com.allen.dao.impl;

import com.allen.dao.BookDao;
import com.allen.pojo.Book;

import java.util.List;

/**
 * @author Allen
 * @date 2020/9/20 10:33
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`)values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "Update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`, `name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book where id=?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryItemTotal() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, Integer pageSize) {
        String sql = "select  `id`, `name`,`author`,`price`,`sales`,`stock`,`img_path` as imgPath from t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryItemTotalByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, Integer pageSize, Integer min, Integer max) {
        String sql = "select  `id`, `name`,`author`,`price`,`sales`,`stock`,`img_path` as imgPath from t_book where price between ? and ? order by price limit ?,? ";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }

}
