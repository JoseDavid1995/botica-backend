package com.example.botica.service.serviceImpl;

import com.example.botica.entity.Usuario;
import com.example.botica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // Buscamos al usuario por su correo
        Usuario usuario = usuarioRepository.findByCorreo(correo)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));

            if (usuario.getEstado() != 1) {
            throw new DisabledException("Cuenta desactivada");
        }

        // Retornamos un objeto de Spring Security
        return new User(
            usuario.getCorreo(), 
            usuario.getContrasena(), 
            Collections.emptyList() // Aquí podrías agregar roles/autoridades
        );
    }
}
