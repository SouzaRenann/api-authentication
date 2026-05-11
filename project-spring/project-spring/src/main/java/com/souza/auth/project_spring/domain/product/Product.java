package com.souza.auth.project_spring.domain.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigInteger;

@Table(name= "product")
@Entity(name = "product")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private @NotNull BigInteger price;

    public Product(ProductRequestDTO data){
        this.price = data.price();
        this.name = data.name();
    }
}
