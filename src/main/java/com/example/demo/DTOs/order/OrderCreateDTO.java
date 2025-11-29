package com.example.demo.DTOs.order;

import java.util.List;

public record OrderCreateDTO(String customerName, Integer tableNumber, Boolean isTakeout, List<OrderItemDTO> productOrders) {
}
