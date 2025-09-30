package com.bedge.product_order_api.controller;

import com.bedge.product_order_api.dto.OrderRequestDto;
import com.bedge.product_order_api.dto.OrderResponseDto;
import com.bedge.product_order_api.dto.OrderSummaryDto;
import com.bedge.product_order_api.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<OrderResponseDto> place(@RequestBody OrderRequestDto req) {
        OrderResponseDto resp = service.placeOrder(req);
        return ResponseEntity.status(201).body(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrder(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDto> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelOrder(id));
    }

    @GetMapping("/summary")
    public ResponseEntity<OrderSummaryDto> summary() {
        return ResponseEntity.ok(service.summary());
    }
}
