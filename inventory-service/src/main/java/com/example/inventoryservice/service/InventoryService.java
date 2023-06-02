package com.example.inventoryservice.service;

import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    public boolean isInStock(String skuCode) {
        return repository.existsBySkuCode(skuCode);
    }
}
