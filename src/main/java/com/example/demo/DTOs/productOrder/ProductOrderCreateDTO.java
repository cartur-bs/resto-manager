package com.example.demo.DTOs.productOrder;

import com.example.demo.models.OrderModel;
import com.example.demo.models.ProductModel;

import java.math.BigDecimal;

public record ProductOrderCreateDTO(OrderModel order, ProductModel product, BigDecimal prodUnitPrice, Integer prodOrderQuantity) {
}
