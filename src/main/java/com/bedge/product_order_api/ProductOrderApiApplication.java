package com.bedge.product_order_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// This tells Spring where to find your repository interfaces
@EnableJpaRepositories("com.bedge.product_order_api.repository")
// This tells Hibernate where to find your @Entity classes to create tables
@EntityScan("com.bedge.product_order_api.entity")
@SpringBootApplication
public class ProductOrderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductOrderApiApplication.class, args);
    }

}

