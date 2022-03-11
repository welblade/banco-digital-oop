package bancodigital.ui;

import bancodigital.core.exception.BancoDigitalException;
import bancodigital.data.model.Banco;
import bancodigital.domain.Operacao;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CommandLine {
    private static int COMANDO = 0;

    private Banco banco;
    private Map<String, ComandoOperacaoContainer> comandos = new HashMap<>();

    public CommandLine(Banco banco) {
        this.banco = banco;
    }

    public void addComando(String nome, Class<?> comando, Class<?> operacao) {
        comandos.put(nome, new ComandoOperacaoContainer(comando, operacao));
    }

    public void start() {
        Scanner input = new Scanner(System.in);
        while(true){
            String entrada = input.nextLine();
            List<String> args = Comando.separaArgumentos(entrada);
            if(args.size() > 0) {
                if(comandos.containsKey(args.get(COMANDO))){
                    ComandoOperacaoContainer factory = comandos.get(args.get(COMANDO));
                    try {
                        Comando comando = (Comando) factory.comando.getDeclaredConstructor().newInstance();
                        List<String> argsOperacao = comando.execute(args);
                        Operacao operacao = (Operacao) factory.operacao.getDeclaredConstructor().newInstance(banco, argsOperacao);
                        operacao.execute();
                    } catch (NoSuchMethodException |
                            InvocationTargetException |
                            IllegalAccessException |
                            InstantiationException e) {
                        e.printStackTrace();
                    } catch (BancoDigitalException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    break;
                }
            }
        }
    }
}
