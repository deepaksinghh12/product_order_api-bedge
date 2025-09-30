package com.bedge.product_order_api.service;


import com.bedge.product_order_api.service.CustomerService;
import com.bedge.product_order_api.dto.OrderRequestDto;
import com.bedge.product_order_api.dto.OrderResponseDto;
import com.bedge.product_order_api.dto.OrderSummaryDto;
import com.bedge.product_order_api.entity.Order;
import com.bedge.product_order_api.entity.Product;
import com.bedge.product_order_api.entity.Customer;
import com.bedge.product_order_api.entity.Order.Status;
import com.bedge.product_order_api.exception.ResourceNotFoundException;
import com.bedge.product_order_api.repository.OrderRepository;
import com.bedge.product_order_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    // This is much cleaner
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepo, ProductRepository productRepo, CustomerService customerService) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.customerService = customerService;
    }

    public OrderResponseDto placeOrder(OrderRequestDto req) {
        Customer cust = customerService.findById(req.getCustomerId());
        if (req.getProductIds() == null || req.getProductIds().isEmpty()) {
            throw new IllegalArgumentException("productIds cannot be empty");
        }
        List<Product> products = productRepo.findAllById(req.getProductIds());
        if (products.size() != req.getProductIds().size()) {
            // find the missing id(s)
            Set<Long> found = products.stream().map(Product::getId).collect(Collectors.toSet());
            List<Long> missing = req.getProductIds().stream().filter(id -> !found.contains(id)).collect(Collectors.toList());
            throw new ResourceNotFoundException("Products not found: " + missing);
        }
        double total = products.stream().mapToDouble(Product::getPrice).sum();

        Order order = Order.builder()
                .customer(cust)
                .products(products)
                .orderDate(LocalDateTime.now())
                .totalAmount(total)
                .status(Status.NEW)
                .build();

        Order saved = orderRepo.save(order);
        return toDto(saved);
    }

    public OrderResponseDto getOrder(Long id) {
        return orderRepo.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }

    public OrderResponseDto cancelOrder(Long id) {
        Order o = orderRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        if (o.getStatus() == Status.CANCELLED) {
            return toDto(o);
        }
        o.setStatus(Status.CANCELLED);
        o = orderRepo.save(o);
        return toDto(o);
    }

    public OrderSummaryDto summary() {
        List<Order> orders = orderRepo.findAll();
        long totalOrders = orders.size();
        double totalRevenue = orders.stream()
                .filter(o -> o.getStatus() != Status.CANCELLED)
                .mapToDouble(Order::getTotalAmount).sum();

        Map<String, Long> countByProduct = new HashMap<>();
        for (Order o : orders) {
            for (Product p : o.getProducts()) {
                countByProduct.put(p.getName(), countByProduct.getOrDefault(p.getName(), 0L) + 1);
            }
        }
        String topProduct = countByProduct.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("None");

        return new OrderSummaryDto(totalOrders, totalRevenue, topProduct);
    }

    private OrderResponseDto toDto(Order o) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(o.getId());
        dto.setOrderDate(o.getOrderDate());
        dto.setCustomerId(o.getCustomer().getId());
        dto.setProductIds(o.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
        dto.setTotalAmount(o.getTotalAmount());
        dto.setStatus(o.getStatus());
        return dto;
    }
}
