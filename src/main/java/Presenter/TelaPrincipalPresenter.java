package Presenter;

import Model.RepositorioUsuarios;
import Model.RepositorioNotificacoes;
import Model.Usuario;
import Service.AutenticacaoService;
import View.TelaPrincipalView;
import config.AppInitializer;

public class TelaPrincipalPresenter {

    private TelaPrincipalView view;
    private AutenticacaoService autenticacaoService;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioNotificacoes repositorioNotificacoes;

    public TelaPrincipalPresenter() {
        this.view = new TelaPrincipalView();
        this.autenticacaoService = AppInitializer.getAutenticacaoService();
        this.repositorioUsuarios = AppInitializer.getRepositorioUsuarios();
        this.repositorioNotificacoes = AppInitializer.getRepositorioNotificacoes();

        inicializarTela();
    }

    private void inicializarTela() {
        if (isDatabaseEmpty()) {
            criarPrimeiroUsuario();
        } else {
            autenticarUsuario();
        }
        atualizarInformacoesTela();

        view.getLblNotificacao().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                abrirVisualizarNotificacoes();
            }
        });

    }

    private boolean isDatabaseEmpty() {
        return repositorioUsuarios.obterTodosUsuarios().isEmpty();
    }

    private void criarPrimeiroUsuario() {
        String nome = view.solicitarNomeUsuario();
        String senha = view.solicitarSenha();

        if (nome == null || senha == null || nome.trim().isEmpty() || senha.trim().isEmpty()) {
            view.exibirMensagem("Nome de usuário e senha são obrigatórios. O programa será encerrado.");
            System.exit(0);
        }

        Usuario primeiroUsuario = new Usuario(nome, senha, "Administrador", true);
        repositorioUsuarios.adicionarUsuario(primeiroUsuario);
        view.exibirMensagem("Usuário administrador criado com sucesso!");
        autenticarUsuario();
    }

    private void autenticarUsuario() {
        String nome = view.solicitarNomeUsuario();
        String senha = view.solicitarSenha();

        if (nome == null || senha == null) {
            System.exit(0);
        }

        if (autenticacaoService.autenticar(nome, senha)) {
            if (autenticacaoService.estaPermitido()) {
                atualizarInformacoesTela();
            } else {
                view.exibirMensagem("Seu acesso ainda não foi autorizado pelo administrador. Por favor, aguarde a aprovação.");
                autenticarUsuario();
            }
        } else {
            Usuario usuario = repositorioUsuarios.obterUsuarioPorNome(nome);
            if (usuario == null) {
                registrarNovoUsuario(nome, senha);
            } else {
                view.exibirMensagem("Senha inválida ou usuário não autorizado!");
                autenticarUsuario();
            }
        }
    }

    private void registrarNovoUsuario(String nome, String senha) {
        Usuario novoUsuario = new Usuario(nome, senha, "Comum", false);
        repositorioUsuarios.adicionarUsuario(novoUsuario);
        view.exibirMensagem("Usuário registrado com sucesso! Aguarde a autorização do administrador.");
        autenticarUsuario();
        view.exibirTela();
    }

    private void atualizarInformacoesTela() {
        Usuario usuarioAtual = autenticacaoService.getUsuarioAutenticado();
        if (usuarioAtual != null && usuarioAtual.isPermitido()) {
            view.atualizarInformacoesUsuario();
            view.habilitarMenuAdministrador(autenticacaoService.isAdmin());
            atualizarContadorNotificacoes();
            view.exibirTela();
        } else {
            view.exibirMensagem("Seu acesso ainda não foi autorizado pelo administrador. Por favor, aguarde a aprovação.");
            autenticarUsuario();
        }
    }

    private void atualizarContadorNotificacoes() {
        Usuario usuarioLogado = autenticacaoService.getUsuarioAutenticado();
        int quantidadeNaoLidas = repositorioNotificacoes.obterNotificacoesDoUsuario(usuarioLogado.getNome()).size();
        view.atualizarContadorNotificacoes(quantidadeNaoLidas);
    }

    private boolean verificarAutenticacaoEPermissao() {
        if (!autenticacaoService.isAdmin()) {
            view.exibirMensagem("Você precisa ser um administrador para realizar esta ação.");
            return false;
        }
        return true;
    }

    private void abrirVisualizarNotificacoes() {
        Usuario usuarioLogado = autenticacaoService.getUsuarioAutenticado();
        VisualizarNotificacoesPresenter visualizarNotificacoesPresenter = new VisualizarNotificacoesPresenter();
        visualizarNotificacoesPresenter.showView();
    }
}
