package bancodigital.data.model;

import bancodigital.core.exception.TipoDeContaInvalidoException;
import bancodigital.core.exception.ValorNegativoException;

public class ContaFactory {
    public Conta criar(Cliente cliente, String tipo, double saldoInicial) throws TipoDeContaInvalidoException, ValorNegativoException {
        Conta conta;
        switch (tipo) {
            case "poupança":
                conta = new ContaPoupanca(cliente);
                break;
            case "corrente":
                conta = new ContaCorrente(cliente);
                break;
            default:
                throw new TipoDeContaInvalidoException();
        }
        conta.deposito(saldoInicial);
        return conta;
    }

}
