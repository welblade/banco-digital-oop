package bancodigital.ui;

import bancodigital.core.exception.BancoDigitalException;

import java.util.ArrayList;
import java.util.List;

public class TransferirComando extends Comando {
    private static final int NUMERO_CONTA_ORIGEM = 1;
    private static final int NUMERO_CONTA_DESTINO = 2;
    private static final int VALOR = 3;

    public TransferirComando() {}

    @Override
    public List<String> execute(List<String> args) throws BancoDigitalException {
        List<String> novosArgs = new ArrayList<>();
        String numero = args.get(NUMERO_CONTA_ORIGEM);
        novosArgs.add(numero);

        String numeroDestino = args.get(NUMERO_CONTA_DESTINO);
        novosArgs.add(numeroDestino);

        String valor = args.get(VALOR);
        novosArgs.add(valor);

        return novosArgs;
    }

    @Override
    public String help() {
        return "transferir <numero conta origem> <numero conta destino> <valor>\n\tTransfere da conta de origem para a conta destino o valor (formato decimal 0.0) especificado.";
    }
}
