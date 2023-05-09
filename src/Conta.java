public class Conta {
    private double saldo;
    private double limite;
    private static int ultimaConta = 100;
    private int numeroConta;
    private Cliente cliente;

    public Conta(double saldoInicial, double limite, Cliente cliente) {
        this.cliente = cliente;
        this.saldo = saldoInicial;
        this.limite = limite;
        this.numeroConta = ++ultimaConta;
    }

    public Conta(double limite, Cliente cliente) {
        this.saldo = 0.0;
        this.cliente = cliente;
        this.limite = limite;
        this.numeroConta = ++ultimaConta;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public boolean sacar(double valor) {
        if (valor > 0 && (valor <= (this.limite + saldo))) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean transferir(double valor, Conta contaDestino) {
        if (valor > 0 && (valor <= (this.limite + saldo))) {
            if (this.sacar(valor)) {
                contaDestino.depositar(valor);
                return true;
            }
        }
        return false;
    }

    public double getLimite() {
        return this.limite;
    }
}
