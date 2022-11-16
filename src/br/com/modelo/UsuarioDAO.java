package br.com.modelo;

import br.com.entidade.Login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            System.out.println("Erro " + e.getMessage());
            return null;
        }
    }

    public void inserir(Login l) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO usuario (id_usuario,usuario,senha)" + "values(null,?,?)";
            ps = (PreparedStatement) con.prepareStatement(query);
            ps.setString(1, l.getUsuario());
            ps.setString(2, l.getSenha());

            ps.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    
    public void PesquisarTudo(Login l) throws Exception {
        try {
            abrirBanco();
            String query = "select id_usuario, usuario, senha FROM usuario";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ResultSet tr = ps.executeQuery();
            if (tr.next()) {
                l.setId_usuario(tr.getInt("id_usuario"));
                l.setUsuario(tr.getString("usuario"));
                l.setSenha(tr.getString("senha"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    /**
    public ArrayList<Login> PesquisarTudo() throws Exception {
        ArrayList<Login> ls = new ArrayList<Login>();
        try {
            abrirBanco();
            //Login l;
            //l = new Login();
            String query = "select id_usuario, usuario, senha, email,telefone, saldo, numero_conta, data_criacao FROM usuario";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ResultSet tr = ps.executeQuery();
            Login l;
            while (tr.next()) {
                l = new Login();
                l.setId_usuario(tr.getInt("id_usuario"));
                l.setUsuario(tr.getString("usuario"));
                l.setEmail(tr.getString("email"));
                l.setTelefone(tr.getInt("telefone"));
                l.setSaldo(tr.getDouble("saldo"));
                l.setNumero_conta(tr.getInt("numero_conta"));
                l.setData_criacao(tr.getString("data_criacao"));
                ls.add(l);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return ls;
    }
    */
    public void PesquisarApenas(Login l) throws Exception {
        try {
            abrirBanco();
            String query = "select id_usuario, usuario, senha FROM usuario WHERE usuario=?";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ps.setString(1, l.getUsuario());
            ResultSet tr = ps.executeQuery();
            if (tr.next()) {
                l.setId_usuario(tr.getInt("id_usuario"));
                l.setUsuario(tr.getString("usuario"));
                l.setSenha(tr.getString("email"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }/**
    
    public ArrayList<Login> PesquisarApenas() throws Exception {
        ArrayList<Login> ls = new ArrayList<Login>();
        try {
            abrirBanco();
            Login l;
            l = new Login();
            String query = "select usuario, senha, email,telefone, saldo, numero_conta, data_criacao FROM usuario where id_usuario=(?)";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ps.setInt(1, l.getId_usuario());
            ResultSet tr = ps.executeQuery();
            
            while (tr.next()) {
                //l.setId_usuario(tr.getInt("id_usuario"));
                l.setUsuario(tr.getString("usuario"));
                l.setEmail(tr.getString("email"));
                l.setTelefone(tr.getInt("telefone"));
                l.setSaldo(tr.getDouble("saldo"));
                l.setNumero_conta(tr.getInt("numero_conta"));
                l.setData_criacao(tr.getString("data_criacao"));
                ls.add(l);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return ls;
    }
    */
    public void editarUsuario(Login l) throws Exception {
        abrirBanco();
        
        String query = "UPDATE usuario set senha = ? where usuario = ?";
        ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
        ps.setString(1, l.getSenha());
        ps.setString(2, l.getUsuario());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Usuario Alterado com sucesso!");
        fecharBanco();
    }
}
