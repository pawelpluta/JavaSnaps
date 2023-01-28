package com.pawelpluta.day027;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
class InMemoryProductRepository {

    private final Map<String, Integer> productsQuantity = new HashMap<>();
    public void save(Product product) {
        productsQuantity.put(product.productId(), product.quantity());
    }

    public Optional<Product> findById(String productId) {
        return Optional.ofNullable(productsQuantity.get(productId))
                .map(quantity -> new Product(productId, quantity, List.of()));
    }
}
