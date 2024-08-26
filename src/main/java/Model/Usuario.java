package Model;

import java.util.Date;

public class Usuario {
    private String nome;
    private String senha;
    private Date dataCadastro;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.dataCadastro = new Date();
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date data) {
        this.dataCadastro = data;
    }
}

