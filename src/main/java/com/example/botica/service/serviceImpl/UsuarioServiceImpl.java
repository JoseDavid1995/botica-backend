package com.example.botica.service.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.botica.Dto.UsuarioRecuperacionDto;
import com.example.botica.Dto.UsuarioRegistroDto;
import com.example.botica.entity.Usuario;
import com.example.botica.repository.UsuarioRepository;
import com.example.botica.service.UsuarioService;
import com.example.botica.util.Constantes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Usuario recuperarContrasena(UsuarioRecuperacionDto usuarioRecuperacionDto) {
        Usuario usuarioExistente = usuarioRepository.findByDni(usuarioRecuperacionDto.getDni())
                .orElseThrow(() -> new RuntimeException("El dni no se encuentra registrado en el sistema."));

        String passwordHasheada = passwordEncoder.encode(usuarioRecuperacionDto.getContrasena());
        usuarioExistente.setContrasena(passwordHasheada);

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    @Transactional
    public Usuario registrarUsuario(UsuarioRegistroDto registroDto) {
        if (usuarioRepository.existsByDni(registroDto.getDni())) {
            throw new RuntimeException("El dni ya se encuentra registrado en el sistema.");
        }

        if (usuarioRepository.existsByCorreo(registroDto.getCorreo())) {
            throw new RuntimeException("El correo ya se encuentra registrado en el sistema.");
        }

        Usuario usuario = new Usuario();
        usuario.setDni(registroDto.getDni());
        usuario.setNombre(registroDto.getNombres());
        usuario.setApellidos(registroDto.getApellidos());
        usuario.setCorreo(registroDto.getCorreo());

        String passwordHasheada = passwordEncoder.encode(registroDto.getContrasena());
        usuario.setContrasena(passwordHasheada);

        usuario.setEstado(Constantes.ESTADO_ACTIVO);

        return usuarioRepository.save(usuario);
    }

}
