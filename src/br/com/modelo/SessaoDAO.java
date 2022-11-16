package br.com.modelo;

import br.com.entidade.Sessao;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class SessaoDAO extends DAO {

    public void validarSessao(Sessao ss) throws Exception {
        abrirBanco();
        //JOptionPane.showMessageDialog(null, a.getNome()+ a.getEmail() + a.getIdade());
        String query = "UPDATE sessao set usuario = ?,senha=? where id_usuario = 1";
        ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
        ps.setString(1, ss.getUsuario());
        ps.setString(2, ss.getSenha());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Token gerado com sucesso!");
        fecharBanco();
    }

    public void criarSessao(Sessao ss) throws Exception {
        abrirBanco();
        String query = "INSERT INTO sessao (id_usuario,usuario,senha)" + "values(null,?,?)";
        ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
        ps.setString(1, ss.getUsuario());
        ps.setString(2, ss.getSenha());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Primeiro token gerado com sucesso!");
        fecharBanco();
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
                JOptionPane.showMessageDialog(null, "Aluno inválido!");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
