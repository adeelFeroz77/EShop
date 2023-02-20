package com.EShop.EShop.dto;

import com.EShop.EShop.domain.Cart;
import com.EShop.EShop.domain.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartDto {

    private Long id;
    @NotNull
    @Min(value = 1)
    private Long quantity;
    @NotNull
    @Min(value = 0)
    private Double cost;
    @NotNull
    @Min(value = 0)
    private Double price;
    @NotNull
    private Cart cart;
    @NotNull
    private Product product;
}
