/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.bean;

import com.opencsv.CSVReader;
import org.uteq.util.SessionUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.uteq.model.ActaEditada;
import org.uteq.model.ActaGrado;
import org.uteq.model.Estudiante;
import org.uteq.model.EstudianteExComplexivo;
import org.uteq.model.ExamenComplexivo;
import org.uteq.model.TipoTitulacion;
import org.uteq.model.Titulo;
import org.uteq.sesion.ActaEditadaFacade;
import org.uteq.sesion.ActaEditadaFacadeLocal;
import org.uteq.sesion.ActaGradoFacadeLocal;
import org.uteq.sesion.EstudianteExComplexivoFacadeLocal;
import org.uteq.sesion.EstudianteFacadeLocal;
import org.uteq.sesion.ExamenComplexivoFacadeLocal;
import org.uteq.sesion.TipoTitulacionFacadeLocal;
import org.uteq.sesion.TituloFacadeLocal;

/**
 *
 * @author Moises
 */
@ViewScoped
@Named(value = "cSVMb")
public class CSVMb implements Serializable {

    @EJB
    private ActaEditadaFacadeLocal aefl;
    @EJB
    private TituloFacadeLocal tfl;
    @EJB
    private TipoTitulacionFacadeLocal ttfl;
    @EJB
    private ExamenComplexivoFacadeLocal ecfl;
    @EJB
    private EstudianteExComplexivoFacadeLocal eecfl;
    @EJB
    private EstudianteFacadeLocal efl;
    @EJB
    private ActaGradoFacadeLocal agfl;
    private List<SelectItem> periodos;
    private List<String> log;
    private String examenComplexivo;

    @PostConstruct
    public void init() {
        log = new ArrayList<>();
        periodos = new ArrayList<>();
    }

