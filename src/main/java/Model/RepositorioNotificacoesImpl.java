package Model;

import java.util.ArrayList;
import java.util.List;

public class RepositorioNotificacoesImpl implements RepositorioNotificacoes {
    private List<Notificacao> notificacoes;

    public RepositorioNotificacoesImpl() {
        this.notificacoes = new ArrayList<>();
    }

    @Override
    public void adicionarNotificacao(Notificacao notificacao) {
        notificacoes.add(notificacao);
    }

    @Override
    public List<Notificacao> obterNotificacoesPorDestinatario(String destinatario) {
        List<Notificacao> notificacoesPorDestinatario = new ArrayList<>();
        for (Notificacao notificacao : notificacoes) {
            if (notificacao.getDestinatario().equals(destinatario)) {
                notificacoesPorDestinatario.add(notificacao);
            }
        }
        return notificacoesPorDestinatario;
    }

    @Override
    public void marcarNotificacaoComoLida(Notificacao notificacao) {
        notificacao.marcarComoLida();
    }

    @Override
    public int contarNotificacoesNaoLidasPorDestinatario(String destinatario) {
        int count = 0;
        for (Notificacao notificacao : notificacoes) {
            if (notificacao.getDestinatario().equals(destinatario) && !notificacao.isLida()) {
                count++;
            }
        }
        return count;
    }
}
