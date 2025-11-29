package com.example.demo.controllers;

import com.example.demo.DTOs.order.OrderCreateDTO;
import com.example.demo.DTOs.order.OrderResponseDTO;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/order/")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("post/")
    public ResponseEntity<?> createNewOrder(@RequestBody OrderCreateDTO orderCreateDTO){
        OrderResponseDTO responseDTO = orderService.createOrder(orderCreateDTO);
        return ResponseEntity
                .created(URI.create("/order/" + responseDTO.orderId()))
                .body(responseDTO);

    }
}
