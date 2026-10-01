package com.shopsphere.controller;

import com.shopsphere.model.user.LoginRequest;
import com.shopsphere.model.user.User;
import com.shopsphere.model.user.UserResponse;
import com.shopsphere.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse registerUser(
            @Valid @RequestBody User user) {

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(
            @Valid @RequestBody LoginRequest loginRequest) {

        String token = userService.loginUser(loginRequest);

        if (token == null) {
            return "Invalid email or password";
        }

        return token;
    }
}

