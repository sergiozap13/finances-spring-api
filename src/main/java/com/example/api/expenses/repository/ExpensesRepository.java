package com.example.api.expenses.repository;

import com.example.api.expenses.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ExpensesRepository extends JpaRepository<Expense, UUID> {
    List<Expense> findByUsuarioId(UUID usuarioId);
}
