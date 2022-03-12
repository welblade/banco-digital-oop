package com.github.welblade.bancodigital.core.exception;

public class SaldoInsuficienteException extends BancoDigitalException {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente para esta operação.");
    }
}
