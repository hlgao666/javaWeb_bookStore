package com.allen.service;

import com.allen.pojo.Cart;

/**
 * @author Allen
 * @date 2020/9/28 20:13
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);

}
