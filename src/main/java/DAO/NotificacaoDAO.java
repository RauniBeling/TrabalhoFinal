/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Notificacao;
import Model.Usuario;
import config.AppInitializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ruanr
 */
public class NotificacaoDAO {
    private Connection connection;
    private static final String DB_URL = "jdbc:sqlite:Banco.db";
    private UsuarioDAO usuarioDAO;
    
    public NotificacaoDAO() {
        usuarioDAO = AppInitializer.getUsuarioDAO();
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarNotificacao(Notificacao notificacao) {
        String sql = "INSERT INTO notificacoes (remetente, destinatario, mensagem, data_envio, lida) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, notificacao.getRemetente().getNome());
            statement.setString(2, notificacao.getDestinatario().getNome());
            statement.setString(3, notificacao.getMensagem());
            statement.setTimestamp(4, new Timestamp(notificacao.getDataEnvio().getTime()));
            statement.setBoolean(5, notificacao.isLida());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Notificacao obterNotificacaoPorId(int id) {
        String sql = "SELECT * FROM notificacoes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getNotificacao(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Notificacao> obterNotificacoes(String nomeUsuario) {
        List<Notificacao> notificacoes = new ArrayList<>();
        String sql = "SELECT * FROM notificacoes WHERE destinatario = ? ORDER BY data_envio DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nomeUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Notificacao notificacao = getNotificacao(resultSet);
                    notificacoes.add(notificacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notificacoes;
    }
    public void marcarNotificacaoComoLida(int idNotificacao) {
        String sql = "UPDATE notificacoes SET lida = true WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idNotificacao);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarNotificacoes(Notificacao notificacao) {
        String sql = "UPDATE notificacoes SET lida = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, notificacao.isLida());
            statement.setInt(2, notificacao.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Notificacao> obterTodasNotificacoesUsuario(String nomeUsuario) {
        List<Notificacao> notificacoesDoUsuario = new ArrayList<>();
        String sql = "SELECT * FROM notificacoes WHERE destinatario = ? ORDER BY data_envio DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nomeUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Notificacao notificacao = getNotificacao(resultSet);
                    notificacoesDoUsuario.add(notificacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notificacoesDoUsuario;
    }
    private Notificacao getNotificacao(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String mensagem = resultSet.getString("mensagem");
        String nomeRemetente = resultSet.getString("remetente");
        String nomeDestinatario = resultSet.getString("destinatario");
        Date dataEnvio = new Date(resultSet.getTimestamp("data_envio").getTime());
        boolean lida = resultSet.getBoolean("lida");

        Usuario remetente = usuarioDAO.obterUsuarioPorNome(nomeRemetente);
        Usuario destinatario = usuarioDAO.obterUsuarioPorNome(nomeDestinatario);

        Notificacao notificacao = new Notificacao(id, mensagem, remetente, destinatario, lida);
        notificacao.setDataEnvio(dataEnvio);

        return notificacao;
    }
}
