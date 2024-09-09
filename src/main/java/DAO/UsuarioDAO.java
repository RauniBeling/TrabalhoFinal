package DAO;

import Model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    private static final String DB_URL = "jdbc:sqlite:Banco.db";
    
    public UsuarioDAO() {
        try {
            
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
                criarTabelaUsuarios();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void criarTabelaUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                     "nome TEXT PRIMARY KEY," +
                     "senha TEXT NOT NULL," +
                     "data_cadastro DATE NOT NULL," +
                     "tipo TEXT NOT NULL," +
                     "permitido BOOLEAN NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, senha, data_cadastro, tipo, permitido) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.setDate(3, new java.sql.Date(usuario.getDataCadastro().getTime()));
            statement.setString(4, usuario.getTipo());
            statement.setBoolean(5, usuario.isPermitido());
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
                String tipo = resultSet.getString("tipo");
                boolean permitido = resultSet.getBoolean("permitido");
                return new Usuario(nome, senha, tipo, permitido);
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
                String tipo = resultSet.getString("tipo");
                boolean permitido = resultSet.getBoolean("permitido");
                Usuario usuario = new Usuario(nome, senha, tipo, permitido);
                usuario.setDataCadastro(dataCadastro);
                usuario.setTipo(tipo);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

}

