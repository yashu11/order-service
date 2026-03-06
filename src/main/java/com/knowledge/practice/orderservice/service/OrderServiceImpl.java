package com.knowledge.practice.orderservice.service;

import com.knowledge.practice.orderservice.client.PaymentClient;
import com.knowledge.practice.orderservice.dto.OrderRequest;
import com.knowledge.practice.orderservice.dto.OrderResponse;
import com.knowledge.practice.orderservice.model.Order;
import com.knowledge.practice.orderservice.model.OrderStatus;
import com.knowledge.practice.orderservice.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl  implements  OrderService{

    private final OrderRepository orderRepository;
//    private final  PaymentClientService paymentClientService;
    private final PaymentClient paymentClient;
//    private final PaymentClientService paymentClientService;
//    private final PaymentService paymentService;
//    private final RestTemplate restTemplate;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order=Order.builder().productName(orderRequest.getProductName())
                .price(orderRequest.getPrice())
                .status(OrderStatus.CREATED)
                .build();
        Order savedOrder= orderRepository.save(order);

        Map<String, Object> paymentRequest = Map.of("orderId", order.getId(), "amount", order.getPrice());
        String response= paymentClient.processPayment(paymentRequest);
        if("FAILED".equals(response)){
            savedOrder.setStatus(OrderStatus.PAYMENT_FAILED);
        }
        else {
            savedOrder.setStatus(OrderStatus.COMPLETED);
        }
         orderRepository.save(savedOrder);

//        try {
//            String paymentServiceUrl = "http://localhost:8081/api/payments/process";
//            Map<String, Object> paymentRequest = Map.of("orderId", savedOrder.getId(), "amount", savedOrder.getPrice());
//            restTemplate.postForEntity(paymentServiceUrl, paymentRequest, Void.class);
//            savedOrder.setStatus(OrderStatus.COMPLETED);
//        } catch (Exception e) {
//            savedOrder.setStatus(OrderStatus.PAYMENT_PENDING);
//        }

//        paymentService.processPayment(savedOrder.getPrice());
        return mapToOrderResponse(savedOrder);
    }





    @Override
    public OrderResponse getOrderById(Long id) {
        Order order= orderRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return mapToOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orderList= orderRepository.findAll();
        return orderList.stream().map(order-> mapToOrderResponse(order)).toList();
    }

    private OrderResponse mapToOrderResponse(Order savedOrder) {
        return OrderResponse.builder().id(savedOrder.getId())
                .productName(savedOrder.getProductName())
                .price(savedOrder.getPrice())
                .status(savedOrder.getStatus().name())
                .createdAt(savedOrder.getCreatedAt())
                .build();
    }
}
