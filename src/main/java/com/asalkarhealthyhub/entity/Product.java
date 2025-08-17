package com.asalkarhealthyhub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "in_stock")
    private boolean inStock;

    @Column(name = "extraction_method")
    private String extractionMethod;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    private String name;
    private String description;
    private double price;
    private String category; // e.g., "Coconut Oil", "Sesame Oil", "Groundnut Oil"
    private String volume; // e.g., "500ml", "1L", "2L"
    private String benefits; // Health benefits
}
