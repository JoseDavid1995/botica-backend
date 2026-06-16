package com.example.botica.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.botica.entity.Usuario;
import com.example.botica.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService; // Clase que crearemos después
    private final AuthenticationManager authenticationManager;

    public String login(String correo, String contrasena) {
        // 1. Autenticar credenciales
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(correo, contrasena)
        );

        // 2. Buscar usuario en DB
        Usuario usuario = usuarioRepository.findByCorreo(correo)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Generar y devolver el token
        return jwtService.generateToken(usuario);
    }
}
