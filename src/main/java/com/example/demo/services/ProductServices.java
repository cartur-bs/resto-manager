package com.example.demo.services;

import com.example.demo.DTOs.ProductCreateDTO;
import com.example.demo.DTOs.ProductResponseDTO;
import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;


    public ProductResponseDTO getProdById(UUID id){
        ProductModel prodModelResponse = productRepository.findById(id).orElseThrow(RuntimeException::new);
        return new ProductResponseDTO(prodModelResponse);

    }
    public ProductResponseDTO patchProd(UUID id, ProductCreateDTO dto) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        if (dto.prodName() != null) product.setProdName(dto.prodName());
        if (dto.prodDescription() != null) product.setProdDescription(dto.prodDescription());
        if (dto.prodCategory() != null) product.setProdCategory(dto.prodCategory());
        if (dto.prodPrice() != null) product.setProdPrice(dto.prodPrice());
        if (dto.isProdAvailable() != null) product.setIsProdAvailable(dto.isProdAvailable());

        productRepository.save(product);

        return new ProductResponseDTO(product);
    }

}
