package com.allen.web;

import com.allen.pojo.Book;
import com.allen.pojo.Page;
import com.allen.service.BookService;
import com.allen.service.impl.BookServiceImpl;
import com.allen.utils.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Allen
 * @date 2020/9/20 14:58
 */
public class BookServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //        1、获取请求的参数pageNo和pageSize
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //        2、调用BookService.page(pageNo, pageSize)-->Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("manager/bookServlet?action=page");

        //          3.保存到request域中
        req.setAttribute("page",page);
        //        4、跳到图书列表页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //        1、获取请求的参数==封装成为Book对象
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo +=1;
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //        2、调用BookService.addBook()保存图书
        bookService.addBook(book);
        //        3、跳到图书列表页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //        1、获取请求的参数id，图书编程
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        //        2、调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);
        //        3、重定向回图书列表管理页面
        int pageTotal = WebUtils.parseInt(req.getParameter("pageTotal"), 0);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        int itemTotal = WebUtils.parseInt(req.getParameter("itemTotal"),0);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),0);
        if((itemTotal-1)%pageSize==0)
            pageTotal-=1;
        if(pageNo>pageTotal)
            pageNo = pageTotal;
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //1.获取请求参数，封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2.调用bookService.updateBook()方法，保存到数据库
        bookService.updateBook(book);
        //3.重定向到/工程路径/pages/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //1.获取请求的图书编号
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        //3.保存到request域
        req.setAttribute("book",book);
        //4.请求转发到，pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        //1.通过bookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到request域中
        req.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
