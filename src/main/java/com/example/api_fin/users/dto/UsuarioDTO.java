package com.example.api_fin.users.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter @Setter
public class UsuarioDTO {

    private UUID id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String password;
}
