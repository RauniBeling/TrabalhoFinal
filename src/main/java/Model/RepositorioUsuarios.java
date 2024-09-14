package Model;

import java.util.List;

import DAO.UsuarioDAO;

public interface RepositorioUsuarios {
    void adicionarUsuario(Usuario usuario);
    void atualizarUsuario(Usuario usuario);
    void excluirUsuario(Usuario usuario);
    Usuario obterUsuarioPorNome(String nome);
    List<Usuario> obterTodosUsuarios();
    void repositorioUsuariosImpl(UsuarioDAO usuarioDAO);
    List<Usuario> obterTodosUsuarios(String letra);
}

