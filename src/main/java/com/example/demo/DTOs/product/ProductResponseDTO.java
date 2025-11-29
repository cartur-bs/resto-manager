package com.example.demo.DTOs.product;

import com.example.demo.models.ProductModel;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID prodId, String prodName, String prodDescription, String prodCategory, BigDecimal prodPrice, Boolean isProdAvailable) {
    public ProductResponseDTO(ProductModel productModel) {
        this(
                productModel.getProdId(),
                productModel.getProdName(),
                productModel.getProdDescription(),
                productModel.getProdCategory(),
                productModel.getProdPrice(),
                productModel.getIsProdAvailable()
        );
    }


}
