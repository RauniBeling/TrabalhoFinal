/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Model.Usuario;
import Presenter.BuscarPresenter;
import Presenter.EnviarNotificacaoPresenter;
import Presenter.ManterInclusaoEdicaoPresenter;
import Presenter.VisualizarNotificacoesPresenter;
import config.AppInitializer;
import Service.AutenticacaoService;

/**
 *
 * @author rauni
 */
public class TelaPrincipalView extends javax.swing.JFrame {
    private AutenticacaoService autenticacaoService;

    /**
     * @param btnEnviaNotificacao the btnEnviaNotificacao to set
     */
    public void setBtnEnviaNotificacao(javax.swing.JMenuItem btnEnviaNotificacao) {
        this.btnEnviaNotificacao = btnEnviaNotificacao;
    }

    private AppInitializer config;
    public javax.swing.JMenuItem getMenuAdicionarEditar() {
        return AdicionarEditar;
    }
    public javax.swing.JMenuItem getMenuBuscar() {
        return Buscar;
    }
    public TelaPrincipalView() {
        config = new AppInitializer();
        autenticacaoService = AppInitializer.getAutenticacaoService();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TelaPrincipalView = new javax.swing.JDesktopPane();
        lblTipoUsuario = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        lblNotificacao = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        Buscar = new javax.swing.JMenuItem();
        AdicionarEditar = new javax.swing.JMenuItem();
        mnConfig = new javax.swing.JMenuItem();
        btnEnviaNotificacao = new javax.swing.JMenuItem();
        mnEsqueciSenha = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTipoUsuario.setDisplayedMnemonic('l');
        lblTipoUsuario.setText("Tipo: N/A");
        lblTipoUsuario.setPreferredSize(new java.awt.Dimension(120, 40));

        lblLogin.setDisplayedMnemonic('l');
        lblLogin.setText("Usuário não logado");
        lblLogin.setPreferredSize(new java.awt.Dimension(140, 40));

        lblNotificacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16px bell.png"))); // NOI18N
        lblNotificacao.setText("0");
        lblNotificacao.setPreferredSize(new java.awt.Dimension(70, 40));
        lblNotificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblNotificacaoMousePressed(evt);
            }
        });

        TelaPrincipalView.setLayer(lblTipoUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TelaPrincipalView.setLayer(lblLogin, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TelaPrincipalView.setLayer(lblNotificacao, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout TelaPrincipalViewLayout = new javax.swing.GroupLayout(TelaPrincipalView);
        TelaPrincipalView.setLayout(TelaPrincipalViewLayout);
        TelaPrincipalViewLayout.setHorizontalGroup(
            TelaPrincipalViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaPrincipalViewLayout.createSequentialGroup()
                .addGap(880, 880, 880)
                .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TelaPrincipalViewLayout.setVerticalGroup(
            TelaPrincipalViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaPrincipalViewLayout.createSequentialGroup()
                .addGap(750, 750, 750)
                .addGroup(TelaPrincipalViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        fileMenu.setMnemonic('f');
        fileMenu.setText("Menu Admin");

        Buscar.setMnemonic('o');
        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        fileMenu.add(Buscar);

        AdicionarEditar.setText("Adicionar ou Editar");
        AdicionarEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarEditarActionPerformed(evt);
            }
        });
        fileMenu.add(AdicionarEditar);

        btnEnviaNotificacao.setText("Enviar notificação");
        btnEnviaNotificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviaNotificacaoActionPerformed(evt);
            }
        });
        fileMenu.add(btnEnviaNotificacao);

        menuBar.add(fileMenu);

        mnEsqueciSenha.setText("Menu user");

        jMenuItem1.setLabel("esqueci a senha");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnEsqueciSenha.add(jMenuItem1);
        jMenuItem1.getAccessibleContext().setAccessibleName("esqueci senha");

        menuBar.add(mnEsqueciSenha);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelaPrincipalView)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelaPrincipalView)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        BuscarPresenter buscar = new BuscarPresenter();
        TelaPrincipalView.add(buscar.getView());
        buscar.showView();
    }//GEN-LAST:event_BuscarActionPerformed

    private void AdicionarEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarEditarActionPerformed
        ManterInclusaoEdicaoPresenter manter = new ManterInclusaoEdicaoPresenter(AppInitializer.getRepositorioUsuarios());
        TelaPrincipalView.add(manter.getView());
        manter.showView();
    }//GEN-LAST:event_AdicionarEditarActionPerformed

    private void mnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnConfigActionPerformed
        JOptionPane.showMessageDialog(this, "Configuração menu clicked");
        
    }//GEN-LAST:event_mnConfigActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void lblNotificacaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNotificacaoMousePressed
        // TODO add your handling code here:
        VisualizarNotificacoesPresenter visualizarNotificacoesPresenter = new VisualizarNotificacoesPresenter();
        TelaPrincipalView.add(visualizarNotificacoesPresenter.getView());
        visualizarNotificacoesPresenter.showView();
    }//GEN-LAST:event_lblNotificacaoMousePressed

    private void btnEnviaNotificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviaNotificacaoActionPerformed
        EnviarNotificacaoPresenter enviarNotificacaoPresenter = new EnviarNotificacaoPresenter();
        TelaPrincipalView.add(enviarNotificacaoPresenter.getView());
        enviarNotificacaoPresenter.showView();
    }//GEN-LAST:event_btnEnviaNotificacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AdicionarEditar;
    private javax.swing.JMenuItem Buscar;
    private javax.swing.JDesktopPane TelaPrincipalView;
    private javax.swing.JMenuItem btnEnviaNotificacao;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNotificacao;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnConfig;
    private javax.swing.JMenu mnEsqueciSenha;
    // End of variables declaration//GEN-END:variables
    /**
     * @return the lblLogin
     */
    public javax.swing.JLabel getLblLogin() {
        return lblLogin;
    }

    /**
     * @return the lblNotificacao
     */
    public javax.swing.JLabel getLblNotificacao() {
        return lblNotificacao;
    }

    /**
     * @return the lblTipoUsuario
     */
    public javax.swing.JLabel getLblTipoUsuario() {
        return lblTipoUsuario;
    }
     public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
    public void atualizarInformacoesUsuario() {
        Usuario usuarioLogado = autenticacaoService.getUsuarioAutenticado();
        if (usuarioLogado != null) {
            lblLogin.setText("Usuário: " + usuarioLogado.getNome());
            lblTipoUsuario.setText("Tipo: " + (usuarioLogado.isAdministrador() ? "Administrador" : "Usuário"));
            habilitarMenuAdministrador(usuarioLogado.isAdministrador());
        } else {
            lblLogin.setText("Usuário não logado");
            lblTipoUsuario.setText("Tipo: N/A");
            habilitarMenuAdministrador(false);
        }
    }
    
    public void habilitarMenuAdministrador(boolean habilitar) {
        AdicionarEditar.setEnabled(habilitar);
        Buscar.setEnabled(habilitar);
        btnEnviaNotificacao.setEnabled(habilitar);
        mnConfig.setEnabled(habilitar);
    }
    public String solicitarNomeUsuario() {
        return JOptionPane.showInputDialog(this, "Digite seu nome de usuário:");
    }
    public String solicitarSenha() {
        return JOptionPane.showInputDialog(this, "Digite sua senha:");
    }
    public void fecharTela() {
        dispose();
    }
    public void exibirTela() {
        setVisible(true);
    }
    private JButton notificacoesButton;

    public JButton getNotificacoesButton() {
        return notificacoesButton;
    }

    public void atualizarContadorNotificacoes(int quantidade) {
        lblNotificacao.setText(String.valueOf(quantidade));
    }

    /**
     * @return the btnEnviaNotificacao
     */
    public javax.swing.JMenuItem getBtnEnviaNotificacao() {
        return btnEnviaNotificacao;
    }

    /**
     * @return the jMenuItem1
     */
    public javax.swing.JMenuItem getjMenuItem1() {
        return jMenuItem1;
    }
}
