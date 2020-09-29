package com.allen.web;

import com.allen.pojo.Cart;
import com.allen.pojo.User;
import com.allen.service.OrderService;
import com.allen.service.impl.OrderServiceImpl;
import com.allen.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Allen
 * @date 2020/9/28 21:07
 */
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        User loginUser = (User) req.getSession().getAttribute("user");

        if(loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;     //调回登录界面后，往下代码不需要执行，因此返回
        }

        Integer userId = loginUser.getId();
        int i = 12/0;
        String orderId =  orderService.createOrder(cart,userId);
        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

    }
}
