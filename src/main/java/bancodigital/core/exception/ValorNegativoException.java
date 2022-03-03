package bancodigital.core.exception;

public class ValorNegativoException extends BancoDigitalException {
    public ValorNegativoException() {
        super("Esta operação não suporta valores negativos.");
    }
}
