package com.github.welblade.bancodigital.data.model;

public class ContaCorrente extends Conta {

    ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String extrato() {
        return "=== Extrato Conta Corrente ==\n" + super.extrato();
    }
}
