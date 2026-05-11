package com.souza.auth.project_spring.repositories;

import com.souza.auth.project_spring.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositories extends JpaRepository<Product, String> {
}
