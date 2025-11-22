package com.example.demo.controllers;

import com.example.demo.DTOs.ProductCreateDTO;
import com.example.demo.DTOs.ProductResponseDTO;
import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products/")
public class ProductController extends SQLException {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductServices productServices;

    @PostMapping("post/")
    public ResponseEntity<?> createNewProduct(@RequestBody @Valid ProductCreateDTO productDTO) throws SQLException {
        ProductModel productModel = new ProductModel(productDTO);
        var savedProd = productRepository.save(productModel);
        URI location = URI.create("/products/" + savedProd.getProdId());
        return ResponseEntity.created(location).body(savedProd);
    }

    @GetMapping("get-all-prods")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<ProductResponseDTO> getProdByID(@PathVariable UUID id) {
        return ResponseEntity.ok(productServices.getProdById(id));
    }

    @PatchMapping("edit/{id}")
    public ResponseEntity<ProductResponseDTO> patchProduct(@PathVariable UUID id, @RequestBody ProductCreateDTO productDTO) {
        ProductResponseDTO productResponseDTOUpdated = productServices.patchProd(id, productDTO);
        return ResponseEntity.ok(productResponseDTOUpdated);
    }
}
