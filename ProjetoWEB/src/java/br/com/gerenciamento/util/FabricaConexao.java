/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciamento.util;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author 8xnec
 */
public class FabricaConexao {

    public static Connection conexao;
    private static final String URL_SERVIDOR = "jdbc:mysql://localhost/gerenciamento";
    private static final String URL_USUARIO = "root";
    private static final String URL_SENHA = "";

    public static Connection getConexao() throws ClassNotFoundException {
        if (conexao == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = (Connection) DriverManager.getConnection(URL_SERVIDOR, URL_USUARIO, URL_SENHA);

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return conexao;
    }

    public static void fecharConexao() throws SQLException {
        if (conexao != null) {

            conexao.close();
            conexao = null;
        }
    }
}
