package com.allen.service.impl;

import com.allen.dao.BookDao;
import com.allen.dao.OrderDao;
import com.allen.dao.OrderItemDao;
import com.allen.dao.impl.BookDaoImpl;
import com.allen.dao.impl.OrderDaoImpl;
import com.allen.dao.impl.OrderItemDaoImpl;
import com.allen.pojo.*;
import com.allen.service.OrderService;
import com.allen.utils.Date2Str;

import java.util.Map;

/**
 * @author Allen
 * @date 2020/9/28 20:15
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {

        String orderId = System.currentTimeMillis()+""+userId;

        Order order = new Order(orderId, Date2Str.getTimeNow(), cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

        for(Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();

            OrderItem orderItem = new OrderItem(null,cartItem.getName(),
                    cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);

            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);

        }

        cart.clear();

        return orderId;
    }
}