    public void handleFileUpload(FileUploadEvent event) {
        if (event != null) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            try {
                InputStream is = event.getFile().getInputstream();
                String ruta = servletContext.getRealPath("/") + "csvs\\";
                Files.copy(is, new File(ruta, event.getFile().getFileName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                is.close();
                cargar(ruta + event.getFile().getFileName());
                FacesMessage message = new FacesMessage("El archivo", event.getFile().getFileName() + " fue subido correctamente.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (Exception e) {
                e.getMessage();
                FacesMessage message = new FacesMessage("El archivo no se subió", event.getFile().getFileName());
                FacesContext.getCurrentInstance().addMessage(null, message);
            } finally {

            }
        }
    }

    public void handleFileUpload1(FileUploadEvent event) {
        if (event != null) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            try {
                InputStream is = event.getFile().getInputstream();
                String ruta = servletContext.getRealPath("/") + "csvs\\";
                Files.copy(is, new File(ruta, event.getFile().getFileName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                is.close();
                borrar(ruta + event.getFile().getFileName());
                FacesMessage message = new FacesMessage("El archivo", event.getFile().getFileName() + " fue subido correctamente.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (Exception e) {
                e.getMessage();
                FacesMessage message = new FacesMessage("El archivo no se subió", event.getFile().getFileName());
                FacesContext.getCurrentInstance().addMessage(null, message);
            } finally {

            }
        }
    }

    public void borrar(String csvFile) {
        CSVReader reader;
        List<String> list;
        try {
            reader = new CSVReader(new FileReader(csvFile), ';');
            String[] line;
            while ((line = reader.readNext()) != null) {
                list = new ArrayList<>();
                Estudiante e =efl.find(line[0]);
                efl.remove(e);
            }
        }
        catch(Exception e){
            
        }
    }

    private void cargar(String csvFile) {
        CSVReader reader;
        List<String> list;
        try {
            reader = new CSVReader(new FileReader(csvFile), ';');
            String[] line;
            while ((line = reader.readNext()) != null) {
                list = new ArrayList<>();
                Estudiante e = new Estudiante();
                ActaGrado ag = new ActaGrado();
                for (String a : line) {
                    list.add(a);
                }

                e.setCedula(list.get(0));
                e.setApellidos(list.get(1));
                e.setNombres(list.get(2));
                e.setGenero(list.get(6));
                Titulo t;
                t = tfl.find(Integer.parseInt(list.get(3)));
                e.setIdTitulo(t);
                TipoTitulacion tt;
                if (list.get(9).equals("1")) {
                    tt = ttfl.find(1);
                } else if (list.get(9).equals("2")) {
                    tt = ttfl.find(2);
                } else {
                    tt = ttfl.find(3);
                }
                e.setIdTipoTitulacion(tt);
                ag.setActaBloqueada(Boolean.FALSE);
                ag.setActaGenerada(Boolean.FALSE);
                ag.setCedulaEstudiante(e);

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                cal.setTime(sdf.parse(list.get(7)));
                ag.setFechaIngresoActa(cal.getTime());
                sdf = new SimpleDateFormat("HH:mm", Locale.US);
                cal.setTime(sdf.parse(list.get(8)));
                ag.setHoraGenerarActa(cal.getTime());
                EstudianteExComplexivo ee = new EstudianteExComplexivo();
                ee.setCedulaEstudiante(e);
                ee.setIdExamenComplexivo(ecfl.find(Integer.parseInt(examenComplexivo)));

                try {
                    Estudiante ex = efl.find(e.getCedula());
                    ExamenComplexivo eexx = ecfl.find(Integer.parseInt(examenComplexivo));
                    if (ex != null) {
                        if (efl.findByExComplexivo(ex) != null) {
                            if (compararperiodo(ex.getEstudianteExComplexivoList())) {
                                if (ex.getIdTipoTitulacion().getIdTipoTitulacion() == 2) {
                                    ActaGrado ax = agfl.find(ex.getActaGradoList().get(0).getIdActaGrado());
                                    ax.setNotaGraciaGeneral(Float.parseFloat(list.get(4).replace(',', '.')) / 10);
                                    ax.setNotaGraciaProfesional(Float.parseFloat(list.get(5).replace(',', '.')) / 10);
                                    sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                    cal.setTime(sdf.parse(list.get(7)));
                                    ax.setFechaIngresoActa(cal.getTime());
                                    sdf = new SimpleDateFormat("HH:mm", Locale.US);
                                    cal.setTime(sdf.parse(list.get(8)));
                                    ax.setHoraGenerarActa(cal.getTime());
                                    ActaEditada ae = new ActaEditada();
                                    ae.setFecha(new Date());
                                    ae.setIdUsuario(SessionUtil.getUsuario());
                                    ae.setMotivo("Dará examen de gracia");
                                    ae.setNumActa(ax);
                                    ax.setPromedioEstudios(0F);
                                    agfl.edit(ax);
                                    aefl.create(ae);
                                    log.add("Estudiante = " + ex.getCedula() + " se ha registrado para examen de gracia en el periodo = " + eexx.getDenominacionExC());
                                } else {
                                    log.add("Estudiante = " + ex.getCedula() + " ya existe en el periodo = " + eexx.getDenominacionExC() + " con elmismo tipo de examen ");
                                }
                            } else {
                                ex.getEstudianteExComplexivoList();
                                ActaGrado ax = agfl.find(ex.getActaGradoList().get(0).getIdActaGrado());
                                ActaEditada ae = new ActaEditada();
                                ae.setFecha(new Date());
                                ae.setIdUsuario(SessionUtil.getUsuario());
                                ae.setMotivo("Se registra en nuevo periodo");
                                ae.setNumActa(ax);
                                if (list.get(9).equals("1")) {
                                    ax.setNotaComplexivoGeneral(Float.parseFloat(list.get(4).replace(',', '.')) / 10);
                                    ax.setNotaComplexivoProfesional(Float.parseFloat(list.get(5).replace(',', '.')) / 10);
                                    sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                    cal.setTime(sdf.parse(list.get(7)));
                                    ax.setFechaIngresoActa(cal.getTime());
                                    sdf = new SimpleDateFormat("HH:mm", Locale.US);
                                    cal.setTime(sdf.parse(list.get(8)));
                                }
                                ax.setPromedioEstudios(0F);
                                agfl.edit(ax);
                                eecfl.create(ee);
                                log.add("Estudiante = " + ex.getCedula() + " se ha registrado en el periodo = " + eexx.getDenominacionExC());
                            }
                        } else {
                            log.add("Estudiante = " + ex.getCedula() + " existe y no se encuentra registrado en ningún periodo");
                        }
                    } else {
                        if (list.get(9).equals("1")) {
                            ag.setNotaComplexivoGeneral(Float.parseFloat(list.get(4).replace(',', '.'))/10);
                            ag.setNotaComplexivoProfesional(Float.parseFloat(list.get(5).replace(',', '.'))/10);
                        } else {
                            ag.setNotaGraciaGeneral(Float.parseFloat(list.get(4).replace(',', '.'))/10);
                            ag.setNotaGraciaProfesional(Float.parseFloat(list.get(5).replace(',', '.'))/10);
                        }
                        ag.setPromedioEstudios(0F);
                        efl.create(e);
                        agfl.create(ag);
                        eecfl.create(ee);
                        log.add("Estudiante nuevo = " + e.getCedula() + " se ha registrado en el periodo = " + ee.getIdExamenComplexivo().getDenominacionExC());
                    }

                } catch (Exception ex) {
                    log.add(ex.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.add(e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(CSVMb.class.getName()).log(Level.SEVERE, null, ex);
            log.add(ex.getMessage());
        } finally {
            if (!log.isEmpty()) {
                BufferedWriter bw = null;
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String sFichero = SessionUtil.getRuta() + "csvs/log-" + format.format(Calendar.getInstance().getTime()) + ".txt";
                    File fichero = new File(sFichero);
                    if (fichero.exists()) {
                        fichero.delete();
                    }
                    bw = new BufferedWriter(new FileWriter(fichero, true));
                    for (String e : log) {
                        bw.write(e);
                        bw.newLine();
                    }
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(CSVMb.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        bw.close();
                    } catch (IOException ex) {
                        Logger.getLogger(CSVMb.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public boolean compararperiodo(List<EstudianteExComplexivo> ex) {
        boolean ban = false;
        ExamenComplexivo eexx = ecfl.find(Integer.parseInt(examenComplexivo));
        for (EstudianteExComplexivo eex : ex) {
            if (eex.getIdExamenComplexivo().getDenominacionExC().equals(eexx.getDenominacionExC())) {
                ban = true;
            }
        }
        return ban;
    }

    public List<SelectItem> getPeriodos() {
        List<ExamenComplexivo> list = ecfl.findAll();
        for (ExamenComplexivo ec : list) {
            periodos.add(new SelectItem(ec.getIdExamenComplexivo().toString(), ec.getDenominacionExC()));
        }
        return periodos;
    }

    public void setPeriodos(List<SelectItem> periodos) {
        this.periodos = periodos;
    }

    public String getExamenComplexivo() {
        return examenComplexivo;
    }

    public void setExamenComplexivo(String examenComplexivo) {
        this.examenComplexivo = examenComplexivo;
    }
}
