package com.walmart.api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.walmart.api.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}