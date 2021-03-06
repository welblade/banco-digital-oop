package com.github.welblade.bancodigital.domain;

import com.github.welblade.bancodigital.core.exception.BancoDigitalException;
import com.github.welblade.bancodigital.core.exception.QuantidadeInvalidaDeArgumentosParaOperacaoException;
import com.github.welblade.bancodigital.data.model.Banco;

import java.util.List;

public abstract class Operacao {

    protected Banco banco;

    Operacao(Banco banco, List<String> args) {
        this.banco = banco;
    }

    public abstract String getMensagemConfirmacao();

    public abstract void execute() throws BancoDigitalException;

    protected void checkQtdArgumentos(List<String> args, int qtdEsperada) throws BancoDigitalException {
        if (args.size() != qtdEsperada) {
            throw new QuantidadeInvalidaDeArgumentosParaOperacaoException(qtdEsperada, args.size());
        }
    }
}
