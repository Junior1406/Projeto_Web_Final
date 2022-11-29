/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciamento.bean;

import br.com.gerenciamento.DAO.FuncionarioDAO;
import br.com.gerenciamento.entidade.Funcionario;
import br.com.gerenciamento.util.SessionUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lnune
 */
@ManagedBean
@SessionScoped
public class FuncionarioBean {

    private Funcionario ct_funcionario = new Funcionario();
    private FuncionarioDAO fun_dao = new FuncionarioDAO();
    private String message;
    private List<Funcionario> listafun = new ArrayList<>();

    public void cadastrar() throws ClassNotFoundException, SQLException {

        new FuncionarioDAO().inserir(ct_funcionario);
        ct_funcionario = new Funcionario();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso"));
    }

    public String entrar() {
        boolean acesso = false;
        acesso = new FuncionarioDAO().login(ct_funcionario);
        if (acesso) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", ct_funcionario);
            limpar();
            return "/pages/funcionario/bvfun.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario ou senha Incorretos",
                            "Tente Novamente!!"));
            return null; //"/login.xhtml?faces-redirect=true";

        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/index.xhtml?faces-redirect=true";
    }

    public void limpar() {

        this.ct_funcionario.setCpf("");
        this.ct_funcionario.setSenha("");

    }

    public void listar() throws ClassNotFoundException, SQLException {
        listafun = fun_dao.selecionarTudo();
    }

    public void alterar(Funcionario f) {
        ct_funcionario = f;
    }

    public void deletar(Funcionario ct_funcionario) throws ClassNotFoundException, SQLException {
        new FuncionarioDAO().deletar(ct_funcionario);
        listar();
    }

    public Funcionario getCt_funcionario() {
        return ct_funcionario;
    }

    public void setCt_funcionario(Funcionario ct_funcionario) {
        this.ct_funcionario = ct_funcionario;
    }

    public FuncionarioDAO getFun_dao() {
        return fun_dao;
    }

    public void setFun_dao(FuncionarioDAO fun_dao) {
        this.fun_dao = fun_dao;
    }

    public List<Funcionario> getListafun() {
        return listafun;
    }

    public void setListafun(List<Funcionario> listafun) {
        this.listafun = listafun;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
