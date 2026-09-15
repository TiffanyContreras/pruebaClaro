package com.prueba.claro.service.impl;

import com.prueba.claro.dto.AuthResponse;
import com.prueba.claro.dto.LoginRequest;
import com.prueba.claro.dto.RegisterRequest;
import com.prueba.claro.entity.UsuarioEntity;
import com.prueba.claro.repository.UsuarioRepository;
import com.prueba.claro.service.AuthService;
import com.prueba.claro.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese username");
        }

        UsuarioEntity usuario = UsuarioEntity.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role("USER")
                .build();

        usuarioRepository.save(usuario);

        return new AuthResponse(
                jwtService.generateToken(usuario.getUsername()),
                "Bearer"
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password()
                    )
            );
        } catch (Exception ex) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        return new AuthResponse(
                jwtService.generateToken(request.username()),
                "Bearer"
        );
    }
}
