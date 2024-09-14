/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ruanr
 */
package config;
import DAO.UsuarioDAO;
import Model.RepositorioUsuarios;
import Model.RepositorioUsuariosImpl;
import Model.Usuario;
import Model.RepositorioNotificacoes;
import Service.AutenticacaoService;
import Model.RepositorioNotificacoesImpl;
import DAO.NotificacaoDAO;
public class AppInitializer {
    private static UsuarioDAO usuarioDAO;
    private static RepositorioUsuarios repositorioUsuarios;
    private static RepositorioNotificacoes repositorioNotificacoes;
    private static AutenticacaoService autenticacaoService;
    private static NotificacaoDAO notificacaoDAO;
    public static void initialize() {
        usuarioDAO = new UsuarioDAO();
        notificacaoDAO = new NotificacaoDAO();
        repositorioUsuarios = new RepositorioUsuariosImpl();
        autenticacaoService = new AutenticacaoService(repositorioUsuarios);
        repositorioNotificacoes = new RepositorioNotificacoesImpl();
        repositorioUsuarios.repositorioUsuariosImpl(usuarioDAO);     
        
    }
    public static AutenticacaoService getAutenticacaoService() {
        return autenticacaoService;
    }
    public static Usuario getUsuarioLogado() {
        return autenticacaoService.getUsuarioAutenticado();
    }
    public static RepositorioUsuarios getRepositorioUsuarios() {
        return repositorioUsuarios;
    }

    public static RepositorioNotificacoes getRepositorioNotificacoes() {
        return repositorioNotificacoes;
    }

    public static UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }
    public static NotificacaoDAO getNotificacaoDAO() {
        return notificacaoDAO;
    }
    
 
}
