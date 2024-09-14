/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presenter;

import Model.RepositorioNotificacoes;
import Model.RepositorioUsuarios;
import Model.Usuario;
import View.EnviaNotificacaoView;
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
    public EnviarNotificacaoPresenter(RepositorioUsuarios repositorioUsuarios, RepositorioNotificacoes repositorioNotificacoes, Usuario usuarioLogado) {
        this.view = new EnviaNotificacaoView();
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioNotificacoes = repositorioNotificacoes;
        this.usuarioLogado = usuarioLogado;
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

        repositorioNotificacoes.enviarNotificacaoEmMassa(usuarioLogado.getNome(), destinatarios, mensagem);
        
        view.exibirMensagem("Notificação enviada com sucesso!");
        view.dispose();
    }

    public void showView() {
        view.setVisible(true);
    }
    public EnviaNotificacaoView getView() {
        return view;
    }
}
