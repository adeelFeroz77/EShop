package com.EShop.EShop.dto;

import com.EShop.EShop.domain.Product;
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
public class CategoryDto {
    private Long id;
    @NotBlank(message = "message must not be null")
    private String name;
    @NotNull
    private Boolean isActive;
}
