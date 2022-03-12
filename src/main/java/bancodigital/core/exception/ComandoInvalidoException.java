package bancodigital.core.exception;

public class ComandoInvalidoException extends BancoDigitalException {
    public ComandoInvalidoException(String comando) {
        super(String.format("Comando inválido: \"%s\".", comando));
    }
}
