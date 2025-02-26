package com.example.api.expenses.dto;

import com.example.api.expenses.model.Category;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class ExpenseDTO {

    private UUID usuarioId; // Solo el ID del usuario
    private Double cantidad;
    private String motivo;
    private boolean necesario;
    private Category category;
    private LocalDateTime fecha;
    private String notas;

}