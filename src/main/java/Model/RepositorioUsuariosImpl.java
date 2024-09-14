package Model;

import DAO.UsuarioDAO;
import java.util.List;

public class RepositorioUsuariosImpl implements RepositorioUsuarios {
    private UsuarioDAO usuarioDAO;
 
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
    public List<Usuario> obterTodosUsuarios() {
        return usuarioDAO.obterTodosUsuarios();
    }

    @Override
    public void repositorioUsuariosImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
     }

}

