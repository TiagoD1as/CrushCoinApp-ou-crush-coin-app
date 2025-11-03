package br.com.crushcoin.model;

public class Conta {
    private int account_id;
    private int user_id;
    private String tipo_conta;
    private String nome_conta;
    private double saldo;


    public Conta() {
    }

    public Conta(int account_id, int user_id, String tipo_conta, String nome_conta, double saldo) {
        this.account_id = account_id;
        this.user_id = user_id;
        this.tipo_conta = tipo_conta;
        this.nome_conta = nome_conta;
        this.saldo = saldo;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTipo_conta() {
        return tipo_conta;
    }

    public void setTipo_conta(String tipo_conta) {
        this.tipo_conta = tipo_conta;
    }

    public String getNome_conta() {
        return nome_conta;
    }

    public void setNome_conta(String nome_conta) {
        this.nome_conta = nome_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void fazerDeposito(double valor) {
        System.out.println("Método 'fazerDeposito' em execução. Depositando R$" + valor + " na conta " + this.nome_conta);
    }
}
