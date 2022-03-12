package bancodigital.ui;

import bancodigital.core.exception.BancoDigitalException;

import java.util.ArrayList;
import java.util.List;

public class SacarComando extends Comando {
    private static final int NUMERO_CONTA = 1;
    private static final int VALOR = 2;
    public SacarComando() {}

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
        return "sacar <numero conta> <valor>\n\tSaca da conta <numero conta> o valor <valor>(formato decimal 0.0) especificado.";
    }
}
