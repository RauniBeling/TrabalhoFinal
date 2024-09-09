package Service;

import Model.RepositorioUsuarios;
import Model.Usuario;

public class AutenticacaoService {
    private RepositorioUsuarios repositorioUsuarios;
    private Usuario usuarioAutenticado;
    public AutenticacaoService(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public boolean autenticar(String nome, String senha) {
        Usuario usuario = repositorioUsuarios.obterUsuarioPorNome(nome);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            usuarioAutenticado = usuario;
            return true;
        }
        return false;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public boolean isAdmin() {
        if (usuarioAutenticado == null) {
            return false;
        }
        return usuarioAutenticado.isAdministrador();
    }

    public boolean estaPermitido() {
        if (usuarioAutenticado == null) {
            return false;
        }
        return usuarioAutenticado.isPermitido();
    }
}
