package com.bedge.product_order_api.repository;

import com.bedge.product_order_api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Optional: findByEmail, etc.
}
