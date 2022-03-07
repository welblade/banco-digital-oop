package bancodigital.domain;

import bancodigital.core.exception.FormatoDeValorInvalido;
import bancodigital.core.exception.QuantidadeInvalidaDeArgumentosParaOperacaoException;
import bancodigital.core.exception.TipoDeContaInvalidoException;
import bancodigital.core.exception.ValorNegativoException;
import bancodigital.data.model.Banco;
import bancodigital.data.model.Cliente;
import bancodigital.data.model.Conta;
import bancodigital.data.model.ContaFactory;

import java.util.List;

public class CriarConta extends Operacao {
    private static final int CLIENTE = 0;
    private static final int TIPO = 1;
    private static final int SALDO = 2;
    private static final int QTD_ARGUMENTOS = 3;

    private Cliente cliente;
    private String tipo;
    private double saldo;

    public CriarConta(Banco banco, List<String> args) throws FormatoDeValorInvalido, QuantidadeInvalidaDeArgumentosParaOperacaoException {
        super(banco, args);
        if (args.size() == QTD_ARGUMENTOS) {
            cliente = new Cliente(args.get(CLIENTE));
            tipo = args.get(TIPO);
            try {
                saldo = Double.parseDouble(args.get(SALDO));
            } catch (NumberFormatException exception) {
                throw new FormatoDeValorInvalido();
            }
        }else {
            throw new QuantidadeInvalidaDeArgumentosParaOperacaoException(QTD_ARGUMENTOS, args.size());
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
