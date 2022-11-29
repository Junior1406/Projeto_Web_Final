/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciamento.DAO;

import br.com.gerenciamento.entidade.Produto;
import br.com.gerenciamento.util.FabricaConexao;

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
public class ProdutoDAO {//classe onde ficam os comandos de SQL

    public void inserir(Produto ct_produto) throws ClassNotFoundException, SQLException {
        try {
            Connection conexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst;
            if (ct_produto .getCodigo() == null) {
                pst = conexao.prepareCall("INSERT INTO produto (codigo,nome_prod,valor,descricao,estoque,categoria)"
                        + " values(null,?,?,?,?,?)");
            } else {
                pst = conexao.prepareCall("UPDATE produto set nome_prod=?, valor=?, descricao=?, estoque=?, categoria=?"
                        + "where codigo=?");
                pst.setInt(6, ct_produto.getCodigo());
            }
            
            pst.setString(1, ct_produto.getNome_prod());
            pst.setString(2, ct_produto.getValor());
            pst.setString(3, ct_produto.getDescricao());
            pst.setLong(4, ct_produto.getEstoque());
            pst.setString(5, ct_produto.getCategoria());
            pst.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Produto> selecionarTudo() throws ClassNotFoundException, SQLException {
        try {
            Connection conexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst = conexao.prepareCall("SELECT * FROM produto");
            ResultSet rs = pst.executeQuery();
            List<Produto> lista = new ArrayList<>();
            while (rs.next()) {
                Produto prod = new Produto();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setNome_prod(rs.getString("nome_prod"));
                prod.setValor(rs.getString("valor"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setEstoque(rs.getLong("estoque"));
                prod.setCategoria(rs.getString("categoria"));
                lista.add(prod);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
  public void deletar(Produto ct_produto) throws  ClassNotFoundException,SQLException{
     try{
         Connection connexao=(Connection) FabricaConexao.getConexao();
         PreparedStatement pst;
         
         if(ct_produto.getCodigo()!=null){
         pst=connexao.prepareCall("DELETE FROM produto WHERE codigo=?;");
         pst.setInt(1,ct_produto.getCodigo());
         pst.execute();
     }
         FabricaConexao.fecharConexao();
     }catch(SQLException ex){
         Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,null,ex);
     }
     
   }
    
    
}

