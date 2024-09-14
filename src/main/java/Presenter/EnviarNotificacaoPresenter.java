/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presenter;

import Model.RepositorioNotificacoes;
import Model.RepositorioUsuarios;
import Model.Usuario;
import View.EnviaNotificacaoView;
import config.AppInitializer;
import Log.LogManager;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JScrollPane;

/**
 *
 * @author ruanr
 */
public class EnviarNotificacaoPresenter {
    private EnviaNotificacaoView view;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioNotificacoes repositorioNotificacoes;
    private Usuario usuarioLogado;
    private LogManager logManager = LogManager.getInstance();

    public EnviarNotificacaoPresenter() {
        this.view = new EnviaNotificacaoView();
        this.repositorioUsuarios = AppInitializer.getRepositorioUsuarios();
        this.repositorioNotificacoes = AppInitializer.getRepositorioNotificacoes();
        this.usuarioLogado = AppInitializer.getUsuarioLogado();
        configureView();
    }

    private void configureView() {
        view.getBtnEnviar().addActionListener(e -> enviarNotificacao());
        view.getBtnBuscar().addActionListener(e -> buscarUsuarios());
        atualizarListaUsuarios();
    }

    private void buscarUsuarios() {
        String termoBusca = view.getCampoBusca().getText().toLowerCase();
        List<String> usuariosFiltrados = repositorioUsuarios.obterTodosUsuarios().stream()
                .filter(u -> u.getNome().toLowerCase().contains(termoBusca))
                .map(Usuario::getNome)
                .collect(Collectors.toList());
        view.atualizarListaUsuarios(usuariosFiltrados);
    }

    private void atualizarListaUsuarios() {
        List<String> todosUsuarios = repositorioUsuarios.obterTodosUsuarios().stream()
                .map(Usuario::getNome)
                .collect(Collectors.toList());
        view.atualizarListaUsuarios(todosUsuarios);
    }

    private void enviarNotificacao() {
        String mensagem = view.getTxtTextoNotificacao().getText();
        List<String> destinatarios = view.getSelectedUsers();

        for (String destinatario : destinatarios) {
            repositorioNotificacoes.enviarNotificacaoEmMassa(usuarioLogado.getNome(), List.of(destinatario), mensagem);
            logManager.log("Envio de notificação", destinatario, usuarioLogado.getNome());
        }
        
        view.exibirMensagem("Notificação enviada com sucesso!");
    }

    public void showView() {
        view.setVisible(true);
    }
    public EnviaNotificacaoView getView() {
        return view;
    }
}
