package DAO;

import Model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        // Inicialize a conexão com o banco de dados aqui
    }

    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, senha, data_cadastro) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.setDate(3, new java.sql.Date(usuario.getDataCadastro().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET senha = ? WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getSenha());
            statement.setString(2, usuario.getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirUsuario(Usuario usuario) {
        String sql = "DELETE FROM usuarios WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario obterUsuarioPorNome(String nome) {
        String sql = "SELECT * FROM usuarios WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String senha = resultSet.getString("senha");
                Date dataCadastro = resultSet.getDate("data_cadastro");
                return new Usuario(nome, senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> obterTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                Date dataCadastro = resultSet.getDate("data_cadastro");
                Usuario usuario = new Usuario(nome, senha);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Implemente outros métodos do DAO, se necessário

    private void abrirConexao() {
        // Implemente o código para abrir a conexão com o banco de dados
    }

    private void fecharConexao() {
        // Implemente o código para fechar a conexão com o banco de dados
    }
}

