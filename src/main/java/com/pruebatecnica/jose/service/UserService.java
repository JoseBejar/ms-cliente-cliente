package com.pruebatecnica.jose.service;

import com.pruebatecnica.jose.dto.AuthResponse;
import com.pruebatecnica.jose.model.LoginRequest;
import com.pruebatecnica.jose.model.RegisterRequest;
import com.pruebatecnica.jose.model.User;

import java.util.List;

public interface UserService {
 List<User> listCliente();

 AuthResponse login(LoginRequest request);


 AuthResponse register(RegisterRequest request);


}
