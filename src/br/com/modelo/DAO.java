package br.com.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public void abrirBanco() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mediacrud";
            String user = "root";
            String senha = "";
            
            con = (Connection) DriverManager.getConnection(url, user, senha);
            System.out.println("Conectado.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao encontrada, adicione o driver nas bibliotecas.");
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }

    public void fecharBanco() throws Exception {
        if (ps != null) { 
            ps.close();
            System.out.println("Execuçao da Query fechada\n");
        }
    }
}
