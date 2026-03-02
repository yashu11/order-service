package com.knowledge.practice.orderservice.service;

import com.knowledge.practice.orderservice.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();

}
