package com.asalkarhealthyhub.service;

import com.asalkarhealthyhub.dto.AuthResponse;
import com.asalkarhealthyhub.entity.User;
import com.asalkarhealthyhub.repository.UserRepository;
import com.asalkarhealthyhub.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return new AuthResponse(null, "Email already exists", null, null);
        }
        // Set role to CUSTOMER if not specified, or use provided role
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("CUSTOMER");
        }
        User savedUser = userRepository.save(user);
        String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole());
        return new AuthResponse(token, "Registration successful", savedUser.getId(), savedUser.getRole());
    }

    public AuthResponse login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            User user = userOpt.get();
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
            return new AuthResponse(token, "Login successful", user.getId(), user.getRole());
        }
        return new AuthResponse(null, "Invalid email or password", null, null);
    }
}
