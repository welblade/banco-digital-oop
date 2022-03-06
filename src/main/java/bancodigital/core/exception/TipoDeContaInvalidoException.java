package bancodigital.core.exception;

public class TipoDeContaInvalidoException extends BancoDigitalException {
    public TipoDeContaInvalidoException() {
        super("O tipo de conta informado não é válido.");
    }
}
