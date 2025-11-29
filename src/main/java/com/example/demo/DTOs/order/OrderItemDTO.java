package com.example.demo.DTOs.order;

import java.util.UUID;

public record OrderItemDTO(UUID prodId, Integer quantity) {
}
