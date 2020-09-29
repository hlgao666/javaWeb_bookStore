package com.allen.test;

import com.allen.dao.OrderItemDao;
import com.allen.dao.impl.OrderItemDaoImpl;
import com.allen.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Allen
 * @date 2020/9/28 20:05
 */
public class OrderItemDaoTest {
    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null, "怎样拐跑别人的媳妇", 1,
                new BigDecimal(68), new BigDecimal(68), "111111"));

    }
}