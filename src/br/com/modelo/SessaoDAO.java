package br.com.modelo;

import br.com.entidade.Sessao;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SessaoDAO extends DAO {

    public boolean constarSessao(Sessao ss) {
        try {
            abrirBanco();
            String query = "SELECT * FROM sessao WHERE id_usuario=1";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ResultSet tr = ps.executeQuery();
            if (tr.next() == true) {
                return true;
            } else if (tr.next() == false) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro constarSessao: " + e.getMessage());
            return false;
        }
        return false;
    }

    public void validarSessao(Sessao ss) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE sessao set usuario = ?,senha=? WHERE id_usuario = 1";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ps.setString(1, ss.getUsuario());
            ps.setString(2, ss.getSenha());
            ps.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro validarSessao: " + e.getMessage());
        }
    }

    public void criarSessao(Sessao ss) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO sessao (id_usuario,usuario,senha) VALUES (1,?,?)";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ps.setString(1, ss.getUsuario());
            ps.setString(2, ss.getSenha());
            ps.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro criarSessao: " + e.getMessage());
        }
    }

    public void selecionarSessao(Sessao ss) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT id_usuario,usuario,senha FROM sessao";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ResultSet tr = ps.executeQuery();
            if (tr.next()) {
                ss.setId_usuario(tr.getInt("id_usuario"));
                ss.setUsuario(tr.getString("usuario"));
                ss.setSenha(tr.getString("senha"));
            } else {
                JOptionPane.showMessageDialog(null, "Usuario inválido!");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro selecionarSessao: " + e.getMessage());
        }
    }
}
