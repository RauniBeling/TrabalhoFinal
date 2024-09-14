package Presenter;

import DAO.NotificacaoDAO;
import Log.LogManager;
import Model.Notificacao;
import Model.RepositorioNotificacoes;
import Model.RepositorioUsuarios;
import Model.Usuario;
import View.TelaPrincipalView;
import View.VisualizarNotificacoesView;
import config.AppInitializer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class VisualizarNotificacoesPresenter {

    private VisualizarNotificacoesView view;
    private RepositorioNotificacoes repositorioNotificacoes;
    private RepositorioUsuarios repositorioUsuarios;
    private Usuario usuarioLogado;
    private NotificacaoDAO notificacaoDAO;
    private LogManager logManager = LogManager.getInstance();

    public VisualizarNotificacoesPresenter() {
        this.view = new VisualizarNotificacoesView();
        this.repositorioNotificacoes = AppInitializer.getRepositorioNotificacoes();
        this.repositorioUsuarios = AppInitializer.getRepositorioUsuarios();
        this.usuarioLogado = AppInitializer.getUsuarioLogado();
        this.notificacaoDAO = new NotificacaoDAO();
        configureView();
    }

    private void configureView() {
        view.getBtnMarcarComoLida().addActionListener(e -> marcarComoLida());
        view.getBtnFechar().addActionListener(e -> {
            atualizarTelaPrincipal();
            view.dispose();
        });
        atualizarNotificacoes();
    }

    private void atualizarNotificacoes() {

        List<Notificacao> notificacoes = notificacaoDAO.obterNotificacoes(usuarioLogado.getNome());
        view.atualizarNotificacoes(notificacoes);
    }

    private void marcarComoLida() {
        int[] selectedRows = view.getSelectedRows();
        if (selectedRows.length == 0) {
            // Show a message if no rows are selected
            JOptionPane.showMessageDialog(view, "Por favor, selecione pelo menos uma notificação.");
            return;
        }
        int notificacoesMarcarLidas = 0;
        for (int row : selectedRows) {
            int jaLida = ((String) view.getTabelaNotificacoes().getValueAt(row, 4)).equals("Sim") ? 1 : 0;
            if (jaLida == 0) {
                int idNotificacao = (int) view.getTabelaNotificacoes().getValueAt(row, 0);
                notificacaoDAO.marcarNotificacaoComoLida(idNotificacao);
                logManager.log("Leitura de notificação", String.valueOf(idNotificacao), usuarioLogado.getNome());
                notificacoesMarcarLidas += 1;
            }
        }
        atualizarNotificacoes();
        atualizarTelaPrincipal(); // Add this line
        JOptionPane.showMessageDialog(view, notificacoesMarcarLidas + " notificação(ões) marcada(s) como lida(s).");
    }

    private void atualizarTelaPrincipal() {
        TelaPrincipalView telaPrincipalView = (TelaPrincipalView) SwingUtilities.getWindowAncestor(view);
        if (telaPrincipalView != null) {
            List<Notificacao> notificacoesNaoLidas = notificacaoDAO.obterNotificacoes(usuarioLogado.getNome());
            telaPrincipalView.atualizarContadorNotificacoes(notificacoesNaoLidas.size());
        }
    }

    public VisualizarNotificacoesView getView() {
        return view;
    }

    public void showView() {
        view.setVisible(true);
    }
}
