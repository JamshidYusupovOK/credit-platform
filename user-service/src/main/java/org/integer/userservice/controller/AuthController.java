package org.integer.userservice.controller;


import lombok.RequiredArgsConstructor;
import org.integer.userservice.dto.AuthResponse;
import org.integer.userservice.dto.LoginRequest;
import org.integer.userservice.dto.RegisterRequest;
import org.integer.userservice.entity.User;
import org.integer.userservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public AuthResponse registerUser(@RequestBody RegisterRequest request){
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return userService.login(request);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        userService.logout(token);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
