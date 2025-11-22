package com.example.demo.models;

import com.example.demo.DTOs.ProductCreateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "prod_model_table")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prod_id")
    private UUID prodId;
    @NotNull
    @Column(name = "prod_name")
    public String prodName;
    @Column(name = "prod_description")
    public String prodDescription;
    @NotNull
    @Column(name = "prod_category")
    public String prodCategory;
    @NotNull
    @Column(name = "prod_price")
    public BigDecimal prodPrice;
    @NotNull
    @Column(name = "is_prod_available")
    public Boolean isProdAvailable;

    public ProductModel() {
    }

    public ProductModel(ProductCreateDTO productDTO) {
        this.prodName = productDTO.prodName();
        this.prodDescription = productDTO.prodDescription();
        this.prodCategory = productDTO.prodCategory();
        this.prodPrice = productDTO.prodPrice();
        this.isProdAvailable = productDTO.isProdAvailable();
    }

    public UUID getProdId() {
        return prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }

    public BigDecimal getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(BigDecimal prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Boolean getIsProdAvailable() {
        return isProdAvailable;
    }

    public void setIsProdAvailable(Boolean prodAvailable) {
        isProdAvailable = prodAvailable;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", prodDescription='" + prodDescription + '\'' +
                ", prodCategory='" + prodCategory + '\'' +
                ", prodPrice=" + prodPrice +
                ", isProdAvailable=" + isProdAvailable +
                '}';
    }
}
