package com.restaurant_manager.restaurant_manager.models.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant_manager.restaurant_manager.models.products.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);
    
}
