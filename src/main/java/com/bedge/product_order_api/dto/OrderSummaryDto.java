package com.bedge.product_order_api.dto;

public class OrderSummaryDto {
    private long totalOrders;
    private double totalRevenue;
    private String topProduct;

    public OrderSummaryDto(long totalOrders, double totalRevenue, String topProduct) {
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.topProduct = topProduct;
    }

    // getters and setters
    // ...
}
