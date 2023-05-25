package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.model.ProductRequest;
import com.example.productservice.model.ProductResponse;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;


    public List<ProductResponse> getProducts() {
        return repository.findAll().stream().map(
                this::mapToProductResponse
        ).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public ProductResponse getProduct(String id) {
        return mapToProductResponse(getProductEntity(id));
    }

    public Product getProductEntity(String id) {
        return repository.findById(id).orElseThrow();

    }

    //    public Product update(ProductRequest request, String id) {
//        Product product = getProduct(id);
//         product = product
//                .name(request.getName())
//                .description(request.getDescription())
//                .price(request.getPrice())
//                .build();
//
//        return repository.save(product);
//    }
    public Product save(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        return repository.save(product);
    }
}
