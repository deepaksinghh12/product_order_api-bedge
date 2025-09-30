package com.bedge.product_order_api.controller;

import com.bedge.product_order_api.entity.Customer;
import com.bedge.product_order_api.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer c) {
        Customer created = service.create(c);
        return ResponseEntity.status(201).body(created);
    }
}
