package com.EShop.EShop.dto;

import com.EShop.EShop.domain.Cart;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionDto {
    private Long id;
    @Min(value = 0)
    private Double amount;
    @NotNull
    private Cart cart;
}
