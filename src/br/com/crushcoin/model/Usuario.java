package br.com.crushcoin.model;

public class Usuario {
    private int user_id;
    private String email;
    private String senha_hash;


    public Usuario() {
    }

    public Usuario(int user_id, String email, String senha_hash) {
        this.user_id = user_id;
        this.email = email;
        this.senha_hash = senha_hash;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha_hash() {
        return senha_hash;
    }

    public void setSenha_hash(String senha_hash) {
        this.senha_hash = senha_hash;
    }

    public void atualizarPerfil() {
        System.out.println("Método 'atualizarPerfil' em execução para o usuário: " + this.email);
    }
}
