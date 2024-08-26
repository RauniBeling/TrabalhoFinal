package Presenter;

import Model.Usuario;
import Model.RepositorioUsuarios;
import View.BuscarView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class BuscarPresenter {
    private BuscarView view;
    private RepositorioUsuarios repositorioUsuarios;

    public BuscarPresenter() {
        view = new BuscarView();
        view.getButtonFecharBuscar().addActionListener((ActionEvent e) -> {
                Fechar();
            });

    }

    private void Fechar() {
        view.dispose();
    }


    public void buscarUsuario(String nome) {
        Usuario usuario = repositorioUsuarios.obterUsuarioPorNome(nome);
        if (usuario != null) {
            // Exibir detalhes do usuário na tela
        } else {
            // Exibir mensagem de usuário não encontrado
            JOptionPane.showMessageDialog(view, "Usuário não encontrado!");
        }
    }
    public void showView() {
        view.setVisible(true);
    }

}
