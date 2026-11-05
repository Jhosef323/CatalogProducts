package com.catalogproducts.catalogproducts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByFavoriteTrue();

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceGreaterThan(double price);

    List<Product> findByPriceLessThanEqual(double price);
}
