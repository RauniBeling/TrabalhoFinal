package Presenter;

import DAO.NotificacaoDTO;
import Model.Notificacao;
import Model.RepositorioNotificacoes;
import Model.RepositorioUsuarios;
import Model.Usuario;
import View.VisualizarNotificacoesView;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class VisualizarNotificacoesPresenter {

    private VisualizarNotificacoesView view;
    private RepositorioNotificacoes repositorioNotificacoes;
    private RepositorioUsuarios repositorioUsuarios;
    private Usuario usuarioLogado;

    public VisualizarNotificacoesPresenter(RepositorioNotificacoes repositorioNotificacoes, RepositorioUsuarios repositorioUsuarios, Usuario usuarioLogado) {
        this.view = new VisualizarNotificacoesView();
        this.repositorioNotificacoes = repositorioNotificacoes;
        this.repositorioUsuarios = repositorioUsuarios;
        this.usuarioLogado = usuarioLogado;
        configureView();
    }

    private void configureView() {
        view.getBtnMarcarComoLida().addActionListener(e -> marcarComoLida());
        view.getBtnFechar().addActionListener(e -> view.dispose());
        atualizarNotificacoes();
    }

    private void atualizarNotificacoes() {
        List<Notificacao> notificacoes = repositorioNotificacoes.obterNotificacoesDoUsuario(usuarioLogado.getNome());
        List<NotificacaoDTO> notificacoesDTO = notificacoes.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        view.atualizarNotificacoes(notificacoesDTO);
    }

    private NotificacaoDTO converterParaDTO(Notificacao notificacao) {
        return new NotificacaoDTO(
            notificacao.getRemetente().getNome(),
            notificacao.getMensagem(), (Date) notificacao.getDataEnvio(),
            notificacao.isLida()
        );
    }

    private void marcarComoLida() {
        int[] selectedRows = view.getSelectedRows();
        if (selectedRows.length == 0) {
            // Show a message if no rows are selected
            JOptionPane.showMessageDialog(view, "Por favor, selecione pelo menos uma notificação.");
            return;
        }

        for (int row : selectedRows) {
            String remetente = (String) view.getTabelaNotificacoes().getValueAt(row, 0);
            String mensagem = (String) view.getTabelaNotificacoes().getValueAt(row, 1);
            Notificacao notificacao = repositorioNotificacoes.obterNotificacao(usuarioLogado, remetente, mensagem);
            if (notificacao != null) {
                repositorioNotificacoes.marcarNotificacaoComoLida(notificacao);
            }
        }
        atualizarNotificacoes();
        JOptionPane.showMessageDialog(view, selectedRows.length + " notificação(ões) marcada(s) como lida(s).");
    }

    public VisualizarNotificacoesView getView() {
        return view;
    }

    public void showView() {
        view.setVisible(true);
    }
}
