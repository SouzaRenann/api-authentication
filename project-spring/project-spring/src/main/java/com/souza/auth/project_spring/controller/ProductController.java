package com.souza.auth.project_spring.controller;

import com.souza.auth.project_spring.domain.product.Product;
import com.souza.auth.project_spring.domain.product.ProductRequestDTO;
import com.souza.auth.project_spring.domain.product.ProductResponseDTO;
import com.souza.auth.project_spring.repositories.ProductRepositories;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductRepositories repositories;


    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDTO body){
        Product newProduct = new Product(body);

        this.repositories.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productList = this.repositories.findAll().stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }
}
