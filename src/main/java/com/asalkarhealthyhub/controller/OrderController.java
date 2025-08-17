package com.asalkarhealthyhub.controller;

import com.asalkarhealthyhub.entity.Order;
import com.asalkarhealthyhub.entity.Product;
import com.asalkarhealthyhub.repository.OrderRepository;
import com.asalkarhealthyhub.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        Optional<Product> productOpt = productRepository.findById(order.getProductId());
        
        if (productOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Product not found");
        }
        
        Product product = productOpt.get();
        if (!product.isInStock() || product.getStockQuantity() < order.getQuantity()) {
            return ResponseEntity.badRequest().body("Product out of stock or insufficient quantity");
        }
        
        // Update stock quantity
        product.setStockQuantity(product.getStockQuantity() - order.getQuantity());
        if (product.getStockQuantity() == 0) {
            product.setInStock(false);
        }
        productRepository.save(product);
        
        order.setOrderTime(LocalDateTime.now());
        order.setDelivered(false);
        orderRepository.save(order);
        return ResponseEntity.ok("Order placed successfully");
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @PutMapping("/deliver/{orderId}")
    public String markAsDelivered(@PathVariable Long orderId) {
        return orderRepository.findById(orderId).map(order -> {
            order.setDelivered(true);
            orderRepository.save(order);
            return "Order marked as delivered";
        }).orElse("Order not found");
    }
}
