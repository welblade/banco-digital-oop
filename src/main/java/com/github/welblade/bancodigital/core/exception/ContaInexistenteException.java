package com.github.welblade.bancodigital.core.exception;

public class ContaInexistenteException extends BancoDigitalException {
    public ContaInexistenteException() {
        super("Não existe uma conta com o número informado.");
    }
}
