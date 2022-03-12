package bancodigital.domain;

import bancodigital.core.exception.BancoDigitalException;
import bancodigital.core.exception.ContaInexistenteException;
import bancodigital.data.model.Banco;
import bancodigital.data.model.Conta;

import java.util.List;

public class ExtratoOperacao extends Operacao {
    private static final int NUMERO_CONTA = 0;
    private static final int QTD_ARGUMENTOS = 1;

    private final Conta conta;

    public ExtratoOperacao(Banco banco, List<String> args) throws BancoDigitalException {
        super(banco, args);
        checkQtdArgumentos(args, QTD_ARGUMENTOS);

        try {
            int numeroConta = Integer.parseInt(args.get(NUMERO_CONTA));
            conta = banco.findConta(numeroConta);
        } catch (NullPointerException exception) {
            throw new ContaInexistenteException();
        }
    }

    @Override
    public String getMensagemConfirmacao() {
        return null;
    }

    @Override
    public void execute() throws BancoDigitalException {
        System.out.println(conta.extrato());
    }
}
