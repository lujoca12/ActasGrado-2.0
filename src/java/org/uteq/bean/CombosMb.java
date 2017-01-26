/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import org.uteq.model.Carrera;
import org.uteq.model.Facultad;
import org.uteq.model.TipoTitulacion;
import org.uteq.model.Titulo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.uteq.model.Estudiante;
import org.uteq.model.ExamenComplexivo;
import org.uteq.sesion.EstudianteFacadeLocal;
import org.uteq.sesion.ExamenComplexivoFacadeLocal;
import org.uteq.sesion.FacultadFacadeLocal;
import org.uteq.sesion.TipoTitulacionFacadeLocal;
import org.uteq.util.Excel;
import org.uteq.util.SessionUtil;

/**
 *
 * @author Moises
 */
@ViewScoped
@Named
public class CombosMb implements Serializable {

    @EJB
    private EstudianteFacadeLocal efl;
    @EJB
    private ExamenComplexivoFacadeLocal ecfl;
    @EJB
    private TipoTitulacionFacadeLocal ttfl;
    @EJB
    private FacultadFacadeLocal ffl;

    private List<SelectItem> categories;
    private List<SelectItem> tipotitulacion;
    private List<TipoTitulacion> tipolist;
    private List<Facultad> facultades;

    private String condicion;

    private String seleccionC;
    private String seleccionF;
    private String seleccionO;
    private String seleccionT;
    private String seleccionP;
    private StreamedContent file;

    private List<ExamenComplexivo> ecs;
    private List<SelectItem> periodo;
    private List<SelectItem> periodoI;

    private List<Carrera> lcarrera;
    private List<SelectItem> carrera;

    private List<Facultad> fs;
    private List<SelectItem> facultad;

    private List<SelectItem> opcion;

    private List<TipoTitulacion> ltTipoTitulacions;
    private List<SelectItem> tipoT;

    private String selected;

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public String getSeleccionC() {
        return seleccionC;
    }

    public void setSeleccionC(String seleccionC) {
        this.seleccionC = seleccionC;
    }

    public String getSeleccionF() {
        return seleccionF;
    }

    public void setSeleccionF(String seleccionF) {
        this.seleccionF = seleccionF;
    }

    public String getSeleccionO() {
        return seleccionO;
    }

    public void setSeleccionO(String seleccionO) {
        this.seleccionO = seleccionO;
    }

    public String getSeleccionT() {
        return seleccionT;
    }

