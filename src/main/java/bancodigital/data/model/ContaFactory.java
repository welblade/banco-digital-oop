package bancodigital.data.model;

import bancodigital.core.exception.TipoDeContaInvalidoException;
import bancodigital.core.exception.ValorNegativoException;

import java.util.Arrays;

public class ContaFactory {
    public static String[] tiposPermitidos = { "poupança", "corrente" };
    public static Boolean isTipoPermitido(String tipo) {
        return Arrays.stream(tiposPermitidos).anyMatch((t) -> t.equals(tipo));
    }
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
