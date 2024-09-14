package Model;

import java.util.List;

public interface RepositorioNotificacoes {
    void adicionarNotificacao(Notificacao notificacao);
    List<Notificacao> obterNotificacoesPorDestinatario(String destinatario);
    void marcarNotificacaoComoLida(Notificacao notificacao);
    int contarNotificacoesNaoLidasPorDestinatario(String destinatario);
    void atualizarNotificacoes();
    List<Notificacao> obterNotificacoesDoUsuario(String usuario);
    Notificacao marcarNotificacaoComoLida(Usuario usuario, String remetente, String mensagem);
    Notificacao obterNotificacao(Usuario usuarioLogado, String remetente, String mensagem);
    void enviarNotificacaoEmMassa(String nomeRemetente, List<String> nomesDestinatarios, String mensagem);
}

