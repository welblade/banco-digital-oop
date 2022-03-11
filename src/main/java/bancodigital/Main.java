package bancodigital;

import bancodigital.data.model.Banco;
import bancodigital.domain.AbrirContaOperacao;
import bancodigital.ui.CommandLine;
import bancodigital.ui.AbrirContaComando;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Digital da Castelia");
        CommandLine cmd = new CommandLine(banco);
        cmd.addComando("abrir", AbrirContaComando.class, AbrirContaOperacao.class);
        cmd.start();
    }
}
