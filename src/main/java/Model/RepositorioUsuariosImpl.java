package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioUsuariosImpl implements RepositorioUsuarios {
    private Map<String, Usuario> usuarios;

    public RepositorioUsuariosImpl() {
        this.usuarios = new HashMap<>();
    }

    @Override
    public void adicionarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNome(), usuario);
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNome(), usuario);
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        usuarios.remove(usuario.getNome());
    }

    @Override
    public Usuario obterUsuarioPorNome(String nome) {
        return usuarios.get(nome);
    }

    @Override
    public List<Usuario> obterTodosUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
}

