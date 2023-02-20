package com.EShop.EShop.dto;

import com.EShop.EShop.domain.Coupon;
import com.EShop.EShop.domain.Customer;
import com.EShop.EShop.domain.ProductCart;
import com.EShop.EShop.domain.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CartDto {
    private Long id;
    @NotNull
    private LocalDate localDate;
    private String deviceAddress;
    private Customer customer;
    private Coupon coupon;

}
