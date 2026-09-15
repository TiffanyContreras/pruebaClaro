package com.prueba.claro.service;

import com.prueba.claro.dto.AuthResponse;
import com.prueba.claro.dto.LoginRequest;
import com.prueba.claro.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
