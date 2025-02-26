package com.example.api.expenses.repository;

import com.example.api.expenses.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ExpensesRepository extends JpaRepository<Expense, UUID> {
    List<Expense> findByUsuarioId(UUID usuarioId);

    @Query("FROM Expense e WHERE e.fecha BETWEEN :initialDate AND :finalDate AND e.usuario.id = :userId")
    List<Expense> findMonthExpensesByUserId(
            @Param("initialDate") LocalDateTime initialDate,
            @Param("finalDate") LocalDateTime finalDate,
            @Param("userId") UUID user_id
    );

}
