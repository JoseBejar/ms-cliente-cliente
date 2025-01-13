package com.pruebatecnica.jose.controller;

import com.pruebatecnica.jose.dto.AuthResponse;
import com.pruebatecnica.jose.model.LoginRequest;
import com.pruebatecnica.jose.model.RegisterRequest;
import com.pruebatecnica.jose.model.User;
import com.pruebatecnica.jose.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping(value = "/list")
    ResponseEntity<List<User>> listCliente() {
        return ResponseEntity
                .ok()
                .body( userService.listCliente());
    }

    @PostMapping(value = "/login")
    ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity
                .ok()
                .body((AuthResponse) userService.login(request));
    }

    @PostMapping(value = "/register")
    ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity
                .ok()
                .body((AuthResponse) userService.register(request));
    }



}


