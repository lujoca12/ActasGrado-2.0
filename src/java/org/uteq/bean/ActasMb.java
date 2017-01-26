/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.uteq.model.ActaEditada;
import org.uteq.model.ActaGrado;
import org.uteq.sesion.ActaEditadaFacadeLocal;
import org.uteq.sesion.ActaGradoFacadeLocal;
import org.uteq.util.SessionUtil;

/**
 *
 * @author USUARIO
 */
@ViewScoped
@Named
public class ActasMb implements Serializable {

    @EJB
    private ActaEditadaFacadeLocal aefl;

    @EJB
    private ActaGradoFacadeLocal agfl;

    private List<ActaGrado> list;
    private ActaGrado seActa;
    private List<ActaGrado> filterList;
    private ActaEditada actaEditada;

    @PostConstruct
    public void init() {
        actaEditada = new ActaEditada();
    }

    public void guardarMotivo() {

        Calendar c1 = Calendar.getInstance();
        Float f = actaEditada.getNumActa().getPromedioEstudios();
        try {
            actaEditada.setFecha(c1.getTime());
            actaEditada.setIdUsuario(SessionUtil.getUsuario());
            aefl.create(actaEditada);
            ActaGrado editada = actaEditada.getNumActa();
            editada.setActaGenerada(Boolean.FALSE);
            editada.setPromedioEstudios(f);
            agfl.edit(editada);
            actaEditada = new ActaEditada();
        } catch (Exception ex) {
            Logger.getLogger(ActasMb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestContext.getCurrentInstance().update("form1:tabla1");
        }
    }

    public List<ActaGrado> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<ActaGrado> filterList) {
        this.filterList = filterList;
    }

    public List<ActaGrado> getList() {
        if (SessionUtil.getUsuario().getIdTipoUsuario().getIdTipoUsuario() == 1) {
            list = agfl.findTrue();
        } else {
            list = agfl.findTrueFacultadd();
        }
        return list;
    }

    public ActaGrado getSeActa() {
        return seActa;
    }

    public void setSeActa(ActaGrado seActa) {
        this.seActa = seActa;
    }

    public void setList(List<ActaGrado> list) {
        this.list = list;
    }

    public ActaEditada getActaEditada() {
        return actaEditada;
    }

    public void setActaEditada(ActaEditada actaEditada) {
        this.actaEditada = actaEditada;
    }

    public ActasMb() {
    }

}
