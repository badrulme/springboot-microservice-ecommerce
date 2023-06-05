package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.InventoryResponse;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/inventories")
public class InventoryController {

    private final InventoryService service;

    @GetMapping("{skuCode}")
    public ResponseEntity<Boolean> isInStock(@PathVariable String skuCode) {
        return new ResponseEntity<>(service.isInStock(skuCode), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam List<String> skuCodes) {
        return new ResponseEntity<>(service.isInStock(skuCodes), HttpStatus.OK);
    }
}
