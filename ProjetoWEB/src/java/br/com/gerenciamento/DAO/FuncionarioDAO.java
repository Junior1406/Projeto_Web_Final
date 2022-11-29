/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciamento.DAO;

import br.com.gerenciamento.entidade.Funcionario;
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
 * @author lnune
 */
public class FuncionarioDAO {

    public void inserir(Funcionario ct_funcionario) throws ClassNotFoundException, SQLException {
        try {
            Connection conexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst;
            if (ct_funcionario.getCodigo() == null) {
                pst = conexao.prepareCall("INSERT INTO funcionario (codigo,cargo,email,nome,telefone,status,cpf,senha)"
                        + " values(null,?,?,?,?,?,?,?)");
            } else {
                pst = conexao.prepareCall("UPDATE funcionario set cargo=?,email=?,nome=?,telefone=?,status=?,cpf=?,senha=?"
                        + "where codigo=?");
                pst.setInt(8, ct_funcionario.getCodigo());
            }

            pst.setString(1, ct_funcionario.getCargo());
            pst.setString(2, ct_funcionario.getEmail());
            pst.setString(3, ct_funcionario.getNome());
            pst.setString(4, ct_funcionario.getTelefone());
            pst.setString(5, ct_funcionario.getStatus());
            pst.setString(6, ct_funcionario.getCpf());
            pst.setString(7, MD5.stringHexa(MD5.gerarHash(ct_funcionario.getSenha(), "MD5")));
            pst.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Funcionario> selecionarTudo() throws ClassNotFoundException, SQLException {
        try {
            Connection conexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst = conexao.prepareCall("SELECT * FROM funcionario");
            ResultSet rs = pst.executeQuery();
            List<Funcionario> lista = new ArrayList<>();
            while (rs.next()) {
                Funcionario fun = new Funcionario();
                fun.setCodigo(rs.getInt("codigo"));
                fun.setCargo(rs.getString("cargo"));
                fun.setEmail(rs.getString("email"));
                fun.setNome(rs.getString("nome"));
                fun.setTelefone(rs.getString("telefone"));
                fun.setStatus(rs.getString("status"));
                fun.setCpf(rs.getString("cpf"));
                lista.add(fun);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean  login(Funcionario funLogin){
        try{
             Connection conexao = (Connection) FabricaConexao.getConexao();
             PreparedStatement pst = conexao.prepareCall("SELECT COUNT(*) AS valor FROM funcionario WHERE senha=? AND cpf=?");
             pst.setString(1, MD5.stringHexa(MD5.gerarHash(funLogin.getSenha(),"MD5")));
             pst.setString(2, funLogin.getCpf());
             ResultSet rs = pst.executeQuery();
             while(rs.next()){
                 return rs.getBoolean("valor");
             }
        }catch(Exception ex){
            System.out.println("ERRO"+ex.getMessage());
        }
        return false;
    }
    
    public void deletar(Funcionario ct_funcionario) throws ClassNotFoundException, SQLException {
        try {
            Connection connexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst;

            if (ct_funcionario.getCodigo() != null) {
                pst = connexao.prepareCall("DELETE FROM funcionario WHERE codigo=?;");
                pst.setInt(1, ct_funcionario.getCodigo());
                pst.execute();
            }
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
