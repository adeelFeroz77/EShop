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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String cellNumber;
    private String name;
    private String address;
    private Boolean isActive;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Cart> carts;
}
