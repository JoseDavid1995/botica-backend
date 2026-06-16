package com.example.botica.controller;

import com.example.botica.Dto.UsuarioRegistroDto;
import com.example.botica.entity.Usuario;
import com.example.botica.request.LoginRequest;
import com.example.botica.response.ApiResponse;
import com.example.botica.service.AuthService;
import com.example.botica.service.UsuarioService;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class usuarioController {
    private final AuthService authService;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getCorreo(), request.getContrasena());
        Map<String, String> data = Collections.singletonMap("token", token);
    
        return ResponseEntity.ok(ApiResponse.ok("Inicio de sesión exitoso", data));
    }

    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse<Usuario>> registrar(@RequestBody UsuarioRegistroDto registroDto) {
        Usuario usuarioCreado = usuarioService.registrarUsuario(registroDto);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Usuario registrado exitosamente", usuarioCreado));
    }
    
}
