package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import config.AppInitializer;
import DAO.NotificacaoDAO;
import DAO.UsuarioDAO;
import java.util.stream.Collectors;

public class RepositorioNotificacoesImpl implements RepositorioNotificacoes {
    private NotificacaoDAO notificacaoDAO;
    private UsuarioDAO usuarioDAO;
    public RepositorioNotificacoesImpl() {
        this.notificacaoDAO = AppInitializer.getNotificacaoDAO();
        this.usuarioDAO = AppInitializer.getUsuarioDAO();
    }

    @Override
    public void adicionarNotificacao(Notificacao notificacao) {
        notificacaoDAO.cadastrarNotificacao(notificacao);
    }

    @Override
    public int contarNotificacoesNaoLidasPorDestinatario(String destinatario) {
        int count = 0;
        for (Notificacao notificacao : notificacaoDAO.obterNotificacoes(destinatario)) {
            if (notificacao.getDestinatario().equals(destinatario) && !notificacao.isLida()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void atualizarNotificacoes(String usuario) {
        Date dataAtual = new Date();
        long umaSemanaEmMillis = 7 * 24 * 60 * 60 * 1000L; // 7 dias em milissegundos
        
        List<Notificacao> todasNotificacoes = notificacaoDAO.obterNotificacoes(usuario);
        todasNotificacoes.removeIf(notificacao -> {
            long diferenca = dataAtual.getTime() - notificacao.getDataEnvio().getTime();
            return diferenca > umaSemanaEmMillis || notificacao.isLida();
        });
        for (Notificacao notificacao : todasNotificacoes) {
            notificacaoDAO.atualizarNotificacoes(notificacao);
        }
    }

    @Override
    public List<Notificacao> obterNotificacoesDoUsuario(String usuario) {
        return notificacaoDAO.obterNotificacoes(usuario);
    }

    @Override
    public Notificacao marcarNotificacaoComoLida(int idNotificacao) {
        Notificacao notificacao = notificacaoDAO.obterNotificacaoPorId(idNotificacao);
        if (notificacao != null && !notificacao.isLida()) {
            notificacao.setLida(true);
            notificacaoDAO.marcarNotificacaoComoLida(idNotificacao); // Pass the ID
            return notificacao;
        }
        return null;
    }

    @Override
    public void enviarNotificacaoEmMassa(String nomeRemetente, List<String> nomesDestinatarios, String mensagem) {
        Usuario remetente = usuarioDAO.obterUsuarioPorNome(nomeRemetente);
        if (remetente == null) {
            throw new IllegalArgumentException("Remetente não encontrado: " + nomeRemetente);
        }
        for (String nomeDestinatario : nomesDestinatarios) {
            Usuario destinatario = usuarioDAO.obterUsuarioPorNome(nomeDestinatario);
            if (destinatario != null) {
                Notificacao novaNotificacao = new Notificacao(mensagem, remetente, destinatario);
                adicionarNotificacao(novaNotificacao);
            } else {
                // Log or handle the case where a destinatario is not found
                System.out.println("Destinatário não encontrado: " + nomeDestinatario);
            }
        }
    }

    @Override
    public int contarNotificacoesEnviadasPorUsuario(String usuario) {
        return (int) notificacaoDAO.obterNotificacoes(usuario).stream()
                .count();
    }

    @Override
    public int contarNotificacoesLidasPorUsuario(String usuario) {
        return (int) notificacaoDAO.obterNotificacoes(usuario).stream()
                .filter(Notificacao::isLida)
                .count();
    }

}
