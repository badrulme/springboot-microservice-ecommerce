package com.example.orderservice.service;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.entity.OrderLineItemEntity;
import com.example.orderservice.model.OrderLineItemRequest;
import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;

    public void placeOrder(OrderRequest request) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber(UUID.randomUUID().toString());

        orderEntity.setOrderLineItems(
                request.getItemRequests().stream().map(this::mapToDto).toList()
        );

        repository.save(orderEntity);
    }

    private OrderLineItemEntity mapToDto(OrderLineItemRequest itemRequest) {

        OrderLineItemEntity orderLineItem = new OrderLineItemEntity();

        orderLineItem.setPrice(itemRequest.getPrice());
        orderLineItem.setQuantity(itemRequest.getQuantity());
        orderLineItem.setSkuCode(itemRequest.getSkuCode());

        return orderLineItem;
    }

    public List<OrderEntity> getAllOrders() {
        return repository.findAll();
    }
}
