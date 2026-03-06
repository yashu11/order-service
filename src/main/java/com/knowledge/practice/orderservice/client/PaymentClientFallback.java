package com.knowledge.practice.orderservice.client;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentClientFallback implements PaymentClient {

    @Override
    public String processPayment(Map<String, Object> paymentRequest) {
        System.out.println("Payment service is down. Executing fallback method.");
        return "FAILED";
    }
}
