package com.example.api_fin.expenses.service;

import com.example.api_fin.expenses.dto.ExpenseDTO;
import com.example.api_fin.expenses.model.Expense;
import java.util.List;
import java.util.UUID;

public interface ExpensesService {

    List<Expense> obtenerTodos();
    Expense obtenerPorId(UUID id);
    Expense addGasto(ExpenseDTO expenseDTO);
    Expense actualizarGasto(UUID id, ExpenseDTO expenseDTO);
    void eliminarGasto(UUID id);
}
