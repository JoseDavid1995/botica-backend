package com.example.botica.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String correo;
    private String contrasena;
}