package com.EShop.EShop.dto;

import com.EShop.EShop.domain.Category;
import com.EShop.EShop.domain.Model;
import com.EShop.EShop.domain.ProductCart;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    @NotNull
    @Min(value = 0)
    private Double price;
    @NotNull
    @Min(value = 0)
    private Double cost;
    @NotBlank
    private String name;
    private String image;
    @NotNull
    private Boolean isActive;
    @NotNull
    private Category category;
    @NotNull
    private Model model;
}
