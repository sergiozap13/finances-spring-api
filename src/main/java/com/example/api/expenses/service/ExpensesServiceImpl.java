package com.example.api.expenses.service;

import com.example.api.expenses.dto.ExpenseDTO;
import com.example.api.expenses.model.Expense;
import com.example.api.expenses.repository.ExpensesRepository;
import com.example.api.users.model.Usuario;
import com.example.api.users.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExpensesServiceImpl implements ExpensesService{

    // En el servicio inyectamos por constructor nuestro repository
    private final ExpensesRepository expensesRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Expense> obtenerTodos() {
        return this.expensesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Expense> getMonthExpensesByUserId( UUID userId, int month ) {
        usuarioRepository.findById(userId)
                .orElseThrow( () -> new RuntimeException("Usuario no encontrado") );

        LocalDateTime initialDate = LocalDateTime.of(LocalDateTime.now().getYear(), month, 1, 0, 0, 0);

        LocalDateTime finalDate = initialDate.plusMonths(1);

        return expensesRepository.findMonthExpensesByUserId(initialDate, finalDate, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Expense> getActualMonthExpensesByUserId( UUID userId ) {

        usuarioRepository.findById(userId)
                .orElseThrow( () -> new RuntimeException("Usuario no encontrado") );

        // colocamos el mes actual
        LocalDateTime initialDate = LocalDateTime.of(LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(), 1, 0, 0, 0);

        LocalDateTime finalDate = initialDate.plusMonths(1);

        return expensesRepository.findMonthExpensesByUserId(initialDate, finalDate, userId);

    }

    @Override
    @Transactional(readOnly = true)
    public Expense obtenerPorId(UUID id) {
        return this.expensesRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("No se pudo obtener el gasto " + id) );
    }

    @Override
    public Expense addGasto(ExpenseDTO expenseDTO) {
        Usuario usuario = usuarioRepository.findById( expenseDTO.getUsuarioId() )
                .orElseThrow( () -> new RuntimeException("Usuario no encontrado") );

        Expense expense = new Expense( usuario, expenseDTO.getCantidad(), expenseDTO.getMotivo(),
                expenseDTO.isNecesario(), expenseDTO.getCategory(), expenseDTO.getFecha(), expenseDTO.getNotas());

        return this.expensesRepository.save(expense);
    }

    @Override
    public Expense actualizarGasto(UUID id, ExpenseDTO expenseDTO) {
        Expense expenseToUpdate = this.expensesRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("No se encontró el gasto a actualizar " + id) );

        Usuario usuario = this.usuarioRepository.findById(expenseDTO.getUsuarioId())
                        .orElseThrow( () -> new RuntimeException("No se encontró el usuario del gasto a actualizar " + expenseDTO.getUsuarioId()) );

        expenseToUpdate.setUsuario( usuario );
        expenseToUpdate.setCantidad( expenseDTO.getCantidad() );
        expenseToUpdate.setMotivo( expenseDTO.getMotivo() );
        expenseToUpdate.setNecesario( expenseDTO.isNecesario() );
        expenseToUpdate.setCategory( expenseDTO.getCategory() );
        expenseToUpdate.setFecha( expenseDTO.getFecha() );
        expenseToUpdate.setNotas( expenseDTO.getNotas() );

        return this.expensesRepository.save(expenseToUpdate);
    }

    @Override
    public void eliminarGasto(UUID id) {
        this.expensesRepository.deleteById(id);
    }

}
