package com.example.api.expenses.service;

import com.example.api.expenses.dto.ExpenseDTO;
import com.example.api.expenses.model.Expense;
import java.util.List;
import java.util.UUID;

public interface ExpensesService {

    List<Expense> obtenerTodos();
    List<Expense> getMonthExpensesByUserId( UUID user_id, int month );
    List<Expense> getActualMonthExpensesByUserId( UUID user_id );
    Expense obtenerPorId(UUID id);
    Expense addGasto(ExpenseDTO expenseDTO);
    Expense actualizarGasto(UUID id, ExpenseDTO expenseDTO);
    void eliminarGasto(UUID id);
}
