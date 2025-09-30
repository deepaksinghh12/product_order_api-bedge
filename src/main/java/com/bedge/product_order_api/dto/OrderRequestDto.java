package com.bedge.product_order_api.dto;

import java.util.List;

public class OrderRequestDto {
    private Long customerId;
    private List<Long> productIds;

    // getters and setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public List<Long> getProductIds() { return productIds; }
    public void setProductIds(List<Long> productIds) { this.productIds = productIds; }
}
