package com.allen.test;

import com.allen.dao.OrderDao;
import com.allen.dao.impl.OrderDaoImpl;
import com.allen.pojo.Order;
import com.allen.utils.Date2Str;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Allen
 * @date 2020/9/28 15:31
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        Order order = new Order("1234567892", Date2Str.getTimeNow(),new BigDecimal(190),0,10);
        orderDao.saveOrder(order);
    }
}