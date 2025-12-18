package com.contable.service;

import com.contable.domain.BankAccount;
import com.contable.domain.Category;
import com.contable.domain.Transaction;
import com.contable.domain.enumeration.TransactionType;
import com.contable.repository.BankAccountRepository;
import com.contable.repository.CategoryRepository;
import com.contable.repository.TransactionRepository;
import com.contable.web.rest.dto.ResumenFinancieroDTO;
import com.contable.web.rest.dto.TransaccionRapidaRequestDTO;
import com.contable.web.rest.dto.TransaccionRapidaResponseDTO;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransaccionRapidaService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final BankAccountRepository bankAccountRepository;

    public TransaccionRapidaService(
        TransactionRepository transactionRepository,
        CategoryRepository categoryRepository,
        BankAccountRepository bankAccountRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    /**
     * Registrar transacción rápida
     */
    public TransaccionRapidaResponseDTO registrarTransaccion(TransaccionRapidaRequestDTO request) {
        // Buscar categoría por nombre
        Category category = categoryRepository
            .findAll()
            .stream()
            .filter(c -> c.getName().equalsIgnoreCase(request.getCategoria()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + request.getCategoria()));

        // Buscar cuenta por nombre
        BankAccount bankAccount = bankAccountRepository
            .findAll()
            .stream()
            .filter(b -> b.getName().equalsIgnoreCase(request.getCuenta()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada: " + request.getCuenta()));

        // Crear transacción
        Transaction transaction = new Transaction();
        transaction.setDate(request.getFecha());
        transaction.setAmount(request.getMonto());
        transaction.setType(request.getTipo().equalsIgnoreCase("INCOME") ? TransactionType.INCOME : TransactionType.EXPENSE);
        transaction.setDescription(request.getDescripcion());
        transaction.setCategory(category);
        transaction.setBankAccount(bankAccount);

        // Guardar
        Transaction saved = transactionRepository.save(transaction);

        // Respuesta
        TransaccionRapidaResponseDTO response = new TransaccionRapidaResponseDTO();
        response.setId(saved.getId());
        response.setTipo(saved.getType().toString());
        response.setMonto(saved.getAmount());
        response.setCategoria(saved.getCategory().getName());
        response.setCuenta(saved.getBankAccount().getName());
        response.setDescripcion(saved.getDescription());
        response.setFecha(saved.getDate());
        response.setMensaje("Transacción registrada exitosamente");

        return response;
    }

    /**
     * Obtener resumen financiero
     */
    @Transactional(readOnly = true)
    public ResumenFinancieroDTO obtenerResumen() {
        List<Transaction> transactions = transactionRepository.findAll();

        BigDecimal totalIngresos = transactions
            .stream()
            .filter(t -> t.getType() == TransactionType.INCOME)
            .map(Transaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalGastos = transactions
            .stream()
            .filter(t -> t.getType() == TransactionType.EXPENSE)
            .map(Transaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIngresos.subtract(totalGastos);

        return new ResumenFinancieroDTO(totalIngresos, totalGastos, balance);
    }
}
