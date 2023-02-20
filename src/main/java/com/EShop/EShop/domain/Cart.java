package com.EShop.EShop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate localDate;
    private String deviceAddress;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    private List<ProductCart> productCarts;

    @OneToOne(mappedBy = "cart",cascade = CascadeType.ALL)
    private Transaction transaction;
}
