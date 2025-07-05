package com.asalkarhealthyhub.controller;

import com.asalkarhealthyhub.entity.User;
import com.asalkarhealthyhub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> body) {
        return authService.login(body.get("email"), body.get("password"));
    }
}
