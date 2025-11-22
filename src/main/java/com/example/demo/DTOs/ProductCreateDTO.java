package com.example.demo.DTOs;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductCreateDTO(@NotNull String prodName, String prodDescription, @NotNull String prodCategory, @NotNull BigDecimal prodPrice, @NotNull Boolean isProdAvailable) {
}
