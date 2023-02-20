package com.EShop.EShop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Double discount;
    private Boolean isPercentage;
    private Boolean isActive;

    @OneToMany(mappedBy = "coupon",cascade = CascadeType.ALL)
    private List<Cart> Cart;

}
