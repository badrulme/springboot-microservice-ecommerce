package com.example.productservice.controller;

import com.example.productservice.model.ProductRequest;
import com.example.productservice.model.ProductResponse;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProductResponse> getProduct(@RequestParam String id) {
        return new ResponseEntity<>(service.getProduct(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest request) {
        return new ResponseEntity<>(service.save(request), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest request,
                                                @PathVariable String id) {
        return new ResponseEntity<>(service.update(request, id), HttpStatus.OK);
    }
}
