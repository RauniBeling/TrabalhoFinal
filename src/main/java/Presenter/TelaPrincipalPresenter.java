package Presenter;

import Model.RepositorioUsuarios;
import Service.AutenticacaoService;
import View.TelaPrincipalView;

import javax.swing.*;

public class TelaPrincipalPresenter {
    private TelaPrincipalView view;
    private AutenticacaoService autenticacaoService;
    private RepositorioUsuarios repositorioUsuarios;

    public TelaPrincipalPresenter() {
        this.view = new TelaPrincipalView();

        view.getMenuAdicionarEditar().addActionListener(e -> abrirManterInclusaoEdicaoView());
        view.getMenuBuscar().addActionListener(e -> abrirBuscarView());
    }

    public void autenticarUsuario(String nome, String senha) {
        boolean autenticado = autenticacaoService.autenticar(nome, senha);
        if (autenticado) {
            // Exibir a tela principal
            view.setVisible(true);
        } else {
            // Exibir mensagem de erro de autenticação
            JOptionPane.showMessageDialog(view, "Usuário ou senha inválidos!");
        }
    }

    private void abrirManterInclusaoEdicaoView() {
        try {
            ManterInclusaoEdicaoPresenter presenter = new ManterInclusaoEdicaoPresenter();
            presenter.showView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "error: " + ex, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void abrirBuscarView() {
        try {
            BuscarPresenter presenter = new BuscarPresenter();
            presenter.showView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "error: " + ex, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        TelaPrincipalPresenter presenter = new TelaPrincipalPresenter();
    }
}
