package bancodigital.domain;

import bancodigital.core.exception.BancoDigitalException;
import bancodigital.core.exception.ContaInexistenteException;
import bancodigital.core.exception.FormatoDeValorInvalido;
import bancodigital.data.model.Banco;
import bancodigital.data.model.Conta;

import java.util.List;

public class SacarOperacao extends Operacao {
    protected static final int QTD_ARGUMENTOS = 2;
    private static final int NUMERO_CONTA = 0;
    private static final int VALOR = 1;

    private final Conta conta;
    private final double valor;

    public SacarOperacao(Banco banco, List<String> args) throws BancoDigitalException {
        super(banco, args);

        checkQtdArgumentos(args, QTD_ARGUMENTOS);

        try {
            int numeroConta = Integer.parseInt(args.get(NUMERO_CONTA));
            conta = banco.findConta(numeroConta);
            valor = Double.parseDouble(args.get(VALOR));
        } catch (NullPointerException exception) {
            throw new ContaInexistenteException();
        } catch (NumberFormatException exception) {
            throw new FormatoDeValorInvalido();
        }
    }

    @Override
    public String getMensagemConfirmacao() {
        return String.format("Confirmação de saque de R$ %.2f na conta número %d", valor, conta.getNumero());
    }

    @Override
    public void execute() throws BancoDigitalException {
        conta.saque(valor);
    }
}
