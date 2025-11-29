package com.example.demo.services;

import com.example.demo.DTOs.product.ProductCreateDTO;
import com.example.demo.DTOs.product.ProductPatchDTO;
import com.example.demo.DTOs.product.ProductResponseDTO;
import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;


    public ProductResponseDTO createProd(@RequestBody ProductCreateDTO productCreateDTO){
        ProductModel productModel = new ProductModel(productCreateDTO);
        productRepository.save(productModel);
        return new ProductResponseDTO(productModel);
    }

    public ProductResponseDTO getProdById(UUID id){
        ProductModel prodModelResponse = productRepository.findById(id).orElseThrow(RuntimeException::new);
        return new ProductResponseDTO(prodModelResponse);

    }
    public ProductResponseDTO patchProd(UUID id, ProductPatchDTO dto) {
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
