package Presenter;

import Model.RepositorioUsuarios;
import Model.Usuario;
import View.ManterInclusaoEdicaoView;
import View.ManterVisualizacaoView;
import javax.swing.JOptionPane;

public class ManterVisualizacaoPresenter {
    private ManterVisualizacaoView view;
    private RepositorioUsuarios repositorioUsuarios;

    public ManterVisualizacaoPresenter(ManterVisualizacaoView view, RepositorioUsuarios repositorioUsuarios) {
        this.view = view;
        this.repositorioUsuarios = repositorioUsuarios;
        view.getButtonFecharManterVisualizar().addActionListener(e -> view.dispose());
        configureView();
    }

    private void configureView() {
        // Configurar ações e eventos da tela de visualização
    }

    public void excluirUsuario(Usuario usuario) {
        repositorioUsuarios.excluirUsuario(usuario);
        // Exibir mensagem de exclusão realizada
        JOptionPane.showMessageDialog(view, "Usuário excluído com sucesso!");
    }

    public void editarUsuario(Usuario usuario) {
        // Abrir tela de inclusão/edição preenchida com os dados do usuário
        ManterInclusaoEdicaoView inclusaoEdicaoView = new ManterInclusaoEdicaoView();
      //  ManterInclusaoEdicaoPresenter inclusaoEdicaoPresenter = new ManterInclusaoEdicaoPresenter(inclusaoEdicaoView, repositorioUsuarios);
        //inclusaoEdicaoPresenter.preencherDadosUsuario(usuario);
        inclusaoEdicaoView.setVisible(true);
    }
}
