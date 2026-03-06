package com.knowledge.practice.orderservice.service;

import com.knowledge.practice.orderservice.client.PaymentClient;
import com.knowledge.practice.orderservice.model.Order;
import com.knowledge.practice.orderservice.model.OrderStatus;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentClientService {

    private final RestTemplate restTemplate;

    private final PaymentClient paymentClient;
    @Retry(name="paymentService")
    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public void callPaymentService(Order order){
        System.out.println("Calling payment service for order: " + order.getId());
//        String paymentServiceUrl = "http://localhost:8081/api/payments/process";
        String paymentServiceUrl = "http://payment-service/api/payments/process";
        Map<String, Object> paymentRequest = Map.of("orderId", order.getId(), "amount", order.getPrice());
        restTemplate.postForEntity(paymentServiceUrl, paymentRequest, String.class);

    }

    public void paymentFallback(Order order, Exception ex){
        ex.printStackTrace();
        System.out.println("Payment service is down. Fallback method called for order id: " + order.getId());
        order.setStatus(OrderStatus.PAYMENT_FAILED);
    }
}
