package com.github.welblade.bancodigital.data.model;

import com.github.welblade.bancodigital.core.exception.ContaInexistenteException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Banco {
    @Getter
    final private String nome;

    private final List<Conta> contas = new ArrayList<>();

    public Banco(String nome){
        this.nome = nome;
    }

    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }

    public void addConta(Conta conta) {
        this.contas.add(conta);
    }

    public Conta findConta(int numeroConta) throws ContaInexistenteException {
        return contas.stream()
                .filter((conta) -> conta.getNumero() == numeroConta)
                .findFirst()
                .orElseThrow(ContaInexistenteException::new);
    }
}
