package com.asalkarhealthyhub.controller;

import com.asalkarhealthyhub.entity.Cart;
import com.asalkarhealthyhub.entity.Product;
import com.asalkarhealthyhub.repository.CartRepository;
import com.asalkarhealthyhub.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
        Optional<Product> productOpt = productRepository.findById(cart.getProductId());
        
        if (productOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Product not found");
        }
        
        Product product = productOpt.get();
        if (!product.isInStock() || product.getStockQuantity() < cart.getQuantity()) {
            return ResponseEntity.badRequest().body("Product out of stock or insufficient quantity");
        }
        
        cartRepository.save(cart);
        return ResponseEntity.ok("Item added to cart successfully");
    }

    @GetMapping("/user/{userId}")
    public List<Cart> getCartByUser(@PathVariable Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @DeleteMapping("/user/{userId}/product/{productId}")
    public String removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
        return "Item removed from cart";
    }
}