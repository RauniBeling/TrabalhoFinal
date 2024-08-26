package Service;

import Model.RepositorioUsuarios;
import Model.Usuario;

public class AutenticacaoService {
    private RepositorioUsuarios repositorioUsuarios;

    public AutenticacaoService(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public boolean autenticar(String nome, String senha) {
        Usuario usuario = repositorioUsuarios.obterUsuarioPorNome(nome);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }
}
