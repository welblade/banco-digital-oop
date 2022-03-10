package bancodigital.domain;

import bancodigital.core.exception.BancoDigitalException;
import bancodigital.core.exception.ContaInexistenteException;
import bancodigital.core.exception.FormatoDeValorInvalido;
import bancodigital.core.exception.QuantidadeInvalidaDeArgumentosParaOperacaoException;
import bancodigital.data.model.Banco;
import bancodigital.data.model.ContaCorrente;
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
class SacarTest {
    @Mock
    Banco banco;
    @Mock
    ContaCorrente conta;

    @BeforeAll
    public void init() throws ContaInexistenteException {
        MockitoAnnotations.openMocks(this);

        when(conta.getNumero()).thenReturn(777);

        when(banco.findConta(777)).thenReturn(conta);
    }

    @Test
    void saqueQuantidadeArgumentosErrada() {
        List<String> args = new ArrayList<>();
        args.add("888");
        assertThatThrownBy(() -> new Sacar(banco, args))
                .isInstanceOf(QuantidadeInvalidaDeArgumentosParaOperacaoException.class);
    }

    @Test
    void getMensagemConfirmacao() throws BancoDigitalException {
        List<String> args = new ArrayList<>();
        args.add("777");
        args.add("70.0");
        Operacao operacao = new Sacar(banco, args);

        String mensagemEsperada = "Confirmação de saque de R$ 70,00 na conta número 777";
        String mensagemAtual = operacao.getMensagemConfirmacao();

        assertThat(mensagemAtual).isEqualTo(mensagemEsperada);
    }

    @Test
    void testValorInvalido() {
        List<String> args = new ArrayList<>();
        args.add("777");
        args.add("A");
        assertThatThrownBy(() -> new Sacar(banco, args))
                .isInstanceOf(FormatoDeValorInvalido.class);
    }

    @Test
    void testContaInvalida() {
        List<String> args = new ArrayList<>();
        args.add("a888");
        args.add("70.0");
        assertThatThrownBy(() -> new Sacar(banco, args))
                .isInstanceOf(FormatoDeValorInvalido.class);
    }

    @Test
    void execute() throws BancoDigitalException {
        List<String> args = new ArrayList<>();
        args.add("777");
        args.add("70.0");
        Operacao operacao = new Sacar(banco, args);

        operacao.execute();
        verify(conta, times(1)).saque(70.0);
    }
}