package com.bedge.product_order_api.repository;

import com.bedge.product_order_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Optional: findByCustomer, findByStatus, etc.
}
