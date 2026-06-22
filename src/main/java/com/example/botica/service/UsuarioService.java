package com.example.botica.service;

import com.example.botica.Dto.UsuarioRecuperacionDto;
import com.example.botica.Dto.UsuarioRegistroDto;
import com.example.botica.entity.Usuario;

public interface UsuarioService {
    Usuario registrarUsuario(UsuarioRegistroDto registroDto);

    Usuario recuperarContrasena(UsuarioRecuperacionDto usuarioRecuperacionDto);
}
