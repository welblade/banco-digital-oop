package bancodigital.domain;

import bancodigital.core.exception.*;
import bancodigital.data.model.Banco;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CriaContaOperacaoTest {
    @Mock
    Banco banco;

    @BeforeAll
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testQuantidadeInvalidaDeArgumentos() {
        List<String> args = new ArrayList<>();
        args.add("João Espekito");
        assertThatThrownBy(() -> new CriaContaOperacao(banco, args))
                .isInstanceOf(QuantidadeInvalidaDeArgumentosParaOperacaoException.class)
        ;
    }
    @Test
    void testMensagemConfirmacao() throws BancoDigitalException {
        List<String> args = new ArrayList<>();
        args.add("João Espekito");
        args.add("poupança");
        args.add("0");
        Operacao operacao = new CriaContaOperacao(banco, args);

        String mensagemConfirmacaoAtual = operacao.getMensagemConfirmacao();
        String mensagemConfirmacaoEsperada = "Deseja criar uma conta poupança para o cliente João Espekito com saldo inicial de R$ 0,00?";

        assertThat(mensagemConfirmacaoAtual).isEqualTo(mensagemConfirmacaoEsperada);
    }

    @Test
    void testCriacaoDeOperacaoComParametroSaldoInicialErrado() {
        List<String> args = new ArrayList<>();
        args.add("João E$spekito");
        args.add("cultura");
        args.add("A");

        assertThatThrownBy(() -> new CriaContaOperacao(banco, args))
                .isInstanceOf(FormatoDeValorInvalido.class)
        ;
    }

    @Test
    void testCriacaoDeOperacaoComParametroTipoErrado() throws BancoDigitalException {
        List<String> args = new ArrayList<>();
        args.add("João E$spekito");
        args.add("cultura");
        args.add("0");
        Operacao operacao = new CriaContaOperacao(banco, args);
        assertThatThrownBy(operacao::execute)
                .isInstanceOf(TipoDeContaInvalidoException.class);
    }

    @Test
    void testCriacaoDeOperacaoComSaldoInicialNegativo() throws BancoDigitalException {
        List<String> args = new ArrayList<>();
        args.add("João Espekito");
        args.add("poupança");
        args.add("-1");
        Operacao operacao = new CriaContaOperacao(banco, args);
        assertThatThrownBy(operacao::execute)
                .isInstanceOf(ValorNegativoException.class);
    }
}