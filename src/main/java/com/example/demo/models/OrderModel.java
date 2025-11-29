package com.example.demo.models;

import com.example.demo.DTOs.order.OrderCreateDTO;
import com.example.demo.models.enums.OrderStatusEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order_table")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "is_takeout")
    private Boolean isTakeout;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrderModel> productOrders = new ArrayList<>();

    @Column(name = "order_total")
    private BigDecimal orderTotal;

    public OrderModel() {
    }

    public OrderModel(OrderCreateDTO orderCreateDTO) {
        this.customerName = orderCreateDTO.customerName();
        this.tableNumber = orderCreateDTO.tableNumber();
        this.orderDate = LocalDateTime.now();
        this.isTakeout = orderCreateDTO.isTakeout();
        this.orderStatus = OrderStatusEnum.CREATED;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getTakeout() {
        return isTakeout;
    }

    public void setTakeout(Boolean takeout) {
        isTakeout = takeout;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<ProductOrderModel> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrderModel> productOrders) {
        this.productOrders = productOrders;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", tableNumber=" + tableNumber +
                ", orderDate=" + orderDate +
                ", isTakeout=" + isTakeout +
                ", orderStatus=" + orderStatus +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
