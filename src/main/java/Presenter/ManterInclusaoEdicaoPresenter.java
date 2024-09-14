package Presenter;

import Model.RepositorioUsuarios;
import Model.Usuario;
import View.ManterInclusaoEdicaoView;
import View.ManterVisualizacaoView;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import Log.LogManager;

public class ManterInclusaoEdicaoPresenter {

    private ManterInclusaoEdicaoView view;
    private RepositorioUsuarios repositorioUsuarios;
    private LogManager logManager = LogManager.getInstance();

    public ManterInclusaoEdicaoPresenter(RepositorioUsuarios repositorioUsuarios) {
        this.view = new ManterInclusaoEdicaoView();
        this.repositorioUsuarios = repositorioUsuarios;
        view.getButtonCancelarManterEditar().addActionListener((ActionEvent e) -> {
            Fechar();
        });
      
        configureView();
        view.setVisible(true);
    }

    public ManterInclusaoEdicaoView getView(){
        return view;
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
        try {
            // Check if user already exists
            if (repositorioUsuarios.obterUsuarioPorNome(nome) != null) {
                view.showErrorMessage("Usuário já existe!");
                logManager.log("Erro na inclusão de usuário", "Usuário já existe: " + nome, "admin");
                return;
            }

            Usuario usuario = new Usuario(nome, senha, "comum", true);
            usuario.setDataCadastro(new Date());
            repositorioUsuarios.adicionarUsuario(usuario);
            logManager.log("Inclusão de usuário", nome, "admin");
            JOptionPane.showMessageDialog(view, "Usuário salvo com sucesso!");
            Fechar();
        } catch (Exception e) {
            String errorMessage = "Erro ao salvar usuário: " + e.getMessage();
            view.showErrorMessage(errorMessage);
        }
    }

    public void cancelar() {
        // Fechar a tela de inclusão/edição
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
