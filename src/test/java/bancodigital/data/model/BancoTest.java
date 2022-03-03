package bancodigital.data.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BancoTest {
    private Banco banco;

    @BeforeAll
    void setUp(){
        banco = new Banco("Banco Digital da Castelia");
    }

    @Test
    @Order(1)
    void testCreatedObject() {
        String nomeAtual = banco.getNome();
        assertThat(nomeAtual).isEqualTo("Banco Digital da Castelia");
    }

    @Test
    @Order(2)
    void addConta() {
        banco.addConta(mock(Conta.class));

        assertThat(banco.getContas())
                .hasSize(1)
                .hasAtLeastOneElementOfType(Conta.class);
    }

    @Test
    void tryToDirectAddConta(){
        assertThatThrownBy(()-> banco.getContas().add(mock(Conta.class)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}