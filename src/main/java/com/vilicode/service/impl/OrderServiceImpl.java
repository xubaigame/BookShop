package com.vilicode.service.impl;

import com.vilicode.bean.Book;
import com.vilicode.bean.Order;
import com.vilicode.bean.OrderItem;
import com.vilicode.bean.Page;
import com.vilicode.mapper.BookMapper;
import com.vilicode.mapper.OrderMapper;
import com.vilicode.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public BookMapper bookMapper;
    @Override
    public boolean addOrder(Order order) {
        String oid=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        order.setOid(oid);
        try {
            orderMapper.addOrder(order);
            order.getItemMap().forEach((key, value) -> {
                value.setOid(oid);
                orderMapper.addOrderItem(value);
            });
            return true;
        }catch (Exception e)
        {
            orderMapper.deleteOrder(oid);
            orderMapper.deleteOrderItem(oid);
            System.out.println(e.getMessage());
            return false;
        }


    }

    @Override
    public List<Order> queryOrderByUid(int uid) {
        List<Order> orders=new ArrayList<>();
        try {
            orders=orderMapper.queryOrderByUid(uid);
            for(int i=0;i<orders.size();i++)
            {
                orders.get(i).setItemList(orderMapper.queryOrderItemByUidAndOid(orders.get(i).getOid()));
                for(int j=0;j<orders.get(i).getItemList().size();j++)
                {
                    orders.get(i).getItemList().get(j).setBook(bookMapper.queryBookById(orders.get(i).getItemList().get(j).getBid()));
                }
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    @Override
    public Page queryOrdersByOstatus(int ostatus,int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 4;
        int totalCount = 0;
        try {
            if(ostatus!=1)
                totalCount=orderMapper.queryCountOfOrderByOstatus(ostatus);
            else
                totalCount=orderMapper.queryCountOfOrder();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List<Order> list=null;
        try {
            if(ostatus!=1)
                list=orderMapper.queryOrderByOstatus(ostatus,(pageNumber-1)*pageSize,pageSize);
            else
                list=orderMapper.queryOrder((pageNumber-1)*pageSize,pageSize);
            for(int i=0;i<list.size();i++)
            {
                list.get(i).setItemList(orderMapper.queryOrderItemByUidAndOid(list.get(i).getOid()));
                for(int j=0;j<list.get(i).getItemList().size();j++)
                {
                    list.get(i).getItemList().get(j).setBook(bookMapper.queryBookById(list.get(i).getItemList().get(j).getBid()));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.setList((List)list);
        return p;
    }

    @Override
    public boolean updateOrderStatus(String oid, int ostatus) {
        try {
            orderMapper.updateOrderStatus(oid,ostatus);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteOrderByOid(String oid) {
        try {
            orderMapper.deleteOrderItem(oid);
            orderMapper.deleteOrder(oid);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
