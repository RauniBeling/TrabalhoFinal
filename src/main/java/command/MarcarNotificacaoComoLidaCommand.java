package command;

import Model.Notificacao;
import Model.RepositorioNotificacoes;

public class MarcarNotificacaoComoLidaCommand implements Command {
    private RepositorioNotificacoes repositorio;
    private Notificacao notificacao;

    public MarcarNotificacaoComoLidaCommand(RepositorioNotificacoes repositorio, Notificacao notificacao) {
        this.repositorio = repositorio;
        this.notificacao = notificacao;
    }

    @Override
    public void execute() {
        repositorio.marcarNotificacaoComoLida(notificacao);
    }
}
