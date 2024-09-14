package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import config.AppInitializer;
import DAO.UsuarioDAO;

public class RepositorioNotificacoesImpl implements RepositorioNotificacoes {
    private UsuarioDAO usuarioDAO;

    public RepositorioNotificacoesImpl() {
        this.usuarioDAO = AppInitializer.getUsuarioDAO();
    }

    @Override
    public void adicionarNotificacao(Notificacao notificacao) {
        usuarioDAO.cadastrarNotificacao(notificacao);
    }

    @Override
    public List<Notificacao> obterNotificacoesPorDestinatario(String destinatario) {
        return usuarioDAO.obterNotificacoesNaoLidas(destinatario);
    }

    @Override
    public void marcarNotificacaoComoLida(Notificacao notificacao) {
        notificacao.marcarComoLida();
    }

    @Override
    public int contarNotificacoesNaoLidasPorDestinatario(String destinatario) {
        int count = 0;
        for (Notificacao notificacao : usuarioDAO.obterNotificacoesNaoLidas(destinatario)) {
            if (notificacao.getDestinatario().equals(destinatario) && !notificacao.isLida()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void atualizarNotificacoes() {
        Date dataAtual = new Date();
        long umaSemanaEmMillis = 7 * 24 * 60 * 60 * 1000L; // 7 dias em milissegundos
        
        usuarioDAO.obterNotificacoesNaoLidas(destinatario).removeIf(notificacao -> {
            long diferenca = dataAtual.getTime() - notificacao.getDataEnvio().getTime();
            return diferenca > umaSemanaEmMillis || notificacao.isLida();
        });
    }

    @Override
    public List<Notificacao> obterNotificacoesDoUsuario(String usuario) {
        List<Notificacao> notificacoesDoUsuario = new ArrayList<>();
        for (Notificacao notificacao : usuarioDAO.obterNotificacoesNaoLidas(usuario)) {
            if (notificacao.getDestinatario().getNome().equals(usuario)) {
                notificacoesDoUsuario.add(notificacao);
            }
        }
        return notificacoesDoUsuario;
    }

    @Override
    public Notificacao marcarNotificacaoComoLida(Usuario usuario, String remetente, String mensagem) {
        for (Notificacao notificacao : usuarioDAO.obterNotificacoesNaoLidas(usuario)) {
            if (notificacao.getDestinatario().equals(usuario) &&
                notificacao.getRemetente().getNome().equals(remetente) &&
                notificacao.getMensagem().equals(mensagem) &&
                !notificacao.isLida()) {
                
                notificacao.marcarComoLida();
                return notificacao;
            }
        }
        return null;
    }

    @Override
    public void enviarNotificacaoEmMassa(String nomeRemetente, List<String> nomesDestinatarios, String mensagem) {
        Usuario remetente = usuarioDAO.obterUsuarioPorNome(nomeRemetente);
        for (String nomeDestinatario : nomesDestinatarios) {
            Usuario destinatario = usuarioDAO.obterUsuarioPorNome(nomeDestinatario);
            if (destinatario != null) {
                Notificacao novaNotificacao = new Notificacao(mensagem, remetente, destinatario);
                adicionarNotificacao(novaNotificacao);
            }
        }
    }

    @Override
    public Notificacao obterNotificacao(Usuario usuarioLogado, String remetente, String mensagem) {
        for (Notificacao notificacao : usuarioDAO.obterNotificacoesNaoLidas(usuarioLogado.getNome())) {
            if (notificacao.getDestinatario().equals(usuarioLogado) &&
                notificacao.getRemetente().getNome().equals(remetente) &&
                notificacao.getMensagem().equals(mensagem)) {
                return notificacao;
            }
        }
        return null;
    }
}
