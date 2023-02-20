package com.EShop.EShop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModelDto {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Boolean isActive;

}
