package Model;

import DAO.UsuarioDAO;
import Log.LogManager;
import java.util.List;

public class RepositorioUsuariosImpl implements RepositorioUsuarios {
    private UsuarioDAO usuarioDAO;
    private LogManager logManager = LogManager.getInstance();
 
    @Override
    public void adicionarUsuario(Usuario usuario) {
        usuarioDAO.adicionarUsuario(usuario);
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        usuarioDAO.atualizarUsuario(usuario);
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        usuarioDAO.excluirUsuario(usuario);
    }

    @Override
    public Usuario obterUsuarioPorNome(String nome) {
        return usuarioDAO.obterUsuarioPorNome(nome);
    }

    @Override
    public List<Usuario> obterTodosUsuarios(String letra) {
        return usuarioDAO.obterTodosUsuarios(letra);
    }

    @Override
    public void repositorioUsuariosImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public List<Usuario> obterTodosUsuarios() {
        return usuarioDAO.obterTodosUsuarios();
    }

    public void alterarSenha(String username, String novaSenha) {
        Usuario usuario = obterUsuarioPorNome(username);
        if (usuario != null) {
            usuario.setSenha(novaSenha);
            atualizarUsuario(usuario);
            logManager.log("Alteração de senha", username, username);
        }
    }

    public void autorizarUsuario(String username, String autorizador) {
        Usuario usuario = obterUsuarioPorNome(username);
        if (usuario != null) {
            usuario.setPermitido(true);
            atualizarUsuario(usuario);
            logManager.log("Autorização de usuário", username, autorizador);
        }
    }
}

