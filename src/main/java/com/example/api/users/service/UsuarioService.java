package com.example.api.users.service;

import com.example.api.users.dto.UsuarioDTO;
import com.example.api.users.model.Usuario;
import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    List<Usuario> obtenerTodos();
    Usuario obtenerPorId(UUID id);
    Usuario crearUsuario(Usuario usuario);
    Usuario actualizarUsuario(UUID id, UsuarioDTO usuarioDTO);
    void eliminarUsuario(UUID id);
}
