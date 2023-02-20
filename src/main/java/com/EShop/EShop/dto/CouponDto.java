package com.EShop.EShop.dto;

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
public class CouponDto {
    private Long id;
    @NotNull
    private String code;
    @Min(value = 1)
    private Double discount;
    @NotNull
    private Boolean isPercentage;
    @NotNull
    private Boolean isActive;
}
