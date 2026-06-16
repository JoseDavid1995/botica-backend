package com.example.botica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.botica.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // 1. Error de credenciales (correo o contraseña incorrectos)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentials(BadCredentialsException ex) {
        return buildResponse("Correo o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
    }

    // 2. Usuario no encontrado
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(UsernameNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 3. Usuario inactivo (Estado != 1)
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ApiResponse<Object>> handleDisabled(DisabledException ex) {
        return buildResponse("Tu cuenta no está activa, contacta con soporte", HttpStatus.FORBIDDEN);
    }

    // 4. Cualquier otro error inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception ex) {
        // Loguear el error real en consola para que tú lo veas
        ex.printStackTrace(); 
        return buildResponse("Ocurrió un error inesperado en el servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Método auxiliar para construir la respuesta usando ApiResponse
    private ResponseEntity<ApiResponse<Object>> buildResponse(String message, HttpStatus status) {
        ApiResponse<Object> response = ApiResponse.error(message);
        return new ResponseEntity<>(response, status);
    }

    // Añade esto a tu GlobalExceptionHandler para capturar errores de negocio
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}