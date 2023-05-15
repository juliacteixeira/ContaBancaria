import java.util.Scanner;

public class CaixaEletronico {
    private Scanner scanner;
    private Conta[] contas;
    private int qtdContas;

    public CaixaEletronico() {
        contas = new Conta[2];
        qtdContas = 0;
        scanner = new Scanner(System.in);
    }

    public void executar() {
        int opcao;

        do {
            exibirMenu();
            System.out.println("\nDigite sua opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    novaConta();
                    break;
                case 2:
                    getSaldo();
                    break;
                case 3:
                    depositar();
                    break;
                case 4:
                    sacar();
                    break;
                case 5:
                    transferir();
                    break;
                case 6:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 6);
    }

    private void transferir() {

        System.out.println("Transferência entre contas");
        System.out.println("==============================");
        System.out.println("Informe o número da conta de origem:");
        int numContaOrigem = Integer.parseInt(scanner.nextLine());
        Conta contaOrigem = buscarConta(numContaOrigem);
        if (contaOrigem == null) {
            System.out.println("Conta não encontrada");
        }

        System.out.println("Informe o número da conta de destino:");
        int numContaDestino = Integer.parseInt(scanner.nextLine());
        Conta contaDestino = buscarConta(numContaDestino);
        if (contaDestino == null) {
            System.out.println("Conta não encontrada");
        }

        System.out.println("Informe o valor a ser transferido:");
        double valorTransferencia = Double.parseDouble(scanner.nextLine());
        if (contaOrigem.transferir(valorTransferencia, contaDestino)) {
            System.out.println("Transferência realizada com sucesso");

        } else {
            System.out.println("Não foi possível realizar a transferência");

        }
    }

    private Conta buscarConta(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    private int getNumConta() {
        System.out.println("Digite o número da conta que deseja procurar: ");
        int numConta = Integer.parseInt(scanner.nextLine());
        return numConta;
    }

    private void sacar() {
        int numConta = getNumConta();
        Conta conta = buscarConta(numConta);
        System.out.println("Informe o valor que deseja sacar: ");
        Double valor = Double.parseDouble(scanner.nextLine());

        if (conta == null) {
            System.out.println("Conta não encontrada");
        }

        if (conta.sacar(valor)) {
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a operação de saque.");

        }

    }

    private void depositar() {
        int numConta = getNumConta();
        Conta conta = buscarConta(numConta);
        System.out.println("Informe o valor que deseja depositar: ");
        Double valor = Double.parseDouble(scanner.nextLine());

        if (conta == null) {
            System.out.println("Conta não encontrada");
        }

        if (conta.depositar(valor)) {
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a operação de depósito.");
        }

    }

    private void getSaldo() {
        int numConta = getNumConta();
        Conta conta = buscarConta(numConta);

        if (conta == null) {
            System.out.println("Conta não encontrada");
        }

        System.out.println("Titular: " + conta.getCliente().getNome());
        System.out.println("==============================");
        System.out.println("Saldo disponível: " + conta.getSaldo());
        System.out.println("Limite disponível: " + conta.getLimite());
        System.out.println("Saldo total: " + (conta.getSaldo() + conta.getLimite()));

    }

    private void novaConta() {

        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = new Cliente(cpf, nome);

        System.out.println("Deseja informar o saldo inicial da conta? ");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        int opcao = Integer.parseInt(scanner.nextLine());
        System.out.println(opcao);

        Double saldo = 0.0;
        if (opcao == 1) {
            System.out.println("Digite o valor do saldo da conta: ");
            saldo = Double.parseDouble(scanner.nextLine());
        }

        System.out.println("Digite o valor do limite da conta: ");
        Double limite = Double.parseDouble(scanner.nextLine());

        if (qtdContas == contas.length) {
            System.out.println("Limite de contas atingido");
        } else {
            if (saldo > 0) {
                contas[qtdContas] = new Conta(saldo, limite, cliente);
                qtdContas++;
            } else {
                contas[qtdContas] = new Conta(limite, cliente);
                qtdContas++;
            }
        }

        System.out.println("Conta criada com sucesso! Número da conta: " + contas[qtdContas - 1].getNumeroConta());

    }

    private void exibirMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Consultar Saldo");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Transferir");
        System.out.println("6 - Sair");
    }
}
