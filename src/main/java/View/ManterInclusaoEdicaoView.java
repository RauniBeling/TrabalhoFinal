/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author rauni
 */
public class ManterInclusaoEdicaoView extends javax.swing.JInternalFrame {

    public void setNome(String nome) {
        lblNome.setText(nome);
    }
    public void setSenha(String senha) {
        lblSenha.setText(senha);
    }
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    public String getNome() {
        return lblNome.getText();
    }
    public String getSenha() {
         return lblSenha.getText();
    }
    public JButton getButtonCancelarManterEditar() {
        return buttonCancelarManterEditar;
    }
    public JButton getButtonSalvar() {
        return buttonSalvar;
    }
    public ManterInclusaoEdicaoView() {
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

        buttonCancelarManterEditar = new javax.swing.JButton();
        buttonSalvar = new javax.swing.JButton();
        lblNome = new javax.swing.JTextField();
        lblSenha = new javax.swing.JTextField();
        lblNomeManter = new javax.swing.JLabel();
        lblSenhaManter = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        buttonCancelarManterEditar.setText("Cancelar");
        buttonCancelarManterEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarManterEditarActionPerformed(evt);
            }
        });

        buttonSalvar.setText("Salvar");
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarActionPerformed(evt);
            }
        });

        lblNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblNomeActionPerformed(evt);
            }
        });

        lblSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblSenhaActionPerformed(evt);
            }
        });

        lblNomeManter.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblNomeManter.setText("Nome:");

        lblSenhaManter.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSenhaManter.setText("Senha:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(buttonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(buttonCancelarManterEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addComponent(lblNomeManter, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSenhaManter)
                        .addComponent(lblNome)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lblNomeManter)
                .addGap(10, 10, 10)
                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSenhaManter)
                .addGap(10, 10, 10)
                .addComponent(lblSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelarManterEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelarManterEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarManterEditarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarManterEditarActionPerformed

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed

    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void lblNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNomeActionPerformed

    private void lblSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSenhaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelarManterEditar;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JTextField lblNome;
    private javax.swing.JLabel lblNomeManter;
    private javax.swing.JTextField lblSenha;
    private javax.swing.JLabel lblSenhaManter;
    // End of variables declaration//GEN-END:variables


}
