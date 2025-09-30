package com.bedge.product_order_api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")  // avoid conflict with SQL reserved word "order"
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private com.bedge.product_order_api.entity.Customer customer;

    @ManyToMany
    private List<com.bedge.product_order_api.entity.Product> products;

    private LocalDateTime orderDate;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        NEW,
        CANCELLED,
        COMPLETED
    }

    public Order() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Customer customer;
        private List<Product> products;
        private LocalDateTime orderDate;
        private double totalAmount;
        private Status status;

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder products(List<Product> products) {
            this.products = products;
            return this;
        }

        public Builder orderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder totalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.customer = this.customer;
            order.products = this.products;
            order.orderDate = this.orderDate;
            order.totalAmount = this.totalAmount;
            order.status = this.status;
            return order;
        }
    }

    // ----- Getters & Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
