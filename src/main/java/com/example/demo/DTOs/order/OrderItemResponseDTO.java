package com.example.demo.DTOs.order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponseDTO(UUID productId, String productName, Integer quantity, BigDecimal unitPrice) {
}
