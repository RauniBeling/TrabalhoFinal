package Model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Usuario {
    private String nome;
    private String senha;
    private Date dataCadastro;
    private String tipo; // "Administrador" ou "Usuario"
    private boolean permitido;// permitido acessar o sistema
    private List<Notificacao> notificacoes;

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
        this.notificacoes = new ArrayList<>();
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
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public boolean isAdministrador() {
        return "Administrador".equals(tipo);
    }

    public void adicionarNotificacao(Notificacao notificacao) {
        notificacoes.add(notificacao);
    }

    public List<Notificacao> getNotificacoesNaoLidas() {
        return notificacoes.stream()
                .filter(n -> !n.isLida())
                .collect(Collectors.toList());
    }

    public int getNotificacoesEnviadas() {
        return notificacoes.size();
    }

    public int getNotificacoesLidas() {
        return (int) notificacoes.stream()
                .filter(Notificacao::isLida)
                .count();
    }
}

