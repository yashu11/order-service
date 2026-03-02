package com.knowledge.practice.orderservice.service;

import com.knowledge.practice.orderservice.dto.OrderRequest;
import com.knowledge.practice.orderservice.dto.OrderResponse;
import com.knowledge.practice.orderservice.model.Order;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest order);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getAllOrders();

}
