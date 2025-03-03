package com.example.api.users.service;

import com.example.api.users.dto.UsuarioDTO;
import com.example.api.users.model.Usuario;
import com.example.api.users.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    // inyectamos por constructor nuestro repository
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
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
