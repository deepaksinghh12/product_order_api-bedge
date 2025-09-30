package com.bedge.product_order_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "Customer name is required")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Region is required")
    private String region;
}
