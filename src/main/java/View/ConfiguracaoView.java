/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import Log.LogManager;

/**
 *
 * @author ruanr
 */
public class ConfiguracaoView extends JInternalFrame {

    private JComboBox<String> formatoLogComboBox;
    private JButton salvarButton;
    private Preferences prefs;

    /**
     * Creates new form ConfiguracaoView
     */
    public ConfiguracaoView() {
        super("Configurações", true, true, true, true);
        prefs = Preferences.userNodeForPackage(ConfiguracaoView.class);
        initComponents();
        setSize(300, 150);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Formato do arquivo de log:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        formatoLogComboBox = new JComboBox<>(new String[]{ "CSV", "JSON"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(formatoLogComboBox, gbc);

        // Carregar a configuração salva
        String formatoSalvo = prefs.get("formatoLog", "CSV");
        formatoLogComboBox.setSelectedItem(formatoSalvo);

        salvarButton = new JButton("Salvar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(salvarButton, gbc);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formatoSelecionado = (String) formatoLogComboBox.getSelectedItem();
                prefs.put("formatoLog", formatoSelecionado);
                
                // Update LogManager with the new format
                LogManager.getInstance().setLogFormat(formatoSelecionado);
                
                JOptionPane.showMessageDialog(ConfiguracaoView.this, 
                    "Configuração salva com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        add(panel, BorderLayout.CENTER);
    }
    // </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-ENDentials
}
