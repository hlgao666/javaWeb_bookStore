package com.allen.service.impl;

import com.allen.dao.BookDao;
import com.allen.dao.impl.BookDaoImpl;
import com.allen.pojo.Book;
import com.allen.pojo.Page;
import com.allen.service.BookService;
import java.util.List;

/**
 * @author Allen
 * @date 2020/9/20 11:15
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }


    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);
        //求总记录数
        Integer itemTotal = bookDao.queryItemTotal();
        page.setItemTotal(itemTotal);
        //求总页码
        Integer pageTotal = itemTotal % pageSize>0?(itemTotal/pageSize+1):itemTotal/pageSize;
        //设置总页码
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);

        int begin = (pageNo-1)*pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max) {
        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);
        //求总记录数
        Integer itemTotal = bookDao.queryItemTotalByPrice(min, max);
        page.setItemTotal(itemTotal);
        //求总页码
        Integer pageTotal = itemTotal % pageSize>0?(itemTotal/pageSize+1):itemTotal/pageSize;
        //设置总页码
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);

        int begin = (pageNo-1)*pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        //设置当前页数据
        page.setItems(items);

        return page;
    }
}
