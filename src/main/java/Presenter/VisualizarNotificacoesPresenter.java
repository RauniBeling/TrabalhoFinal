package Presenter;

import Model.Notificacao;
import Model.RepositorioNotificacoes;
import Model.Usuario;
import View.VisualizarNotificacoesView;
import java.util.List;

public class VisualizarNotificacoesPresenter {
    private VisualizarNotificacoesView view;
    private RepositorioNotificacoes repositorioNotificacoes;
    private Usuario usuarioLogado;

    public VisualizarNotificacoesPresenter(VisualizarNotificacoesView view, RepositorioNotificacoes repositorioNotificacoes, Usuario usuarioLogado) {
        this.view = view;
        this.repositorioNotificacoes = repositorioNotificacoes;
        this.usuarioLogado = usuarioLogado;
        configureView();
    }

    private void configureView() {
        //view.getButtonMarcarComoLida().addActionListener(e -> marcarComoLida());
        atualizarListaNotificacoes();
    }

    private void atualizarListaNotificacoes() {
        List<Notificacao> notificacoes = repositorioNotificacoes.obterNotificacoesPorDestinatario(usuarioLogado.getNome());
      //  view.preencherListaNotificacoes(notificacoes);
    }

    private void marcarComoLida() {
        //Notificacao notificacaoSelecionada = view.getNotificacaoSelecionada();
       // if (notificacaoSelecionada != null) {
         //   repositorioNotificacoes.marcarNotificacaoComoLida(notificacaoSelecionada);
           // atualizarListaNotificacoes();
       // }
    }
}
