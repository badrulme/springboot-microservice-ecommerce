package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    boolean existsBySkuCode(String skuCode);
}
