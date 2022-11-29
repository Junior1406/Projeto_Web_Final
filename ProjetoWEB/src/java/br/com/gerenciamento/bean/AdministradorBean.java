/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciamento.bean;

import br.com.gerenciamento.DAO.AdministradorDAO;
import br.com.gerenciamento.entidade.Administrador;
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
 * @author 8xnec
 */
@ManagedBean
@SessionScoped
public class AdministradorBean {

    private Administrador admin = new Administrador();
    private AdministradorDAO adm_dao = new AdministradorDAO();
    private List<Administrador> listadmin = new ArrayList<>();

    public void cadastro() throws ClassNotFoundException, SQLException {

        new AdministradorDAO().inserir(admin);
        admin = new Administrador();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso"));
    }

    public String entrar() throws Exception {
        boolean acesso = false;
        acesso = new AdministradorDAO().login(admin);
        if (acesso) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", admin);
            return "/pages/adm/bvadm.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario ou senha Incorretos",
                            "Tente Novamente!!"));
            return null; //"/loginadm.xhtml?faces-redirect=true";
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/index.xhtml?faces-redirect=true";
    }

    
    
    public void listar() throws ClassNotFoundException, SQLException {
        listadmin = adm_dao.selecionarTudo();
    }

    public void alterar(Administrador a) {
        admin = a;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public AdministradorDAO getAdm_dao() {
        return adm_dao;
    }

    public void setAdm_dao(AdministradorDAO adm_dao) {
        this.adm_dao = adm_dao;
    }

    public List<Administrador> getListadmin() {
        return listadmin;
    }

    public void setListadmin(List<Administrador> listadmin) {
        this.listadmin = listadmin;
    }

}
