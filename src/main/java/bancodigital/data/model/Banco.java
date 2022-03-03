package bancodigital.data.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Banco {
    @Getter
    final private String nome;

    private final List<Conta> contas = new ArrayList<>();

    Banco(String nome){
        this.nome = nome;
    }

    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }

    public void addConta(Conta conta) {
        this.contas.add(conta);
    }
}
