package com.restaurant_manager.restaurant_manager.models.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant_manager.restaurant_manager.models.orders.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

    long countByState(String state);
}
