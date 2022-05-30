package com.revature.phuflix.services;

import com.revature.phuflix.daos.OrderDAO;
import com.revature.phuflix.models.Orders;

import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void save(Orders order){
        orderDAO.save(order);
    }

    public List<Orders> getShoppingCart(String user_id){
        return orderDAO.getShoppingCart(user_id);
    }

    public void deleteByID(String order_id) {
        orderDAO.delete(order_id);
    }

    public int getShoppingCartSum(String user_id){
        return orderDAO.getShoppingCartSum(user_id);
    }

    public void update(Orders order){
        orderDAO.update(order);
    }

    public List<Orders> getSOrderHistory(String user_id) {
        return orderDAO.getSOrderHistory(user_id);
    }
}
