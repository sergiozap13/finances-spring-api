package com.example.api.expenses.controller;

import com.example.api.expenses.dto.ExpenseDTO;
import com.example.api.expenses.model.Expense;
import com.example.api.expenses.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gastos")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @GetMapping
    public List<Expense> obtenerTodos() {
        return this.expensesService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> obtenerGastoPorId(@PathVariable UUID id) {
        try {
            Expense expense = expensesService.obtenerPorId(id);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Expense> crearGasto(@RequestBody ExpenseDTO expenseDTO) {
        return new ResponseEntity<>(expensesService.addGasto(expenseDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> actualizarGasto(@PathVariable UUID id,@RequestBody ExpenseDTO expenseDTO){
        try {
            Expense expenseToUpdate = this.expensesService.actualizarGasto(id,expenseDTO);
            return new ResponseEntity<>( expenseToUpdate, HttpStatus.OK );

        } catch (RuntimeException e) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGasto(@PathVariable UUID id) {
        try {
            this.expensesService.eliminarGasto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }


}
