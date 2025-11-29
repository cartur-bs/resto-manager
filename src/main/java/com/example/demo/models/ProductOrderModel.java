package com.example.demo.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "prod_order_table")
public class ProductOrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productOrderId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne
    private ProductModel product;

    @Column(name = "prod_unit_price")
    private BigDecimal prodUnitPrice;

    @Column(name = "prod_order_quantity")
    private Integer prodOrderQuantity;

    public ProductOrderModel() {
    }

    public ProductOrderModel(OrderModel order, ProductModel product, BigDecimal prodUnitPrice, Integer prodOrderQuantity) {
        this.order = order;
        this.product = product;
        this.prodUnitPrice = prodUnitPrice;
        this.prodOrderQuantity = prodOrderQuantity;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public BigDecimal getProdUnitPrice() {
        return prodUnitPrice;
    }

    public void setProdUnitPrice(BigDecimal prodUnitPrice) {
        this.prodUnitPrice = prodUnitPrice;
    }

    public Integer getProdOrderQuantity() {
        return prodOrderQuantity;
    }

    public void setProdOrderQuantity(Integer prodOrderQuantity) {
        this.prodOrderQuantity = prodOrderQuantity;
    }

    @Override
    public String toString() {
        return "ProductOrderModel{" +
                "productOrderId=" + productOrderId +
                ", order=" + order +
                ", product=" + product +
                ", prodUnitPrice=" + prodUnitPrice +
                ", prodOrderQuantity=" + prodOrderQuantity +
                '}';
    }
}
