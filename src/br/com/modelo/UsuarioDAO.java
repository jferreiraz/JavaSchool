package br.com.modelo;

import br.com.entidade.Login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioDAO extends DAO {

    public ResultSet autenticacaoUsuario(Login l) {
        try {
            abrirBanco();
            String query = "select * FROM usuario where usuario=? and senha=?";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ps.setString(1, l.getUsuario());
            ps.setString(2, l.getSenha());
            ResultSet tr = ps.executeQuery();
            return tr;
        } catch (SQLException e) {
            System.out.println("Erro autenticaoUsuario: " + e.getMessage());
            return null;
        }
    }

    public void inserirUsuario(Login l) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO usuario (id_usuario,usuario,senha)" + "values(null,?,?)";
            ps = (PreparedStatement) con.prepareStatement(query);
            ps.setString(1, l.getUsuario());
            ps.setString(2, l.getSenha());
            ps.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro inserirUsuario: " + e.getMessage());
        }
    }

    public void editarUsuario(Login l) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE usuario set senha = ? where usuario = ?";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ps.setString(1, l.getSenha());
            ps.setString(2, l.getUsuario());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro editarUsuario: " + e.getMessage());
        }
    }
}
