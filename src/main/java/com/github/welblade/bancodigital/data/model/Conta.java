package com.github.welblade.bancodigital.data.model;

import com.github.welblade.bancodigital.core.exception.ValorNegativoException;
import com.github.welblade.bancodigital.core.exception.SaldoInsuficienteException;
import lombok.Getter;
import lombok.NonNull;

public abstract class Conta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    private final Cliente cliente;
    @Getter
    private final int agencia;
    @Getter
    private final int numero;
    @Getter
    private double saldo = 0.0;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = Conta.SEQUENCIAL++;
        this.cliente = cliente;
    }

    public void saque(double valor) throws ValorNegativoException, SaldoInsuficienteException {
        if (valor < 0.0) {
            throw new ValorNegativoException();
        }
        if (valor > getSaldo()) {
            throw new SaldoInsuficienteException();
        }
        saldo -= valor;
    }

    public void deposito(double valor) throws ValorNegativoException {
        if (valor < 0.0) {
            throw new ValorNegativoException();
        }
        saldo += valor;
    }

    public void transferir(@NonNull Conta contaDestino, double valor) throws ValorNegativoException, SaldoInsuficienteException {
        saque(valor);
        contaDestino.deposito(valor);
    }

    public String extrato() {
        String extrato = "Titular: %s\n" +
                "Agencia: %d\n" +
                "Numero: %d\n" +
                "Saldo: R$ %.2f";
        return String.format(extrato, cliente.getNome(), agencia, numero, saldo);
    }
}
