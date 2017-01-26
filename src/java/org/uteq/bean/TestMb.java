/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.bean;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.uteq.model.Titulo;
import org.uteq.sesion.TituloFacade;

/**
 *
 * @author Moises
 */
@Named(value = "testMb")
@ViewScoped
public class TestMb implements Serializable {

    /**
     * Creates a new instance of TestMb
     */

    private StreamedContent file;

    public TestMb() {
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/optimus.jpg");
        file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
    }

    public StreamedContent getFile() {
        
        return file;
    }

}
