/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciamento.DAO;

import br.com.gerenciamento.entidade.Administrador;
import br.com.gerenciamento.util.FabricaConexao;
import br.com.gerenciamento.util.MD5;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 8xnec
 */
public class AdministradorDAO {

    public void inserir(Administrador administrador) throws ClassNotFoundException, SQLException {

        try {
            Connection conexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst;
            if (administrador.getCodigo() == null) {
                pst = conexao.prepareCall("INSERT INTO administrador (login, senha)"
                        + "VALUES(null,?,?)");
            } else {
                pst = conexao.prepareCall("UPDATE administrador SET login=?,senha=? WHERE codigo=?");
                pst.setInt(3, administrador.getCodigo());
            }
            pst.setString(1, administrador.getLogin());
            pst.setString(2, MD5.stringHexa(MD5.gerarHash(administrador.getSenha(), "MD5")));
            pst.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean login(Administrador admin) throws Exception {
        try {
            Connection conexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst = conexao.prepareCall("SELECT COUNT(*) AS valor FROM administrador WHERE senha = ? AND login = ?");
            pst.setString(1, MD5.stringHexa(MD5.gerarHash(admin.getSenha(), "MD5")));
            pst.setString(2, admin.getLogin());
            ResultSet rs = pst.executeQuery();
            //Retornar esses dados para criar Session
            while (rs.next()) {
                return rs.getBoolean("valor");
            }
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return false;
    }

    public List<Administrador> selecionarTudo() throws ClassNotFoundException, SQLException {
        try {
            Connection conexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst = conexao.prepareCall("SELECT * FROM administrador");
            ResultSet rs = pst.executeQuery();
            List<Administrador> lista = new ArrayList<>();
            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setCodigo(rs.getInt("codigo"));
                admin.setLogin(rs.getString("login"));
                admin.setSenha(rs.getString("senha"));

                lista.add(admin);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deletar(Administrador adm) {

        try {
            Connection conexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst;

            if (adm.getCodigo() != null) {
                pst = conexao.prepareCall("Delete FROM administrador WHERE Codigo=?");
                pst.setInt(1, adm.getCodigo());
                pst.execute();
            }
            FabricaConexao.fecharConexao();
        } catch (Exception ex) {
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
