package com.github.welblade.bancodigital.ui;

import com.github.welblade.bancodigital.core.exception.BancoDigitalException;

import java.util.ArrayList;
import java.util.List;

public class DepositarComando extends Comando {
    private static final int NUMERO_CONTA = 1;
    private static final int VALOR = 2;
    public DepositarComando() {}
    @Override
    public List<String> execute(List<String> args) throws BancoDigitalException {
        List<String> novosArgs = new ArrayList<>();
        String numero = args.get(NUMERO_CONTA);
        novosArgs.add(numero);

        String valor = args.get(VALOR);
        novosArgs.add(valor);

        return novosArgs;
    }

    @Override
    public String help() {
        return "depositar <numero conta> <valor>\n\tDeposita na conta <numero conta> o valor <valor>(formato decimal 0.0) especificado.";
    }
}
