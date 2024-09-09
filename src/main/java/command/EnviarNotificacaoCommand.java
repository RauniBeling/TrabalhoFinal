package command;

import Model.Notificacao;
import Model.RepositorioNotificacoes;

public class EnviarNotificacaoCommand implements Command {
    private RepositorioNotificacoes repositorio;
    private Notificacao notificacao;

    public EnviarNotificacaoCommand(RepositorioNotificacoes repositorio, Notificacao notificacao) {
        this.repositorio = repositorio;
        this.notificacao = notificacao;
    }

    @Override
    public void execute() {
        repositorio.adicionarNotificacao(notificacao);
    }
}
