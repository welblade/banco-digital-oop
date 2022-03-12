package bancodigital.domain;

import bancodigital.core.exception.BancoDigitalException;
import bancodigital.core.exception.ContaInexistenteException;
import bancodigital.core.exception.FormatoDeValorInvalido;
import bancodigital.data.model.Banco;
import bancodigital.data.model.Conta;

import java.util.List;

public class TransferirOperacao extends Operacao {
    protected static final int QTD_ARGUMENTOS = 3;
    private static final int NUMERO_CONTA_ORIGEM = 0;
    private static final int NUMERO_CONTA_DESTINO = 1;
    private static final int VALOR = 2;

    private final Conta contaOrigem;
    private final Conta contaDestino;
    private final double valor;

    public TransferirOperacao(Banco banco, List<String> args) throws BancoDigitalException {
        super(banco, args);
        checkQtdArgumentos(args, QTD_ARGUMENTOS);
        try {
            int numeroContaOrigem = Integer.parseInt(args.get(NUMERO_CONTA_ORIGEM));
            contaOrigem = banco.findConta(numeroContaOrigem);

            int numeroContaDestino = Integer.parseInt(args.get(NUMERO_CONTA_DESTINO));
            contaDestino = banco.findConta(numeroContaDestino);

            valor = Double.parseDouble(args.get(VALOR));
        } catch (NullPointerException exception) {
            throw new ContaInexistenteException();
        } catch (NumberFormatException exception) {
            throw new FormatoDeValorInvalido();
        }
    }

    @Override
    public String getMensagemConfirmacao() {
        return String.format("Confirmação de tranferência de R$ %.2f da conta %d para a conta %d",
                valor, contaOrigem.getNumero(), contaDestino.getNumero()
        );
    }

    @Override
    public void execute() throws BancoDigitalException {
        contaOrigem.transferir(contaDestino, valor);
    }
}
