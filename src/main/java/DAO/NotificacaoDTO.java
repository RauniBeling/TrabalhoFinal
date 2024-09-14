/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Date;

/**
 *
 * @author ruanr
 */
public class NotificacaoDTO {
    private String remetente;
    private String mensagem;
    private Date dataEnvio;
    private boolean lida;

    public NotificacaoDTO(String remetente, String mensagem, Date dataEnvio, boolean lida) {
        this.remetente = remetente;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.lida = lida;
    }

    // Getters
    public String getRemetente() { return remetente; }
    public String getMensagem() { return mensagem; }
    public Date getDataEnvio() { return dataEnvio; }
    public boolean isLida() { return lida; }
}