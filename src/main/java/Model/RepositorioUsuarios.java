package Model;

import java.util.List;

public interface RepositorioUsuarios {
    void adicionarUsuario(Usuario usuario);
    void atualizarUsuario(Usuario usuario);
    void excluirUsuario(Usuario usuario);
    Usuario obterUsuarioPorNome(String nome);
    List<Usuario> obterTodosUsuarios();
}

