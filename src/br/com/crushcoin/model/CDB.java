package br.com.crushcoin.model;

import java.util.Date;

public class CDB extends Investimento {
    private double taxaRendimento;
    private String bancoEmissor;


    public CDB(int investment_id, int user_id, Date data, double valor, double taxaRendimento, String bancoEmissor) {
        super(investment_id, user_id, data, valor);
        this.taxaRendimento = taxaRendimento;
        this.bancoEmissor = bancoEmissor;
    }


    public double getTaxaRendimento() { return taxaRendimento; }
    public void setTaxaRendimento(double taxaRendimento) { this.taxaRendimento = taxaRendimento; }
    public String getBancoEmissor() { return bancoEmissor; }
    public void setBancoEmissor(String bancoEmissor) { this.bancoEmissor = bancoEmissor; }


    @Override
    public void resgatarInvestimento() {
        System.out.println("Resgatando o CDB do banco " + this.bancoEmissor + ".");
        System.out.println("O rendimento será calculado com base na taxa de " + this.taxaRendimento + "%.");
    }
}
