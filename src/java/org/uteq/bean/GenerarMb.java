/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.bean;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Moises
 */
@RequestScoped
@Named
public class GenerarMb implements Serializable{

    /**
     * Creates a new instance of GenerarMb
     */
    public GenerarMb() {
    }
    
    public void generarPDFS() {
//        PDF pdf = new PDF();
//        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//        pdf.setRuta(servletContext.getRealPath("/"));
//        pdf.actas();
        
    }
}
