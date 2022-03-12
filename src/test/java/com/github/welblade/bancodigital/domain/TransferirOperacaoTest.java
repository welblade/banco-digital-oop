package com.github.welblade.bancodigital.domain;

import com.github.welblade.bancodigital.core.exception.BancoDigitalException;
import com.github.welblade.bancodigital.core.exception.ContaInexistenteException;
import com.github.welblade.bancodigital.core.exception.FormatoDeValorInvalido;
import com.github.welblade.bancodigital.core.exception.QuantidadeInvalidaDeArgumentosParaOperacaoException;
import com.github.welblade.bancodigital.data.model.Banco;
import com.github.welblade.bancodigital.data.model.ContaCorrente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransferirOperacaoTest {
    @Mock
    Banco banco;
    @Mock
    ContaCorrente contaOrigem;
    @Mock
    ContaCorrente contaDestino;

    @BeforeAll
    public void init() throws ContaInexistenteException {
        MockitoAnnotations.openMocks(this);

        when(contaOrigem.getNumero()).thenReturn(777);
        when(contaDestino.getNumero()).thenReturn(999);

        when(banco.findConta(777)).thenReturn(contaOrigem);
        when(banco.findConta(999)).thenReturn(contaDestino);
    }

    @Test
    void saqueQuantidadeArgumentosErrada() {
        List<String> args = new ArrayList<>();
        args.add("888");
        assertThatThrownBy(() -> new TransferirOperacao(banco, args))
                .isInstanceOf(QuantidadeInvalidaDeArgumentosParaOperacaoException.class);
    }

    @Test
    void testValorInvalido() {
        List<String> args = new ArrayList<>();
        args.add("777");
        args.add("999");
        args.add("A");
        assertThatThrownBy(() -> new TransferirOperacao(banco, args))
                .isInstanceOf(FormatoDeValorInvalido.class);
    }

    @Test
    void getMensagemConfirmacao() throws BancoDigitalException {
        List<String> args = new ArrayList<>();
        args.add("777");
        args.add("999");
        args.add("70.0");
        Operacao operacao = new TransferirOperacao(banco, args);

        String mensagemEsperada = "Confirmação de tranferência de R$ 70,00 da conta 777 para a conta 999";
        String mensagemAtual = operacao.getMensagemConfirmacao();

        assertThat(mensagemAtual).isEqualTo(mensagemEsperada);
    }

    @Test
    void execute() throws BancoDigitalException {
        List<String> args = new ArrayList<>();
        args.add("777");
        args.add("999");
        args.add("70.0");
        Operacao operacao = new TransferirOperacao(banco, args);

        operacao.execute();
        verify(contaOrigem, times(1)).transferir(contaDestino, 70.0);
    }
}