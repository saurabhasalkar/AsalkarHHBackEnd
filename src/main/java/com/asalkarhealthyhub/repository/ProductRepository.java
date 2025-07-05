package com.asalkarhealthyhub.repository;

import com.asalkarhealthyhub.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
