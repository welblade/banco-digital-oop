package bancodigital.ui;

public class ComandoOperacaoContainer {
    public final Class<?> comando;
    public final Class<?> operacao;

    public ComandoOperacaoContainer(Class<?> comando, Class<?> operacao) {
        this.comando = comando;
        this.operacao = operacao;
    }
}
