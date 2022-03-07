package bancodigital.core.exception;

public class QuantidadeInvalidaDeArgumentosParaOperacaoException extends BancoDigitalException {
    public QuantidadeInvalidaDeArgumentosParaOperacaoException(int valorEsperado, int valorAtual) {
        super(
                String.format(
                        "Quantidade inválida de argumentos para a operação, era esperado %d mas foi recebido %d",
                        valorEsperado, valorAtual
                )
        );
    }
}
