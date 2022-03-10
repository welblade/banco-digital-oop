package bancodigital.domain;

import bancodigital.core.exception.*;
import bancodigital.data.model.Banco;
import bancodigital.data.model.Cliente;
import bancodigital.data.model.Conta;
import bancodigital.data.model.ContaFactory;

import java.util.List;

public class CriarConta extends Operacao {
    private static final int CLIENTE = 0;
    private static final int TIPO = 1;
    private static final int SALDO = 2;

    protected static final int QTD_ARGUMENTOS = 3;

    private Cliente cliente;
    private String tipo;
    private double saldo;

    public CriarConta(Banco banco, List<String> args) throws BancoDigitalException {
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
                "Deseja criar uma conta %s para o cliente %s com saldo inicial de R$ %.2f?",
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
