package Model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Notificacao {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private String mensagem;
    private Date dataEnvio;
    private boolean lida;
    private Usuario remetente;
    private Usuario destinatario;
    private final int id;

    public Notificacao(String mensagem, Usuario remetente, Usuario destinatario) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.mensagem = mensagem;
        this.dataEnvio = new Date();
        this.lida = false;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }
    public Notificacao(int id, String mensagem, Usuario remetente, Usuario destinatario, boolean lida) {
        this.id = id;
        this.mensagem = mensagem;
        this.dataEnvio = new Date();
        this.lida = lida;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }
    // Getters e setters
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public void marcarComoLida() {
        this.lida = true;
    }

    public int getId() {
        return id;
    }
}
