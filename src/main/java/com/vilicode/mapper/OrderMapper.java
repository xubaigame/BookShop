package com.vilicode.mapper;

import com.vilicode.bean.Order;
import com.vilicode.bean.OrderItem;

import java.util.List;

public interface OrderMapper {
    public void addOrder(Order order);
    public void addOrderItem(OrderItem orderItem);
    public void deleteOrder(String oid);
    public void deleteOrderItem(String oid);
    public List<Order> queryOrderByUid(int uid);
    public List<OrderItem> queryOrderItemByUidAndOid(String oid);
    public List<Order> queryOrder(int pageIndex,int pageSize);
    public int queryCountOfOrder();
    public List<Order> queryOrderByOstatus(int ostatus,int pageIndex,int pageSize);
    public int queryCountOfOrderByOstatus(int ostatus);
    public void updateOrderStatus(String oid,int ostatus);
}
