package com.asalkarhealthyhub.repository;

import com.asalkarhealthyhub.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
    
    @Transactional
    void deleteByUserIdAndProductId(Long userId, Long productId);
}