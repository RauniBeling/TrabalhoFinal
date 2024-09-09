package Model;

import java.util.Date;

public class Usuario {
    private String nome;
    private String senha;
    private Date dataCadastro;
    private String tipo; // "Administrador" ou "Usuario"
    private boolean permitido;// permitido acessar o sistema

    public boolean isPermitido() {
        return permitido;
    }

    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }

    public Usuario(String nome, String senha, String tipo, boolean permitido) {
        this.nome = nome;
        this.senha = senha;
        this.dataCadastro = new Date();
        this.permitido = permitido;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
    public boolean isAdministrador() {
        return "Administrador".equals(tipo);
    }
}

