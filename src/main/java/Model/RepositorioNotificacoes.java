package Model;

import java.util.List;

public interface RepositorioNotificacoes {
    void adicionarNotificacao(Notificacao notificacao);
    int contarNotificacoesNaoLidasPorDestinatario(String destinatario);
    void atualizarNotificacoes(String usuario);
    List<Notificacao> obterNotificacoesDoUsuario(String usuario);
    Notificacao marcarNotificacaoComoLida(int idNotificacao);
    void enviarNotificacaoEmMassa(String nomeRemetente, List<String> nomesDestinatarios, String mensagem);
    int contarNotificacoesEnviadasPorUsuario(String usuario);
    int contarNotificacoesLidasPorUsuario(String usuario);
    
}

