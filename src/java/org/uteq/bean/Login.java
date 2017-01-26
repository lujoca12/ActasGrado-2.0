/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.uteq.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.uteq.model.Usuario;
import org.uteq.sesion.UsuarioFacadeLocal;

/**
 *
 * @author jacob
 */
@SessionScoped
@Named
public class Login implements Serializable {

    @EJB
    private UsuarioFacadeLocal ufl; 
    
    private String pwd;
    private String msg;
    private String user;
    private boolean valido;
    private Usuario usuario;

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    //validate login
    public void validateUsernamePassword() {
        RequestContext context = RequestContext.getCurrentInstance();
        usuario = ufl.findUserPwd(user, pwd);
        FacesMessage facesMessage = null;
        String pagina = "../login/";
        if (usuario != null) {
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("usuario", usuario);
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Bienvenido", usuario.getUsuario());
            pagina = "../index/";
            valido=true;
        } else {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Usuario y Contraseña incorrectos",
                    "Por favor ingrese un usuario y contraseña correcta");
            pagina = "../login/";
            valido=false;
        }
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
        context.addCallbackParam("isValido", valido);
        context.addCallbackParam("view", pagina);
    }

    //logout event, invalidate session
    public void logout() {
        try {
            SessionUtil.getInvalidarSesion();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login/");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
