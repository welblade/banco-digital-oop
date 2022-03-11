package bancodigital.ui;

import bancodigital.core.exception.BancoDigitalException;
import bancodigital.core.exception.TipoDeContaInvalidoException;
import bancodigital.data.model.ContaFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AbrirContaComando extends Comando {
    private static final int TIPO_CONTA = 2;

    public AbrirContaComando() {

    }

    @Override
    public List<String> execute(List<String> args) throws BancoDigitalException {
        List<String> newArgs = new ArrayList<>();
        String tipo = args.get(TIPO_CONTA);
        if (!ContaFactory.isTipoPermitido(tipo)) {
            throw new TipoDeContaInvalidoException();
        }

        newArgs.add(tipo);
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do titular:");
        String titular = input.nextLine();
        newArgs.add(titular);

        System.out.println("Digite o valor inicial depositado na conta: (padrão: 0.00)");
        String valorIncial = input.nextLine();
        newArgs.add(valorIncial);

        return newArgs;
    }

    @Override
    public String help() {
        return "abrir conta <tipo>\t Abre uma conta do <tipo> especificado (poupança ou corrente)";
    }
}
