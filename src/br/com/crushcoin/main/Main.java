package br.com.crushcoin.main;

import br.com.crushcoin.model.Usuario;
import br.com.crushcoin.model.Conta;
import br.com.crushcoin.model.CDB;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("----- Teste do Sistema CrushCoin -----");


        Usuario novoUsuario = new Usuario(1, "tiago.dias@email.com", "senha123");
        System.out.println("\nUsuário criado: " + novoUsuario.getEmail());
        novoUsuario.atualizarPerfil();


        Conta contaPoupanca = new Conta(101, novoUsuario.getUser_id(), "Poupanca", "Conta Poupanca", 1000.00);
        System.out.println("\nConta criada: " + contaPoupanca.getNome_conta());
        System.out.println("Saldo inicial: R$" + contaPoupanca.getSaldo());
        contaPoupanca.fazerDeposito(500.00);
        System.out.println("Novo saldo após depósito: R$" + contaPoupanca.getSaldo());


        CDB meuCDB = new CDB(201, novoUsuario.getUser_id(), new Date(), 5000.00, 1.05, "Banco CrushCoin");
        System.out.println("\nCDB criado:");
        System.out.println("ID do Investimento: " + meuCDB.getInvestment_id());
        System.out.println("Valor aplicado: R$" + meuCDB.getValor());
        System.out.println("Banco Emissor: " + meuCDB.getBancoEmissor());


        meuCDB.resgatarInvestimento();

        System.out.println("\n----- Fim do Teste -----");
    }
}