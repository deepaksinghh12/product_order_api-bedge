package com.bedge.product_order_api.dto;

import com.bedge.product_order_api.entity.Order;
import com.bedge.product_order_api.entity.Status;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private LocalDateTime orderDate;
    private Long customerId;
    private List<Long> productIds;
    private double totalAmount;
    private Order.Status status;
}
