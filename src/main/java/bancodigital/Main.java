package bancodigital;

import bancodigital.data.model.Banco;
import bancodigital.domain.*;
import bancodigital.ui.*;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Digital da Castelia");
        CommandLine cmd = new CommandLine(banco);
        cmd.addComando("abrir", AbrirContaComando.class, AbrirContaOperacao.class);
        cmd.addComando("depositar", DepositarComando.class, DepositarOperacao.class);
        cmd.addComando("extrato", ExtratoComando.class, ExtratoOperacao.class);
        cmd.addComando("sacar", SacarComando.class, SacarOperacao.class);
        cmd.addComando("transferir", TransferirComando.class, TransferirOperacao.class);
        cmd.start();
    }
}
