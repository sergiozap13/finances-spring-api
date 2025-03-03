package com.example.api.users.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class UsuarioDTO {

    private UUID id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String password;
}
