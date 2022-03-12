package bancodigital.ui;

import bancodigital.core.exception.BancoDigitalException;

import java.util.ArrayList;
import java.util.List;

public class ExtratoComando extends Comando {
    private static final int NUMERO_CONTA = 1;
    public ExtratoComando() {}
    @Override
    public List<String> execute(List<String> args) throws BancoDigitalException {
        List<String> novosArgs = new ArrayList<>();
        String numero = args.get(NUMERO_CONTA);
        novosArgs.add(numero);

        return novosArgs;
    }

    @Override
    public String help() {
        return "extrato <conta numero>\t\t\t\tExibe o extrato da conta <conta numero>.";
    }
}
