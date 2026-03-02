package com.knowledge.practice.orderservice.controller;

import com.knowledge.practice.orderservice.dto.OrderRequest;
import com.knowledge.practice.orderservice.dto.OrderResponse;
import com.knowledge.practice.orderservice.model.Order;
import com.knowledge.practice.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/")
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

}
