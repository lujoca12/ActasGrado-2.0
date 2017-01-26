/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.test;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;
import org.primefaces.model.LazyDataModel;
import org.uteq.model.Estudiante;
import org.uteq.sesion.EstudianteFacade;

/**
 *
 * @author USUARIO
 */
@ViewScoped
@Named
public class LazyView implements Serializable{

    @EJB
    private EstudianteFacade estudianteFacade;  
    

    private LazyDataModel<Estudiante> lazyModel;
    private Estudiante selectedEstudiante;

    /**
     * Creates a new instance of LazyView
     */


    @PostConstruct
    public void init() {
        lazyModel = new LazyEstudianteModel(estudianteFacade.findAll());
    }

    public LazyDataModel<Estudiante> getLazyModel() {  
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Estudiante> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Estudiante getSelectedEstudiante() {
        return selectedEstudiante;
    }

    public void setSelectedEstudiante(Estudiante selectedEstudiante) {
        this.selectedEstudiante = selectedEstudiante;
    }

}
