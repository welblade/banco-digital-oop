package bancodigital.domain;

import bancodigital.core.exception.FormatoDeValorInvalido;
import bancodigital.core.exception.TipoDeContaInvalidoException;
import bancodigital.core.exception.ValorNegativoException;
import bancodigital.data.model.Banco;
import bancodigital.data.model.Cliente;
import bancodigital.data.model.Conta;
import bancodigital.data.model.ContaFactory;

import java.util.List;

public class CriarConta extends Operacao {

    private Cliente cliente;
    private String tipo;
    private double saldo;

    CriarConta(Banco banco, List<String> args) throws FormatoDeValorInvalido {
        super(banco, args);
        if (args.size() == 3) {
            cliente = new Cliente(args.get(0));
            tipo = args.get(1);
            try {
                saldo = Double.parseDouble(args.get(2));
            } catch (NumberFormatException exception) {
                throw new FormatoDeValorInvalido();
            }
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
