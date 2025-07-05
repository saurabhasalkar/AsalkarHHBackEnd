package com.asalkarhealthyhub.service;

import com.asalkarhealthyhub.entity.User;
import com.asalkarhealthyhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }
        user.setRole("USER");
        userRepository.save(user);
        return "Registration successful";
    }

    public String login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return "Login successful";
        }
        return "Invalid email or password";
    }
}
