package br.com.banco.modelo;

import javax.swing.*;
import java.util.ArrayList;

public class AdenciaBancaria {

    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        operacoes();
    }

    public static void operacoes() {

        int operacao = Integer.parseInt(JOptionPane.showInputDialog("""
                --- Selecione uma operacao ---
                |   Opção 1 - Criar conta
                |   Opção 2 - Depositar
                |   Opção 3 - Sacar
                |   Opção 4 - Transferir
                |   Opção 5 - Listar
                |   Opção 6 - Sair"""));

        switch (operacao) {
            case 1 -> criarConta();
            case 2 -> depositar();
            case 3 -> sacar();
            case 4 -> transferir();
            case 5 -> listarContas();
            case 6 -> {
                JOptionPane.showMessageDialog(null, "Volte sempre!");
                System.exit(0);
            }
            default -> {
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                operacoes();
            }
        }
    }

    public static void criarConta() {
        Cliente cliente = new Cliente(null, null, null);

        cliente.setNome(JOptionPane.showInputDialog("Nome: "));


        cliente.setCpf(JOptionPane.showInputDialog("CPF: "));

        cliente.setEmail(JOptionPane.showInputDialog("Email: "));

        Conta conta = new Conta(cliente);

        contasBancarias.add(conta);

        JOptionPane.showMessageDialog(null, "--- Sua conta foi criada com sucesso! ---");

        operacoes();
    }

    private static Conta encontarConta(int numeroConta) {
        Conta conta = null;
        if (contasBancarias.size() > 0) {
            for (Conta c : contasBancarias) {
                if (c.getNumeroConta() == numeroConta) ;
                conta = c;
            }
        }
        return conta;
    }

    public static void depositar() {

        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da conta para deposito:"));

        Conta conta = encontarConta(numeroConta);

        if (conta != null) {
            Double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Qual valor dejesa depositar:"));
            conta.depositar(valorDeposito);
            JOptionPane.showMessageDialog(null, "Valor depositado com sucesso! ");

        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada! ");
        }
        operacoes();
    }

    public static void sacar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da conta para saque: "));

        Conta conta = encontarConta(numeroConta);

        if (conta != null) {
            Double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Qual valor dejesa casar?"));
            conta.sacar(valorSaque);

        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada! ");
        }
        operacoes();
    }

    public static void transferir() {
        int numeroContaRemetente = Integer.parseInt(JOptionPane.showInputDialog("Número da conta que vai enviar a transferência: "));

        Conta contaRemetente = encontarConta(numeroContaRemetente);

        if (contaRemetente != null) {
            int numeroContaDestinatario = Integer.parseInt(JOptionPane.showInputDialog("Número da conta do destinatário:"));
            Conta contaDestinatario = encontarConta(numeroContaDestinatario);

            if (contaDestinatario != null) {
                Double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor da transferência:"));
                contaRemetente.trasferir(contaDestinatario, valor);

            } else {
                JOptionPane.showMessageDialog(null, "Conta para deposito não foi encontrada");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Conta para transferência não encontrada");
        }
        operacoes();
    }

    public static void listarContas() {
        if (contasBancarias.size() > 0) {
            for (Conta conta : contasBancarias) {
                JOptionPane.showMessageDialog(null, conta);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não há contas cadastradas!");

        }
        operacoes();
    }
}



