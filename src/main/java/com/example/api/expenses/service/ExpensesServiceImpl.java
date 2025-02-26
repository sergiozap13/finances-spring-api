package com.example.api.expenses.service;

import com.example.api.expenses.dto.ExpenseDTO;
import com.example.api.expenses.model.Expense;
import com.example.api.expenses.repository.ExpensesRepository;
import com.example.api.users.model.Usuario;
import com.example.api.users.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ExpensesServiceImpl implements ExpensesService{

    // En el servicio inyectamos por constructor nuestro repository
    private final ExpensesRepository expensesRepository;
    private final UsuarioRepository usuarioRepository;

    public ExpensesServiceImpl(ExpensesRepository expensesRepository, UsuarioRepository usuarioRepository) {

        this.expensesRepository = expensesRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Expense> obtenerTodos() {
        return this.expensesRepository.findAll();
    }

    @Override
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
