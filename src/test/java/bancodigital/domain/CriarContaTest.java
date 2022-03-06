package bancodigital.domain;

import bancodigital.core.exception.FormatoDeValorInvalido;
import bancodigital.core.exception.TipoDeContaInvalidoException;
import bancodigital.core.exception.ValorNegativoException;
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
class CriarContaTest {
    @Mock
    Banco banco;

    @BeforeAll
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMensagemConfirmacao() throws FormatoDeValorInvalido {
        List<String> args = new ArrayList<>();
        args.add("João Espekito");
        args.add("poupança");
        args.add("0");
        Operacao operacao = new CriarConta(banco, args);

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

        assertThatThrownBy(() -> new CriarConta(banco, args))
                .isInstanceOf(FormatoDeValorInvalido.class)
        ;
    }

    @Test
    void testCriacaoDeOperacaoComParametroTipoErrado() throws FormatoDeValorInvalido {
        List<String> args = new ArrayList<>();
        args.add("João E$spekito");
        args.add("cultura");
        args.add("0");
        Operacao operacao = new CriarConta(banco, args);
        assertThatThrownBy(operacao::execute)
                .isInstanceOf(TipoDeContaInvalidoException.class);
    }

    @Test
    void testCriacaoDeOperacaoComSaldoInicialNegativo() throws FormatoDeValorInvalido {
        List<String> args = new ArrayList<>();
        args.add("João Espekito");
        args.add("poupança");
        args.add("-1");
        Operacao operacao = new CriarConta(banco, args);
        assertThatThrownBy(operacao::execute)
                .isInstanceOf(ValorNegativoException.class);
    }
}