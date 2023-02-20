package com.EShop.EShop.dto;

import com.EShop.EShop.domain.Cart;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CustomerDto {
    private Long id;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String cellNumber;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private Boolean isActive;
}
