package com.example.api_fin.users.service;

import com.example.api_fin.users.dto.UsuarioDTO;
import com.example.api_fin.users.model.Usuario;
import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    List<Usuario> obtenerTodos();
    Usuario obtenerPorId(UUID id);
    Usuario crearUsuario(Usuario usuario);
    Usuario actualizarUsuario(UUID id, UsuarioDTO usuarioDTO);
    void eliminarUsuario(UUID id);
}
