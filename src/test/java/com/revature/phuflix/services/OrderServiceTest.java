package com.revature.phuflix.services;

import com.revature.phuflix.daos.OrderDAO;
import com.revature.phuflix.models.Orders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    OrderService orderService = new OrderService(new OrderDAO());

    @org.junit.jupiter.api.Test
    void getShoppingCartSum() {
        String userId = "e914444f-4ce5-4b4e-955b-a2b3d5e87bcd";
        assertEquals(10545,orderService.getShoppingCartSum(userId));
    }

}