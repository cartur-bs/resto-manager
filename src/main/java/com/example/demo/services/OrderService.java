package com.example.demo.services;

import com.example.demo.DTOs.order.OrderCreateDTO;
import com.example.demo.DTOs.order.OrderItemDTO;
import com.example.demo.DTOs.order.OrderItemResponseDTO;
import com.example.demo.DTOs.order.OrderResponseDTO;
import com.example.demo.models.OrderModel;
import com.example.demo.models.ProductModel;
import com.example.demo.models.ProductOrderModel;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

@Service
public class OrderService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    public OrderResponseDTO createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        OrderModel orderModel = new OrderModel(orderCreateDTO);
        List<ProductOrderModel> productOrdersList = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemDTO orderItemDTO : orderCreateDTO.productOrders()) {
            ProductModel product = productRepository.findById(orderItemDTO.prodId())
                    .orElseThrow(RuntimeException::new);

            // 2.2 Criar item do pedido com preço congelado
            ProductOrderModel productOrder = new ProductOrderModel(
                    orderModel,                         // relação ManyToOne
                    product,                            // produto associado
                    product.getProdPrice(),             // preço atual → congelado
                    orderItemDTO.quantity()             // quantidade
            );

            // 2.3 Adicionar à lista
            productOrdersList.add(productOrder);

            // 2.4 Soma no total
            BigDecimal itemTotal = product.getProdPrice()
                    .multiply(BigDecimal.valueOf(orderItemDTO.quantity()));

            total = total.add(itemTotal);
        }

        orderModel.setProductOrders(productOrdersList);

        // 4. Definir total calculado
        orderModel.setOrderTotal(total);

        // 5. Salvar
        OrderModel savedOrder = orderRepository.save(orderModel);

        // 6. Retornar DTO
        return convertToResponseDTO(savedOrder);
    }

    private OrderResponseDTO convertToResponseDTO(OrderModel orderModel) {

        List<OrderItemResponseDTO> items = orderModel.getProductOrders()
                .stream()
                .map(item -> new OrderItemResponseDTO(
                        item.getProduct().getProdId(),
                        item.getProduct().getProdName(),
                        item.getProdOrderQuantity(),
                        item.getProdUnitPrice()
                )).toList();

        return new OrderResponseDTO(
                orderModel.getOrderId(),
                orderModel.getCustomerName(),
                orderModel.getTableNumber(),
                orderModel.getOrderDate(),
                orderModel.getTakeout(),
                orderModel.getOrderStatus(),
                orderModel.getOrderTotal(),
                items
        );
    }
}
