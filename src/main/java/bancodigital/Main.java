package bancodigital;

import bancodigital.data.model.Banco;
import bancodigital.domain.CriaContaOperacao;
import bancodigital.ui.CommandLine;
import bancodigital.ui.CriaContaComando;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Digital da Castelia");
        CommandLine cmd = new CommandLine(banco);
        cmd.addComando("criar", CriaContaComando.class, CriaContaOperacao.class);
        cmd.start();
    }
}
