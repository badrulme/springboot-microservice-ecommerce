package com.example.inventoryservice.service;

import com.example.inventoryservice.model.InventoryResponse;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    public boolean isInStock(String skuCode) {
        return repository.existsBySkuCode(skuCode);
    }

    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        return repository.findAllBySkuCodeIn(skuCodes).stream().map(inventoryEntity ->
                InventoryResponse.builder()
                        .skuCode(inventoryEntity.getSkuCode())
                        .isInStock(inventoryEntity.getQuantity() > 0)
                        .build()
        ).toList();
    }
}
