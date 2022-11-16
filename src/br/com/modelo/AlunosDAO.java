package br.com.modelo;

import br.com.entidade.Alunos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class AlunosDAO extends DAO{
    public void inserir(Alunos al) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO alunos (id,nome,nota1,nota2,media)" + "values(null,?,?,?,?)";
            ps = (PreparedStatement) con.prepareStatement(query);
            //ps.setInt(1, al.getId());
            ps.setString(1, al.getNome());
            ps.setDouble(2, al.getNota1());
            ps.setDouble(3, al.getNota2());
            ps.setDouble(4, al.getMedia());

            ps.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
        public ArrayList<Alunos> PesquisarTudo() throws Exception {
        ArrayList<Alunos> als = new ArrayList<Alunos>();
        try {
            abrirBanco();
            String query = "select id, nome, nota1, nota2, media FROM alunos";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ResultSet tr = ps.executeQuery();
            Alunos al;
            while (tr.next()) {
                al = new Alunos();
                al.setId(tr.getInt("id"));
                al.setNome(tr.getString("nome"));
                al.setNota1(tr.getDouble("nota1"));
                al.setNota2(tr.getDouble("nota2"));
                al.setMedia(tr.getDouble("media"));
                als.add(al);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return als;
    }
    public void deletar(Alunos al) throws Exception {
        try {
        abrirBanco();
        String query = "DELETE FROM alunos WHERE nome=?";
        ps=(PreparedStatement) con.prepareStatement(query);
        ps.setString(1, al.getNome());
    
        ps.execute();
        fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }
  }
    public void editarAluno(Alunos al) throws Exception {
        abrirBanco();
        //JOptionPane.showMessageDialog(null, a.getNome()+ a.getEmail() + a.getIdade());
        String query = "UPDATE alunos set nota1 = ?, nota2 = ?, media = ? where nome = ?";
        ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
        ps.setDouble(1, al.getNota1());
        ps.setDouble(2, al.getNota2());
        ps.setDouble(3, al.getMedia());
        ps.setString(4, al.getNome());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Aluno Alterado com sucesso!");
        fecharBanco();
    }
    public void PesquisarRegistro(Alunos al) throws Exception {
        try {
            abrirBanco();
            String query = "select nome, nota1, nota2, media FROM alunos where nome=?";
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            ps.setString(1, al.getNome());
            ResultSet tr = ps.executeQuery();
            if (tr.next()) {
                al.setNome(tr.getString("nome"));
                al.setNota1(tr.getDouble("nota1"));
                al.setNota2(tr.getDouble("nota2"));
                al.setMedia(tr.getDouble("media"));
            } else {
                JOptionPane.showMessageDialog(null, "Aluno inválido!");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
