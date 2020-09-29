package com.allen.web;

import com.allen.dao.BookDao;
import com.allen.dao.impl.BookDaoImpl;
import com.allen.pojo.Book;
import com.allen.pojo.Cart;
import com.allen.pojo.CartItem;
import com.allen.service.BookService;
import com.allen.service.impl.BookServiceImpl;
import com.allen.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Allen
 * @date 2020/9/25 8:44
 */
public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();
    private BookDao bookDao = new BookDaoImpl();
//    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
//        Book book = bookService.queryBookById(id);
//        //判断book库存为空，则弹窗提示，并return
//        if(book.getStock()<=0){
//            resp.sendRedirect(req.getContextPath());
//            return;
//        }
//
//        CartItem cartItem = new CartItem(book.getId(),book.getName(),1, book.getPrice(),book.getPrice(), book.getStock());
//
//        Cart cart = (Cart)req.getSession().getAttribute("cart");
//        if(cart==null){
//            cart = new Cart();
//            req.getSession().setAttribute("cart",cart);
//        }
//
//        cart.addItem(cartItem);
//        //更新库存和销量
//        book.setSales(book.getSales() + cartItem.getCount());
//        book.setStock(book.getStock() - cartItem.getCount());
//        bookDao.updateBook(book);
//        req.getSession().setAttribute("lastName",cartItem.getName());
////        System.out.println("add to cart");
//        //重定向回原来商品界面
//        resp.sendRedirect(req.getHeader("Referer"));
//
//    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        //判断book库存为空，则弹窗提示，并return
        if(book.getStock()<=0){
            resp.sendRedirect(req.getContextPath());
            return;
        }

        CartItem cartItem = new CartItem(book.getId(),book.getName(),1, book.getPrice(),book.getPrice(), book.getStock());

        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        cart.addItem(cartItem);
        //更新库存和销量
        book.setSales(book.getSales() + cartItem.getCount());
        book.setStock(book.getStock() - cartItem.getCount());
        bookDao.updateBook(book);
        req.getSession().setAttribute("lastName",cartItem.getName());
//        System.out.println("add to cart");
        //重定向回原来商品界面
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String resultMapJson = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJson);

    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.deleteItem(id);
            //重定向回原来商品界面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
            //重定向回原来商品界面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),1);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        Book book = bookService.queryBookById(id);
        //若修改数量大于库存，则弹窗提示，并return
        if(count>book.getStock()){
            resp.sendRedirect(req.getHeader("Referer"));
            return;
        }
        cart.updateCount(id,count);
        //更新库存和销量
        book.setSales(book.getSales() + count);
        book.setStock(book.getStock() - count);
        bookDao.updateBook(book);
        //重定向回原来商品界面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
