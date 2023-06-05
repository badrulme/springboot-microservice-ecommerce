package com.example.orderservice.controller;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String placeOrder(@RequestBody OrderRequest request) throws IllegalAccessException {
        orderService.placeOrder(request);
        return "OrderEntity placed successfully";
    }

    @GetMapping
    public List<OrderEntity> getAllOrders(){
        return orderService.getAllOrders();
    }

}
