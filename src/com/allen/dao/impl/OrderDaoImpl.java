package com.allen.dao.impl;

import com.allen.dao.OrderDao;
import com.allen.pojo.Order;

/**
 * @author Allen
 * @date 2020/9/28 15:15
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
