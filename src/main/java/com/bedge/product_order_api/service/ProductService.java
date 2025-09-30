package com.bedge.product_order_api.service;

import com.bedge.product_order_api.entity.Product;
import com.bedge.product_order_api.exception.ResourceNotFoundException;
import com.bedge.product_order_api.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public Product create(Product product) { return repo.save(product); }

    public List<Product> getAll() { return repo.findAll(); }

    public Page<Product> getPage(int page, int size) { return repo.findAll(PageRequest.of(page, size)); }

    public Product findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }
}