    public void setSeleccionT(String seleccionT) {
        this.seleccionT = seleccionT;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getSeleccionP() {
        return seleccionP;
    }

    public void setSeleccionP(String seleccionP) {
        this.seleccionP = seleccionP;
    }

    public List<SelectItem> getTipotitulacion() {
        tipotitulacion = new ArrayList<>();
        tipolist = ttfl.findAll();
        for (TipoTitulacion t : tipolist) {
            tipotitulacion.add(new SelectItem(t.getIdTipoTitulacion(), t.getTipoTitulacion()));
        }
        return tipotitulacion;
    }

    public void onFacultadChange() {
        if (seleccionF != null && !seleccionF.equals("")) {
            getCarrera();
        } else {
            carrera = new ArrayList<>();
        }
    }

    public void onEstadoChange() {
        if (seleccionO != null && !seleccionO.equals("")) {
            getTipoT();
        } else {
            carrera = new ArrayList<>();
        }
    }

    public void onTipoChange() {
        if (seleccionT != null && !seleccionT.equals("")) {
            getPeriodo();
        } else {
            periodo = new ArrayList<>();
        }
    }

    public void setTipotitulacion(List<SelectItem> tipotitulacion) {
        this.tipotitulacion = tipotitulacion;
    }

    public List<SelectItem> getCategories() {
        categories = new ArrayList<>();
        if (SessionUtil.getUsuario().getIdTipoUsuario().getIdTipoUsuario() == 1) {
            facultades = ffl.findAll();
        } else {
            facultades = new ArrayList<>();
            facultades.add(ffl.find(SessionUtil.getUsuario().getIdFacultad().getIdFacultad()));
        }

        int cont1 = 0, cont2 = 0;

        for (Facultad f : facultades) {
            SelectItemGroup group1 = new SelectItemGroup(f.getFacultad());
            SelectItem[] carreras = new SelectItem[f.getCarreraList().size()];
            cont1 = 0;
            for (Carrera c : f.getCarreraList()) {
                SelectItemGroup carrera = new SelectItemGroup(c.getCarrera());
                SelectItem[] titulos = new SelectItem[c.getTituloList().size()];
                cont2 = 0;
                for (Titulo t : c.getTituloList()) {
                    SelectItem titulo = new SelectItem(t.getIdtitulo(), t.getTitulo());
                    titulos[cont2] = new SelectItem(titulo.getValue(), titulo.getLabel());
                    cont2++;
                }
                carrera.setSelectItems(titulos);
                carreras[cont1] = carrera;
                cont1++;
            }
            group1.setSelectItems(carreras);
            categories.add(group1);
        }
        return categories;
    }

    public void setCategories(List<SelectItem> categories) {
        this.categories = categories;
    }

    public List<SelectItem> getPeriodo() {
        periodo = new ArrayList<>();
        if (seleccionT != null && !seleccionT.equals("")) {
            ecs = ecfl.findAll();
            ecs.stream().forEach((e) -> {
                periodo.add(new SelectItem(e.getIdExamenComplexivo(), e.getDenominacionExC()));
            });
        }
        return periodo;
    }

    public List<SelectItem> getPeriodoI() {
        periodoI = new ArrayList<>();
        ecs = ecfl.findAll();
        ecs.stream().forEach((e) -> {
            periodoI.add(new SelectItem(e.getIdExamenComplexivo(), e.getDenominacionExC()));
        });
        return periodoI;
    }

    public void setPeriodoI(List<SelectItem> periodoI) {
        this.periodoI = periodoI;
    }

    public void generarInforme() {
        setcondicion(seleccionT, seleccionO);
        try {
            List<Estudiante> lista = efl.findByPeriodo(Integer.parseInt(seleccionP), Integer.parseInt(seleccionC), Integer.parseInt(seleccionT), condicion);
            Excel excel = new Excel();
            String ruta = excel.generarExcel(lista, seleccionT);
            InputStream stream;
            try {
                stream = new FileInputStream(ruta);
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                String contentType = externalContext.getMimeType(ruta);
                setFile(new DefaultStreamedContent(stream, contentType, "reporteExcel.xls"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CombosMb.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen Estudiantes"));
        }

    }

    public void setcondicion(String tipo, String opcion) {
        if ((tipo.equals("1") && opcion.equals("1"))) {
            condicion = "";
        }
        if (tipo.equals("1") && opcion.equals("2")) {
            condicion = "and (a.notaComplexivoGeneral + a.notaComplexivoProfesional) >= 7";
        }
        if (tipo.equals("1") && opcion.equals("3")) {
            condicion = "and (a.notaComplexivoGeneral + a.notaComplexivoProfesional) < 7";
        }
        if (tipo.equals("2") && opcion.equals("2")) {
            condicion = "and (a.notaGraciaGeneral + a.notaGraciaProfesional) >= 7";
        }
        if (tipo.equals("2") && opcion.equals("3")) {
            condicion = "and (a.notaGraciaGeneral + a.notaGraciaProfesional) < 7";
        }
        if ((tipo.equals("2") && opcion.equals("1"))) {
            condicion = "and e.cedulaEstudiante.idTipoTitulacion.idTipoTitulacion = 2";
        }
    }

    public void setPeriodo(List<SelectItem> periodo) {
        this.periodo = periodo;
    }

    public List<SelectItem> getCarrera() {
        carrera = new ArrayList<>();
        if (seleccionF != null && !seleccionF.equals("")) {
            for (Carrera c : fs.get(Integer.parseInt(seleccionF) - 1).getCarreraList()) {
                carrera.add(new SelectItem(c.getIdCarrera(), c.getCarrera()));
            }
        }
        return carrera;
    }

    public void setCarrera(List<SelectItem> carrera) {
        this.carrera = carrera;
    }

    public List<SelectItem> getFacultad() {
        facultad = new ArrayList<>();
        fs = ffl.findAll();
        for (Facultad f : fs) {
            facultad.add(new SelectItem(f.getIdFacultad(), f.getFacultad()));
        }
        return facultad;
    }

    public void setFacultad(List<SelectItem> facultad) {
        this.facultad = facultad;
    }

    public List<SelectItem> getTipoT() {
        tipoT = new ArrayList<>();
        if (seleccionO != null && !seleccionO.equals("")) {
            tipolist = ttfl.findAll();
            tipolist.stream().forEach((t) -> {
                tipoT.add(new SelectItem(t.getIdTipoTitulacion(), t.getTipoTitulacion()));
            });
        }
        return tipoT;
    }

    public void setTipoT(List<SelectItem> tipoT) {
        this.tipoT = tipoT;
    }

    public List<SelectItem> getOpcion() {
        opcion = new ArrayList<>();
        opcion.add(new SelectItem(1, "Todos"));
        opcion.add(new SelectItem(2, "Aprobado"));
        opcion.add(new SelectItem(3, "No Aprobado"));
        return opcion;
    }

    public void setOpcion(List<SelectItem> opcion) {
        this.opcion = opcion;
    }

    public CombosMb() {
        seleccionF = null;
        seleccionO = null;
        seleccionP = null;
        seleccionC = null;
        seleccionT = null;
    }

}
