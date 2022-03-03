package bancodigital.data.model;

public class ContaPoupanca extends Conta {

    ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    String extrato() {
        return "=== Extrato Conta Poupança ==\n" + super.extrato();
    }
}
