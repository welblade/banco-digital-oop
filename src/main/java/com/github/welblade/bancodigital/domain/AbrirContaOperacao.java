package com.github.welblade.bancodigital.domain;

import com.github.welblade.bancodigital.core.exception.BancoDigitalException;
import com.github.welblade.bancodigital.core.exception.ValorNegativoException;
import com.github.welblade.bancodigital.data.model.Banco;
import com.github.welblade.bancodigital.data.model.Cliente;
import com.github.welblade.bancodigital.data.model.Conta;
import com.github.welblade.bancodigital.data.model.ContaFactory;
import com.github.welblade.bancodigital.core.exception.FormatoDeValorInvalido;
import com.github.welblade.bancodigital.core.exception.TipoDeContaInvalidoException;

import java.util.List;

public class AbrirContaOperacao extends Operacao {
    private static final int TIPO = 0;
    private static final int CLIENTE = 1;
    private static final int SALDO = 2;

    protected static final int QTD_ARGUMENTOS = 3;

    private Cliente cliente;
    private String tipo;
    private double saldo;

    public AbrirContaOperacao(Banco banco, List<String> args) throws BancoDigitalException {
        super(banco, args);

        checkQtdArgumentos(args, QTD_ARGUMENTOS);

        cliente = new Cliente(args.get(CLIENTE));
        tipo = args.get(TIPO);
        try {
            saldo = Double.parseDouble(args.get(SALDO));
        } catch (NumberFormatException exception) {
            throw new FormatoDeValorInvalido();
        }
    }

    @Override
    public String getMensagemConfirmacao() {
        return String.format(
                "Deseja abrir uma conta %s para o cliente %s com saldo inicial de R$ %.2f?",
                tipo, cliente.getNome(), saldo
        );
    }

    @Override
    public void execute() throws ValorNegativoException, TipoDeContaInvalidoException {
        ContaFactory factory = new ContaFactory();
        Conta conta = factory.criar(cliente, tipo, saldo);
        banco.addConta(conta);
    }
}
