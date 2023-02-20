package com.EShop.EShop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Length(min = 8)
    private String password;
    @Email
    @NotBlank
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    @Length(min = 11,max = 11)
    private String phoneNumber;
    @NotNull
    private Boolean isActive;
}
