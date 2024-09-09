package Presenter;

import Model.RepositorioUsuarios;
import Model.RepositorioNotificacoes;
import Model.Usuario;
import Service.AutenticacaoService;
import View.TelaPrincipalView;


public class TelaPrincipalPresenter {
    private TelaPrincipalView view;
    private AutenticacaoService autenticacaoService;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioNotificacoes repositorioNotificacoes;

    public TelaPrincipalPresenter(AutenticacaoService autenticacaoService, RepositorioUsuarios repositorioUsuarios) {
        this.view = new TelaPrincipalView();
        this.repositorioUsuarios = repositorioUsuarios;
        this.autenticacaoService = autenticacaoService;

        inicializarTela();
    }

    private void inicializarTela() {
        if (isDatabaseEmpty()) {
            criarPrimeiroUsuario();
        } else {
            autenticarUsuario();
        }
        atualizarInformacoesTela();
        
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
            view.atualizarInformacoesUsuario(usuarioAtual.getNome(), usuarioAtual.getTipo());
            view.habilitarMenuAdministrador(autenticacaoService.isAdmin());
            atualizarContadorNotificacoes();
            view.exibirTela();
        } else {
            view.exibirMensagem("Seu acesso ainda não foi autorizado pelo administrador. Por favor, aguarde a aprovação.");
            autenticarUsuario();
        }
    }

    private void atualizarContadorNotificacoes() {
        Usuario usuarioAtual = autenticacaoService.getUsuarioAutenticado();
        if (usuarioAtual != null) {
//            int quantidadeNotificacoes = repositorioNotificacoes.contarNotificacoesNaoLidasPorDestinatario(usuarioAtual.getNome());
  //          view.atualizarContadorNotificacoes(quantidadeNotificacoes);
        }
    }

  

    public void abrirTelaManterInclusaoEdicao() {
        if (verificarAutenticacaoEPermissao()) {
            new ManterInclusaoEdicaoPresenter().showView();
        }
    }

    public void abrirTelaBuscar() {
        if (verificarAutenticacaoEPermissao()) {
            new BuscarPresenter().showView();
        }
    }

    private boolean verificarAutenticacaoEPermissao() {
        if (!autenticacaoService.isAdmin()) {
            view.exibirMensagem("Você precisa ser um administrador para realizar esta ação.");
            return false;
        }
        return true;
    }
}
