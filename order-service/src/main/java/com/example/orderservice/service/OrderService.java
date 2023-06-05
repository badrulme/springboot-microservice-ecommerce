package com.example.orderservice.service;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.entity.OrderLineItemEntity;
import com.example.orderservice.model.InventoryResponse;
import com.example.orderservice.model.OrderLineItemRequest;
import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;

    private final WebClient webClient;

    @Transactional
    public void placeOrder(OrderRequest request) throws IllegalAccessException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber(UUID.randomUUID().toString());

        orderEntity.setOrderLineItems(
                request.getItemRequests().stream().map(this::mapToDto).toList()
        );

        List<String> skuCodes = orderEntity.getOrderLineItems().stream()
                .map(OrderLineItemEntity::getSkuCode)
                .toList();

        InventoryResponse[] result = webClient.get()
                .uri("http://localhost:8082/api/inventories",
                        uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        assert result != null;
        boolean allProductsInStock = Arrays.stream(result).allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            repository.save(orderEntity);
        } else {
            throw new IllegalAccessException("Product is not in stock, pleaes try again");
        }
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
