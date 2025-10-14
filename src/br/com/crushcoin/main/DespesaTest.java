package br.com.crushcoin.main;

import br.com.crushcoin.dao.DespesaDAO;
import br.com.crushcoin.dao.UsuarioDAO;
import br.com.crushcoin.model.Despesa;
import br.com.crushcoin.model.Usuario;
import java.util.Date;
import java.util.List;

public class DespesaTest {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        DespesaDAO despesaDAO = new DespesaDAO();

        // 0. Primeiro, inserir usuários necessários
        System.out.println("Inserindo usuários necessários...");
        try {
            // Verificar se os usuários já existem antes de inserir
            if (!usuarioDAO.exists(1)) {
                usuarioDAO.insert(new Usuario(1, "usuario1@email.com", "senha123"));
            }
            if (!usuarioDAO.exists(2)) {
                usuarioDAO.insert(new Usuario(2, "usuario2@email.com", "senha456"));
            }
        } catch (Exception e) {
            System.err.println("Falha ao inserir usuários: " + e.getMessage());
        }

        // 1. Teste de cadastro: Inserindo 5 novas despesas
        System.out.println("\nIniciando o cadastro de 5 despesas...");
        try {
            despesaDAO.insert(new Despesa(1, 50.00, new Date())); // user_id = 1
            despesaDAO.insert(new Despesa(1, 75.50, new Date()));
            despesaDAO.insert(new Despesa(2, 120.00, new Date())); // user_id = 2
            despesaDAO.insert(new Despesa(1, 35.90, new Date()));
            despesaDAO.insert(new Despesa(2, 200.00, new Date()));
        } catch (Exception e) {
            System.err.println("Falha ao inserir dados de teste: " + e.getMessage());
        }

        // 2. Teste de consulta: Recuperando e exibindo todas as despesas
        System.out.println("\nRecuperando todas as despesas do banco de dados...");
        List<Despesa> todasAsDespesas = despesaDAO.getAll();

        if (todasAsDespesas.isEmpty()) {
            System.out.println("Nenhuma despesa encontrada.");
        } else {
            for (Despesa d : todasAsDespesas) {
                System.out.println(d);
            }
        }
    }
}