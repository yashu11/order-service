package com.knowledge.practice.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private String productName;
    private double price;
    private String status;
    private LocalDateTime createdAt;
}
