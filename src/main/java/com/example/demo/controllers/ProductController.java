package com.example.demo.controllers;

import com.example.demo.DTOs.product.ProductCreateDTO;
import com.example.demo.DTOs.product.ProductPatchDTO;
import com.example.demo.DTOs.product.ProductResponseDTO;
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
       ProductResponseDTO productResponseDTO = productServices.createProd(productDTO);

        return ResponseEntity.created((URI.create("/products/" + productResponseDTO.prodId()))).body(productResponseDTO);
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
    public ResponseEntity<ProductResponseDTO> patchProduct(@PathVariable UUID id, @RequestBody ProductPatchDTO productDTO) {
        ProductResponseDTO productResponseDTOUpdated = productServices.patchProd(id, productDTO);
        return ResponseEntity.ok(productResponseDTOUpdated);
    }
}
