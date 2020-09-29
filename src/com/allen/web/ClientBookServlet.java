package com.allen.web;

import com.allen.pojo.Book;
import com.allen.pojo.Page;
import com.allen.service.BookService;
import com.allen.service.impl.BookServiceImpl;
import com.allen.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Allen
 * @date 2020/9/22 22:45
 */
public class ClientBookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //        1、获取请求的参数pageNo和pageSize
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //        2、调用BookService.page(pageNo, pageSize)-->Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("client/clientBookServlet?action=page");

        //          3.保存到request域中
        req.setAttribute("page",page);
        //        4、跳到图书列表页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //        1、获取请求的参数pageNo和pageSize
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Integer min = WebUtils.parseInt(req.getParameter("min"),0);
        Integer max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //        2、调用BookService.page(pageNo, pageSize)-->Page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder sb = new StringBuilder("client/clientBookServlet?action=pageByPrice");
        if (req.getParameter("min")!=null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        //          3.保存到request域中
        req.setAttribute("page",page);
        //        4、跳到图书列表页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
