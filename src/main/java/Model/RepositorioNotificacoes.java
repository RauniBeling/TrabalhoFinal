package Model;

import java.util.List;

public interface RepositorioNotificacoes {
    void adicionarNotificacao(Notificacao notificacao);
    List<Notificacao> obterNotificacoesPorDestinatario(String destinatario);
    void marcarNotificacaoComoLida(Notificacao notificacao);
    int contarNotificacoesNaoLidasPorDestinatario(String destinatario);
}

