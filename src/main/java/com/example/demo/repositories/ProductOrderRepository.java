package com.example.demo.repositories;

import com.example.demo.models.ProductOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductOrderRepository extends JpaRepository<ProductOrderModel, UUID> {
}
