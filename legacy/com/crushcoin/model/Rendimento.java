package br.com.crushcoin.model;

import java.util.Date;

public class Rendimento {
    private int income_id;
    private int investment_id;
    private int tipo_id;
    private double valor;
    private Date data_recebido;
    private Date data_prevista;


    public Rendimento() {
    }


    public Rendimento(int income_id, int investment_id, int tipo_id, double valor, Date data_recebido, Date data_prevista) {
        this.income_id = income_id;
        this.investment_id = investment_id;
        this.tipo_id = tipo_id;
        this.valor = valor;
        this.data_recebido = data_recebido;
        this.data_prevista = data_prevista;
    }


    public int getIncome_id() {
        return income_id;
    }

    public void setIncome_id(int income_id) {
        this.income_id = income_id;
    }

    public int getInvestment_id() {
        return investment_id;
    }

    public void setInvestment_id(int investment_id) {
        this.investment_id = investment_id;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData_recebido() {
        return data_recebido;
    }

    public void setData_recebido(Date data_recebido) {
        this.data_recebido = data_recebido;
    }

    public Date getData_prevista() {
        return data_prevista;
    }

    public void setData_prevista(Date data_prevista) {
        this.data_prevista = data_prevista;
    }


    public void calcularRendimento() {
        System.out.println("Método 'calcularRendimento' em execução para o rendimento ID: " + this.income_id);
    }
}