package com.EShop.EShop.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @OneToOne
    @JoinColumn(name = "cart_id",referencedColumnName = "id")
    private Cart cart;
}
