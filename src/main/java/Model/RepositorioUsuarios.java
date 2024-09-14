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
    void enviarNotificacao(Usuario remetente, Usuario destinatario, String mensagem);
    List<Notificacao> obterNotificacoesNaoLidas(Usuario usuario);
    void marcarNotificacaoComoLida(Notificacao notificacao);
}

