package com.example.api.expenses.model;

import com.example.api.users.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="expenses")
@Data
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(nullable = false)
    private Double cantidad;

    @Column(length = 100)
    private String motivo;

    @Column(nullable = false)
    private boolean necesario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column
    private String notas;

    public Expense(Usuario usuario, Double cantidad, String motivo, boolean necesario, Category category, LocalDateTime fecha, String notas) {
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.necesario = necesario;
        this.category = category;
        this.fecha = fecha;
        this.notas = notas;
    }

}
