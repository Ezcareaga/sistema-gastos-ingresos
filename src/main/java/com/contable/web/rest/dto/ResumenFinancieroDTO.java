package com.contable.web.rest.dto;

import java.math.BigDecimal;

public class ResumenFinancieroDTO {

    private BigDecimal totalIngresos;
    private BigDecimal totalGastos;
    private BigDecimal balance;

    public ResumenFinancieroDTO(BigDecimal totalIngresos, BigDecimal totalGastos, BigDecimal balance) {
        this.totalIngresos = totalIngresos;
        this.totalGastos = totalGastos;
        this.balance = balance;
    }

    // Getters y Setters
    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public BigDecimal getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(BigDecimal totalGastos) {
        this.totalGastos = totalGastos;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
