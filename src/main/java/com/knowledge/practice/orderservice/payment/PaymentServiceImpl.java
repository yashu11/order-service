package com.knowledge.practice.orderservice.payment;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Override
    public void processPayment(Double price) {

        if(new Random().nextBoolean()){
            throw new RuntimeException("Payment failed !");
        }
        System.out.println("Payment processed successfully for amount: " + price);
    }
}
