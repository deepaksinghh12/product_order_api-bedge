package com.bedge.product_order_api.dto;

import com.bedge.product_order_api.entity.Order;
import com.bedge.product_order_api.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;

    private LocalDateTime orderDate;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Products are required")
    private List<Product> products;

    private Double totalAmount;

    private Order.Status status;
}
