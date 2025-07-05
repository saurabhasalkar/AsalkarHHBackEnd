package com.asalkarhealthyhub.controller;

import com.asalkarhealthyhub.entity.Order;
import com.asalkarhealthyhub.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        order.setOrderTime(LocalDateTime.now());
        order.setDelivered(false);
        orderRepository.save(order);
        return "Order placed successfully";
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
