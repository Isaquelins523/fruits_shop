package com.Lins.fruit_shop.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "products")
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private  String name;

    private Integer price_in_cents;

    public Product(RequestProduct requestProduct) {
        this.id = requestProduct.id();
        this.name = requestProduct.name();
        this.price_in_cents = (Integer) requestProduct.price_in_cents();
    }
}
