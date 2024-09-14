package Presenter;

import Model.RepositorioUsuarios;
import Model.Usuario;
import View.ManterInclusaoEdicaoView;
import View.ManterVisualizacaoView;
import View.SucessoView;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JOptionPane;

public class ManterInclusaoEdicaoPresenter {

    private ManterInclusaoEdicaoView view;
    private RepositorioUsuarios repositorioUsuarios;

    public ManterInclusaoEdicaoPresenter(RepositorioUsuarios repositorioUsuarios) {
        this.view = new ManterInclusaoEdicaoView();
        this.repositorioUsuarios = repositorioUsuarios;
        view.getButtonCancelarManterEditar().addActionListener((ActionEvent e) -> {
            Fechar();
        });
        configureView();
        view.setVisible(true);
    }


    private void Fechar() {
        view.dispose();
    }

    void configureView() {
        // Configurar ações e eventos da tela de inclusão/edição
        view.getButtonCancelarManterEditar().addActionListener(e -> cancelar());
        view.getButtonSalvar().addActionListener(e -> {
            String nome = view.getNome();
            String senha = view.getSenha();
            salvarUsuario(nome, senha);
        });
        
    }

    public void salvarUsuario(String nome, String senha) {
        Usuario usuario = new Usuario(nome, senha, "comum", true);
        usuario.setDataCadastro(new Date()); // Define a data e hora atual do sistema
        repositorioUsuarios.adicionarUsuario(usuario);
        // Exibir mensagem de sucesso
        JOptionPane.showMessageDialog(view, "Usuário salvo com sucesso!");
        // Fechar a tela de inclusão/edição
        SucessoView sucessoView = new SucessoView();
        sucessoView.setVisible(true);
    }

    public void cancelar() {
        // Fechar a tela de inclusão/edição
        view.dispose();
        ManterVisualizacaoView visualizacaoView = new ManterVisualizacaoView();
        ManterVisualizacaoPresenter visualizacaoPresenter = new ManterVisualizacaoPresenter(visualizacaoView, repositorioUsuarios);
        visualizacaoView.setVisible(true);
    }

    public void preencherDadosUsuario(Usuario usuario) {
        // Preencher os campos da tela de inclusão/edição com os dados do usuário
        view.setNome(usuario.getNome());
        view.setSenha(usuario.getSenha());
    }

    public void showView() {
        view.setVisible(true);
    }
}
