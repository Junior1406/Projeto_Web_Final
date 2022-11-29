/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciamento.bean;

import br.com.gerenciamento.DAO.ProdutoDAO;
import br.com.gerenciamento.entidade.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lnune
 */
@ManagedBean
@SessionScoped
public class ProdutoBean {
    
    private Produto ct_produto = new Produto();
    private ProdutoDAO prod_dao = new ProdutoDAO();
    private Produto selectpro;
  

      
    private List<Produto> listaprod = new ArrayList<>();
    public void cadastrar() throws ClassNotFoundException, SQLException {
     
        new ProdutoDAO().inserir(ct_produto);
        ct_produto = new Produto();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Salvo com Sucesso"));
        
    }

    public Produto getSelectpro() {
        return selectpro;
    }

    public void setSelectpro(Produto selectpro) {
        this.selectpro = selectpro;
    }

    
    public void listar() throws ClassNotFoundException, SQLException {
        listaprod = prod_dao.selecionarTudo();
    }
    
    
    public void alterar(Produto p){
        ct_produto = p;
    }
 
    
    public void deletar(Produto ct_produto) throws ClassNotFoundException,SQLException{
        new ProdutoDAO().deletar(ct_produto);
        listar();
    }

    public Produto getCt_produto() {
        return ct_produto;
    }

    public void setCt_produto(Produto ct_produto) {
        this.ct_produto = ct_produto;
    }

    

    public ProdutoDAO getProd_dao() {
        return prod_dao;
    }

    public void setProd_dao(ProdutoDAO prod_dao) {
        this.prod_dao = prod_dao;
    }

    public List<Produto> getListaprod() {
        return listaprod;
    }

    public void setListaprod(List<Produto> listaprod) {
        this.listaprod = listaprod;
    }

    private String message;
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
     
    
   
}


