package com.souza.auth.project_spring.domain.product;

public record ProductResponseDTO (String id, String name, Integer price){

    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice().intValue());
    }
}
