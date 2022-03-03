package bancodigital.data.model;

public class ContaCorrente extends Conta {

    ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    String extrato() {
        return "=== Extrato Conta Corrente ==\n" + super.extrato();
    }
}
