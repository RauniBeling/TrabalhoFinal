/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presenter;

import View.TrocaSenhaView;
import config.AppInitializer;
import Model.Usuario;
import Model.RepositorioUsuarios;
import Log.LogManager;

/**
 *
 * @author ruanr
 */
public class TrocaSenhaPresenter {
    
    private TrocaSenhaView view;
    private RepositorioUsuarios repositorioUsuarios; // Added field
    private Usuario usuarioLogado; // Added field
    private LogManager logManager = LogManager.getInstance();

    public TrocaSenhaPresenter() {
        this.view = new TrocaSenhaView();
        this.view.setPresenter(this);
        this.repositorioUsuarios = AppInitializer.getRepositorioUsuarios();
        this.usuarioLogado = AppInitializer.getUsuarioLogado();
    }
    
    public void trocarSenha(String senhaAtual, String novaSenha, String confirmarSenha) {
        if (senhaAtual.isEmpty() || novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
            view.showErrorMessage("Todos os campos devem ser preenchidos.");
            return;
        }
        
        if (!novaSenha.equals(confirmarSenha)) {
            view.showErrorMessage("A nova senha e a confirmação não coincidem.");
            return;
        }
        
        if (!validarSenha(novaSenha)) {
            view.showErrorMessage("A nova senha não atende aos requisitos de segurança.");
            return;
        }
        
        boolean senhaAtualCorreta = verificarSenhaAtual(senhaAtual);
        if (!senhaAtualCorreta) {
            view.showErrorMessage("A senha atual está incorreta.");
            return;
        }
        
        boolean senhaAtualizada = atualizarSenha(novaSenha);
        if (senhaAtualizada) {
            view.showSuccessMessage("Senha atualizada com sucesso!");
            view.dispose();
        } else {
            view.showErrorMessage("Erro ao atualizar a senha. Tente novamente.");
        }
    }
    
    private boolean verificarSenhaAtual(String senhaAtual) {
        return usuarioLogado != null && usuarioLogado.getSenha().equals(senhaAtual);
    }
    
    private boolean atualizarSenha(String novaSenha) {
        if (usuarioLogado != null) {
            usuarioLogado.setSenha(novaSenha);
            repositorioUsuarios.atualizarUsuario(usuarioLogado);
            logManager.log("Alteração de senha", usuarioLogado.getNome(), usuarioLogado.getNome());
            return true;
        }
        return false;
    }
    
    private boolean validarSenha(String senha) {
        // try {
        //     Class<?> validadorSenhaClass = Class.forName("com.mycompany.validadorsenha.ValidadorSenha");
        //     Object validador = validadorSenhaClass.getDeclaredConstructor().newInstance();
        //     return (boolean) validadorSenhaClass.getMethod("validarSenha", String.class).invoke(validador, senha);
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     return false;
        // }
        return true; // Temporary return to avoid compilation errors
    }
    
    public void handleTrocarSenhaButtonClick() {
        String senhaAtual = new String(view.getTxtSenhaAtual().getPassword());
        String novaSenha = new String(view.getTxtNovaSenha().getPassword());
        String confirmarSenha = new String(view.getTxtConfirmarSenha().getPassword());
        
        trocarSenha(senhaAtual, novaSenha, confirmarSenha);
    }
    
    public TrocaSenhaView getView() {
        return view;
    }
    
    public void showView() {
        view.setVisible(true);
    }
}
