package com.example.botica.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO", schema = "BOTICA")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "APELLIDOS", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "CORREO", unique = true, nullable = false, length = 150)
    private String correo;

    @Column(name = "CONTRASENA", nullable = false, length = 255)
    private String contrasena;

    @Column(name = "ESTADO", precision = 1)
    private Integer estado = 1;

    @Column(name = "DNI", unique = true, nullable = false, length = 8)
    private String dni;
}
