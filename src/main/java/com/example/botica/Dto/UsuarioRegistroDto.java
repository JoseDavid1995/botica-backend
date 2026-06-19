package com.example.botica.Dto;

import lombok.Data;

@Data
public class UsuarioRegistroDto {
    private String dni;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;
}
