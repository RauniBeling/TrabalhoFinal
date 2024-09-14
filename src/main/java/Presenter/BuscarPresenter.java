package Presenter;

import Model.Usuario;
import Model.RepositorioUsuarios;
import View.BuscarView;
import config.AppInitializer;
import Model.RepositorioNotificacoes;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;

import java.text.SimpleDateFormat;
import java.util.List;
import Model.Notificacao;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class BuscarPresenter {
    private BuscarView view;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioNotificacoes repositorioNotificacoes;

    public BuscarPresenter() {
        this.view = new BuscarView();
        this.repositorioUsuarios = AppInitializer.getRepositorioUsuarios();
        this.repositorioNotificacoes = AppInitializer.getRepositorioNotificacoes();
        configureView();
    }

    private void configureView() {
        view.getButtonBuscar().addActionListener(e -> buscarUsuarios());
        buscarUsuarios();
        DefaultTableModel model = (DefaultTableModel) view.getCamposBuscar().getModel();
        model.setColumnIdentifiers(new Object[]{"Nome", "Data de Cadastro", "Notificações Enviadas", "Notificações Lidas"});
    }

    private void buscarUsuarios() {
        String letra = view.getTxtUsuarioNome().getText().trim();
        List<Usuario> usuarios;
        if (letra.isEmpty()) {
            usuarios = repositorioUsuarios.obterTodosUsuarios();
        } else {
            usuarios = repositorioUsuarios.obterTodosUsuarios(letra);
        }
        atualizarTabelaUsuarios(usuarios);
    }

    private void mostrarNotificacoesEnviadas() {
        int selectedRow = view.getCamposBuscar().getSelectedRow();
        if (selectedRow == -1) {
            return;
        }

        String nomeUsuario = (String) view.getCamposBuscar().getValueAt(selectedRow, 0);
        List<Notificacao> notificacoesEnviadas = repositorioNotificacoes.obterNotificacoesDoUsuario(nomeUsuario);

        atualizarTabelaNotificacoes(notificacoesEnviadas);
    }

    private void atualizarTabelaNotificacoes(List<Notificacao> notificacoes) {
        DefaultTableModel model = (DefaultTableModel) view.getCamposBuscar().getModel();
        model.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (Notificacao notificacao : notificacoes) {
            model.addRow(new Object[]{
                notificacao.getRemetente().getNome(),
                dateFormat.format(notificacao.getDataEnvio()),
                notificacao.getDestinatario().getNome(),
                notificacao.getMensagem(),
                dateFormat.format(notificacao.getDataEnvio()),
                notificacao.isLida() ? "Sim" : "Não"
            });
        }
    }

    private void atualizarTabelaUsuarios(List<Usuario> usuarios) {
        DefaultTableModel model = (DefaultTableModel) view.getCamposBuscar().getModel();
        model.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Usuario usuario : usuarios) {
            int notificacoesEnviadas = repositorioNotificacoes.contarNotificacoesEnviadasPorUsuario(usuario.getNome());
            int notificacoesNaoLidas = repositorioNotificacoes.contarNotificacoesLidasPorUsuario(usuario.getNome());
            model.addRow(new Object[]{
                usuario.getNome(),
                dateFormat.format(usuario.getDataCadastro()),
                notificacoesEnviadas,
                notificacoesNaoLidas
            });
        }
        
        // Notificar a tabela sobre as mudanças no modelo
        model.fireTableDataChanged();
        
        // Se nenhum usuário for encontrado, exibir uma mensagem
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Nenhum usuário encontrado.", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public BuscarView getView() {
        return view;
    }

    public void showView() {
        view.setVisible(true);
    }
}

