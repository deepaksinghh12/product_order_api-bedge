package com.bedge.product_order_api.service;

import com.bedge.product_order_api.entity.Customer;
import com.bedge.product_order_api.exception.ResourceNotFoundException;
import com.bedge.product_order_api.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) { this.repo = repo; }

    public Customer create(Customer c) { return repo.save(c); }

    public Customer findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
    }
}
