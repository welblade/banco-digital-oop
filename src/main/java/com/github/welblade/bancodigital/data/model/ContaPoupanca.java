package com.github.welblade.bancodigital.data.model;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String extrato() {
        return "=== Extrato Conta Poupança ==\n" + super.extrato();
    }
}
