package com.allen.dao.impl;

import com.allen.dao.OrderItemDao;
import com.allen.pojo.OrderItem;

/**
 * @author Allen
 * @date 2020/9/28 15:27
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?);";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),
                orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
