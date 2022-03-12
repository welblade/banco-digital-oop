package com.github.welblade.bancodigital.ui;

import com.github.welblade.bancodigital.core.exception.BancoDigitalException;
import com.github.welblade.bancodigital.core.exception.ComandoInvalidoException;
import com.github.welblade.bancodigital.data.model.Banco;
import com.github.welblade.bancodigital.domain.Operacao;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CommandLine {
    private static final int COMANDO = 0;

    private final Banco banco;
    private final Map<String, ComandoOperacaoContainer> comandos = new HashMap<>();

    public CommandLine(Banco banco) {
        this.banco = banco;
    }

    public void addComando(String nome, Class<?> comando, Class<?> operacao) {
        comandos.put(nome, new ComandoOperacaoContainer(comando, operacao));
    }

    public void start() {
        System.out.println(String.format("Bem vindo ao %s\nDigite \"ajuda\" para obter os comandos existentes.", banco.getNome()));
        Boolean isContinuar = true;
        while(isContinuar){
            String entrada = prompt("");
            List<String> args = Comando.separaArgumentos(entrada);
            try {
                if(args.size() > 0) {
                    switch (args.get(COMANDO)) {
                        case "sair":
                            isContinuar = false;
                            break;
                        case "ajuda":
                            ajuda();
                            break;
                        default:
                            processarComando(args);
                    }
                }
            } catch (NoSuchMethodException |
                    InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException | InvocationTargetException e) {
                Throwable causa = e.getCause();
                String message = e.getMessage();
                if (causa != null) {
                    message = causa.getMessage();
                }
                System.out.println("✘ " + message);
            } catch (BancoDigitalException e) {
                System.out.println("✘ " + e.getMessage());
            }
        }
    }

    private void processarComando(List<String> args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, BancoDigitalException {
        if(comandos.containsKey(args.get(COMANDO))){
            ComandoOperacaoContainer factory = comandos.get(args.get(COMANDO));
            List<String> argsOperacao = executarComando(factory.comando, args);
            Operacao operacao = novaOperacao(factory.operacao, banco, argsOperacao);
            if(confirmarOperacao(operacao)) {
                operacao.execute();
            }
        } else {
            throw new ComandoInvalidoException(args.get(COMANDO));
        }
    }

    private List<String> executarComando(Class<?> comando, List<String> args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, BancoDigitalException {
        Comando classe = novoComando(comando);
        return classe.execute(args);
    }

    private Comando novoComando(Class<?> comando) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (Comando) comando.getDeclaredConstructor().newInstance();
    }

    private Operacao novaOperacao(Class<?> operacao, Banco banco, List<String> args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (Operacao) operacao.getDeclaredConstructor(Banco.class, List.class).newInstance(banco, args);
    }

    private boolean confirmarOperacao(Operacao operacao) {
        String mensagem = operacao.getMensagemConfirmacao();
        if(mensagem == null) {
            return true;
        }
        System.out.println(mensagem);
        String resposta = prompt("Digite 'sim' para confirmar a operação");
        return resposta.equalsIgnoreCase("sim");
    }

    private String prompt(String mensagem) {
        Scanner input = new Scanner(System.in);
        System.out.println(mensagem);
        System.out.print("◉ ");
        return input.nextLine().trim();
    }

    private void ajuda() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (ComandoOperacaoContainer container : comandos.values()) {
           Comando comando = novoComando(container.comando);
           System.out.println("◌ " + comando.help());
        }
        System.out.println("◌ ajuda\n\tExibe comandos para o programa.");
        System.out.println("◌ sair\n\tFinaliza o programa.");
    }
}
