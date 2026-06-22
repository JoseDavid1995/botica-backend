package com.example.botica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.botica.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByDni(String dni);

    Optional<Usuario> findByDni(String dni);

    boolean existsByCorreo(String correo);

    Optional<Usuario> findByCorreo(String correo);

}
