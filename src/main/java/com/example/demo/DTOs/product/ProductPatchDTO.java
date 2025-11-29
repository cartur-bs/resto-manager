package com.example.demo.DTOs.product;

import java.math.BigDecimal;

public record ProductPatchDTO(String prodName, String prodDescription, String prodCategory, BigDecimal prodPrice, Boolean isProdAvailable) {

}
