package br.com.crushcoin.dao;

import br.com.crushcoin.model.Despesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;

public class DespesaDAO {

    private Connection conexao;

    public DespesaDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "RM561438", "120588");
            System.out.println("Conexão com Oracle estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver Oracle JDBC não encontrado: " + e.getMessage());
            System.err.println("Executando em modo simulado (sem banco de dados)...");
            conexao = null;
        } catch (SQLException e) {
            System.err.println("Erro na conexão com o banco de dados: " + e.getMessage());
            System.err.println("Executando em modo simulado (sem banco de dados)...");
            conexao = null;
        }
    }

    public void insert(Despesa despesa) {
        if (conexao == null) {
            System.out.println("SIMULADO: Despesa cadastrada - User ID: " + despesa.getUser_id() + 
                             ", Valor: R$" + despesa.getValor() + 
                             ", Data: " + despesa.getData_gasto());
            return;
        }
        
        String sql = "INSERT INTO Despesa (expense_id, user_id, valor, data_gasto) VALUES ((SELECT NVL(MAX(expense_id), 0) + 1 FROM Despesa), ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, despesa.getUser_id());
            stmt.setDouble(2, despesa.getValor());
            stmt.setDate(3, new java.sql.Date(despesa.getData_gasto().getTime()));
            stmt.executeUpdate();
            System.out.println("Despesa cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar despesa: " + e.getMessage());
        }
    }

    public List<Despesa> getAll() {
        List<Despesa> despesas = new ArrayList<>();
        
        if (conexao == null) {
            System.out.println("SIMULADO: Consultando despesas... (modo simulado - sem banco de dados)");
            return despesas;
        }
        
        String sql = "SELECT * FROM Despesa ORDER BY data_gasto DESC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Despesa despesa = new Despesa(
                        rs.getInt("expense_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("valor"),
                        rs.getDate("data_gasto")
                );
                despesas.add(despesa);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar despesas: " + e.getMessage());
        }
        return despesas;
    }
}