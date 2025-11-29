package com.example.demo.DTOs.order;

import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;
import com.example.demo.models.enums.OrderStatusEnum;
import java.math.BigDecimal;



public record OrderResponseDTO(UUID orderId, String customerName, Integer tableNumber, LocalDateTime orderDate, Boolean isTakeout, OrderStatusEnum orderStatus, BigDecimal orderTotal, List<OrderItemResponseDTO> items) {
}
