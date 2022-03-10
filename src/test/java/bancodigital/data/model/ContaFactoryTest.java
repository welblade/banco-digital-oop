package bancodigital.data.model;

import bancodigital.core.exception.TipoDeContaInvalidoException;
import bancodigital.core.exception.ValorNegativoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class ContaFactoryTest {


    private static Stream<Arguments> argumentosCorretos() {
        return Stream.of(
                Arguments.of("poupança", ContaPoupanca.class),
                Arguments.of("corrente", ContaCorrente.class)
        );
    }
    @SuppressWarnings("rawtypes")
    @ParameterizedTest
    @MethodSource("argumentosCorretos")
    void criarContaArgumentosCorretos(final String tipo, Class classeEsperada) throws TipoDeContaInvalidoException, ValorNegativoException {
        ContaFactory factory = new ContaFactory();
        Conta conta =  factory.criar(mock(Cliente.class), tipo, 0.0);

        assertThat(conta)
                .isInstanceOf(classeEsperada)
        ;
    }
    @Test
    void criarContaTipoInexistente(){
        ContaFactory factory = new ContaFactory();

        assertThatThrownBy(() -> factory.criar(mock(Cliente.class), "comigo", 0.0))
                .isInstanceOf(TipoDeContaInvalidoException.class)
        ;
    }

    @Test
    void criarContaSaldoNegativo(){
        ContaFactory factory = new ContaFactory();

        assertThatThrownBy(() -> factory.criar(mock(Cliente.class), "poupança", -0.1))
                .isInstanceOf(ValorNegativoException.class)
        ;
    }

    @Test
    void testIsTipoPermitido(){
        Boolean resposta = ContaFactory.isTipoPermitido("corrente");
        assertThat(resposta).isTrue();
    }

    @Test
    void testIsNotTipoPermitido(){
        Boolean resposta = ContaFactory.isTipoPermitido("comigo");
        assertThat(resposta).isFalse();
    }
}