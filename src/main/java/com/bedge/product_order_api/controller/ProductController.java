package com.bedge.product_order_api.controller;

import com.bedge.product_order_api.entity.Product;
import com.bedge.product_order_api.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product p) {
        Product created = service.create(p);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "size", required = false) Integer size) {
        if (page != null && size != null) {
            Page<Product> p = service.getPage(page, size);
            return ResponseEntity.ok(p);
        } else {
            List<Product> list = service.getAll();
            return ResponseEntity.ok(list);
        }
    }
}
