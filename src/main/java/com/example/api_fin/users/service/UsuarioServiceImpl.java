package com.example.api_fin.users.service;

import com.example.api_fin.users.dto.UsuarioDTO;
import com.example.api_fin.users.model.Usuario;
import com.example.api_fin.users.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    // inyectamos por constructor nuestro repository
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(UUID id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = obtenerPorId(id);
        usuarioExistente.setNombre(usuarioDTO.getNombre());
        usuarioExistente.setApellido1(usuarioDTO.getApellido1());
        usuarioExistente.setApellido2(usuarioDTO.getApellido2());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuarioExistente.setPassword(usuarioDTO.getPassword());
        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void eliminarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
