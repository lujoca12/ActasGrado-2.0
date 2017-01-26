/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.bean;


import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


/**
 *
 * @author Anonimous
 */
@Named(value = "menuAdministracionMb")
@Dependent
@ManagedBean
public class MenuAdministracionMb {
    
    
    public MenuAdministracionMb() {

    }

    public void GuardarC() {
        addMessage("Success", "Data saved");
    }

    public void ActualizarC() {
        addMessage("Success", "Data updated");
    }

    public void BorrarC() {
        addMessage("Success", "Data deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    

}
