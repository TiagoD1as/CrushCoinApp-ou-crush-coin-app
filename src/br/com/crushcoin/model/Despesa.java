package br.com.crushcoin.model;

import java.util.Date;

public class Despesa {
    private int expense_id;
    private int user_id;
    private double valor;
    private Date data_gasto;

    public Despesa() {}

    public Despesa(int user_id, double valor, Date data_gasto) {
        this.user_id = user_id;
        this.valor = valor;
        this.data_gasto = data_gasto;
    }

    public Despesa(int expense_id, int user_id, double valor, Date data_gasto) {
        this.expense_id = expense_id;
        this.user_id = user_id;
        this.valor = valor;
        this.data_gasto = data_gasto;
    }

    public int getExpense_id() { return expense_id; }
    public void setExpense_id(int expense_id) { this.expense_id = expense_id; }
    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public Date getData_gasto() { return data_gasto; }
    public void setData_gasto(Date data_gasto) { this.data_gasto = data_gasto; }

    @Override
    public String toString() {
        return "Despesa{" +
                "expense_id=" + expense_id +
                ", user_id=" + user_id +
                ", valor=" + valor +
                ", data_gasto=" + data_gasto +
                '}';
    }
}