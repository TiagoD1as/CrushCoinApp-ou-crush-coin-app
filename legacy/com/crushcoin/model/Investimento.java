package br.com.crushcoin.model;

import java.util.Date;

public class Investimento {
    private int investment_id;
    private int user_id;
    private Date data;
    private double valor;


    public Investimento() {
    }


    public Investimento(int investment_id, int user_id, Date data, double valor) {
        this.investment_id = investment_id;
        this.user_id = user_id;
        this.data = data;
        this.valor = valor;
    }


    public int getInvestment_id() {
        return investment_id;
    }

    public void setInvestment_id(int investment_id) {
        this.investment_id = investment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public void resgatarInvestimento() {
        System.out.println("Método 'resgatarInvestimento' em execução para o investimento ID: " + this.investment_id);
    }
}
