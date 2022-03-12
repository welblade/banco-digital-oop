package com.github.welblade.bancodigital.data.model;

import com.github.welblade.bancodigital.core.exception.SaldoInsuficienteException;
import com.github.welblade.bancodigital.core.exception.ValorNegativoException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContaCorrenteTest {
    @Mock
    private Cliente cliente;

    private ContaCorrente conta;

    @BeforeAll
    void init(){
        MockitoAnnotations.openMocks(this);
        when(cliente.getNome()).thenReturn("João Expekito");
    }

    @BeforeEach
    void setUp() {
        conta = new ContaCorrente(cliente);
    }

    @AfterAll
    void destroy(){
        cliente = null;
        conta = null;
    }

    @Test
    @Order(1)
    void testCriarDoisObjetos(){
        Conta segundaConta = new ContaCorrente(cliente);
        assertThat(segundaConta.getNumero()).isEqualTo(2);
    }

    @Test
    @Order(2)
    void testExtrato() throws ValorNegativoException {
        conta.deposito(100.50);

        String extratoAtual = conta.extrato();
        String experado =  "=== Extrato Conta Corrente ==\n" +
                "Titular: João Expekito\n" +
                "Agencia: 1\n" +
                "Numero: 3\n" +
                "Saldo: R$ 100,50";

        assertThat(extratoAtual).isEqualTo(experado);
    }

    @Test
    void testDeposito() throws ValorNegativoException {
        conta.deposito(5.0);

        double valorEsperado = 5.0;
        double valorAtual = conta.getSaldo();

        assertThat(valorAtual).isEqualTo(valorEsperado);
    }

    @Test
    void testDepositoNegativo() {

        assertThatThrownBy(() -> conta.deposito(-0.01))
                .isExactlyInstanceOf(ValorNegativoException.class);

        double valorEsperado = 0.0;
        double valorAtual = conta.getSaldo();

        assertThat(valorAtual).isEqualTo(valorEsperado);
    }

    @Test
    void testSaque() throws ValorNegativoException, SaldoInsuficienteException {
        conta.deposito(15.0);
        conta.saque(10);

        double valorEsperado = 5.0;
        double valorAtual = conta.getSaldo();

        assertThat(valorAtual).isEqualTo(valorEsperado);
    }

    @Test
    void testSaqueNegativo() throws ValorNegativoException {
        conta.deposito(15.0);

        assertThatThrownBy(() -> conta.saque(-0.01))
                .isExactlyInstanceOf(ValorNegativoException.class);

        double valorEsperado = 15.0;
        double valorAtual = conta.getSaldo();

        assertThat(valorAtual).isEqualTo(valorEsperado);
    }

    @Test
    void testSaqueMaiorQueSaldo() {

        assertThatThrownBy(() -> conta.saque(0.01))
                .isExactlyInstanceOf(SaldoInsuficienteException.class);

        double valorEsperado = 0.0;
        double valorAtual = conta.getSaldo();

        assertThat(valorAtual).isEqualTo(valorEsperado);
    }

    @Test
    void testSaqueMaiorQueSaldoComSaldoMaiorQueZero() throws ValorNegativoException {
        conta.deposito(10);
        assertThatThrownBy(() -> conta.saque(10.01))
                .isExactlyInstanceOf(SaldoInsuficienteException.class);

        double valorEsperado = 10.0;
        double valorAtual = conta.getSaldo();

        assertThat(valorAtual).isEqualTo(valorEsperado);
    }

    @Test
    void testTransferencia() throws ValorNegativoException, SaldoInsuficienteException {
        conta.deposito(100.50);
        Conta destino = new ContaCorrente(cliente);
        conta.transferir(destino, 40);

        double saldoAtual = conta.getSaldo();
        double saldoAtualNaContaDestino = destino.getSaldo();
        double saldoEsperado = 60.50;
        double saldoEsperadoNaContaDestino = 40;

        assertThat(saldoAtual).isEqualTo(saldoEsperado);
        assertThat(saldoAtualNaContaDestino).isEqualTo(saldoEsperadoNaContaDestino);
    }
}