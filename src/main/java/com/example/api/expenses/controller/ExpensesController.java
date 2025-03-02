package com.example.api.expenses.controller;

import com.example.api.expenses.dto.ExpenseDTO;
import com.example.api.expenses.model.Expense;
import com.example.api.expenses.service.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/gastos")
@AllArgsConstructor
public class ExpensesController {

    // se autoinyecta en el constructor con todos los args
    private ExpensesService expensesService;

    @GetMapping
    public List<Expense> obtenerTodos() {
        return this.expensesService.obtenerTodos();
    }


    // para obtener los gastos del mes actual dado un usuario
    // api/gastos/usuario/1234/m
    @GetMapping(value = "/usuario/{user_id}/m")
    public List<Expense> getExpensesActualMonthByUserId(@PathVariable UUID user_id){
        return expensesService.getActualMonthExpensesByUserId(user_id);
    }

    // para obtener los gastos de un mes dado para un usuario
    // api/gastos/usuario/1234/m/2
    @GetMapping(value = "/usuario/{user_id}/m/{month}")
    public List<Expense> getExpensesMonthByUserId(@PathVariable UUID user_id, @PathVariable int month){
        return expensesService.getMonthExpensesByUserId(user_id, month);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> obtenerGastoPorId(@PathVariable UUID id) {
        Expense expense = expensesService.obtenerPorId(id);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Expense> crearGasto(@RequestBody ExpenseDTO expenseDTO) {
        return new ResponseEntity<>(expensesService.addGasto(expenseDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> actualizarGasto(@PathVariable UUID id,@RequestBody ExpenseDTO expenseDTO){
        Expense expenseToUpdate = this.expensesService.actualizarGasto(id,expenseDTO);
        return new ResponseEntity<>( expenseToUpdate, HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGasto(@PathVariable UUID id) {
        this.expensesService.eliminarGasto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
