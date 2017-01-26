/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "ActaGrado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActaGrado.findAll", query = "SELECT a FROM ActaGrado a"),
    @NamedQuery(name = "ActaGrado.findByIdActaGrado", query = "SELECT a FROM ActaGrado a WHERE a.idActaGrado = :idActaGrado"),
    @NamedQuery(name = "ActaGrado.findByNumeroActaGrado", query = "SELECT a FROM ActaGrado a WHERE a.numeroActaGrado = :numeroActaGrado"),
    @NamedQuery(name = "ActaGrado.findByNotaComplexivoGeneral", query = "SELECT a FROM ActaGrado a WHERE a.notaComplexivoGeneral = :notaComplexivoGeneral"),
    @NamedQuery(name = "ActaGrado.findByNotaComplexivoProfesional", query = "SELECT a FROM ActaGrado a WHERE a.notaComplexivoProfesional = :notaComplexivoProfesional"),
    @NamedQuery(name = "ActaGrado.findByNotaGraciaGeneral", query = "SELECT a FROM ActaGrado a WHERE a.notaGraciaGeneral = :notaGraciaGeneral"),
    @NamedQuery(name = "ActaGrado.findByNotaGraciaProfesional", query = "SELECT a FROM ActaGrado a WHERE a.notaGraciaProfesional = :notaGraciaProfesional"),
    @NamedQuery(name = "ActaGrado.findByNotaTrabajoInvestigaci\u00f3n", query = "SELECT a FROM ActaGrado a WHERE a.notaTrabajoInvestigaci\u00f3n = :notaTrabajoInvestigaci\u00f3n"),
    @NamedQuery(name = "ActaGrado.findByPromedioEstudios", query = "SELECT a FROM ActaGrado a WHERE a.promedioEstudios = :promedioEstudios"),
    @NamedQuery(name = "ActaGrado.findByActaGenerada", query = "SELECT a FROM ActaGrado a WHERE a.actaGenerada = :actaGenerada"),
    @NamedQuery(name = "ActaGrado.findByActaBloqueada", query = "SELECT a FROM ActaGrado a WHERE a.actaBloqueada = :actaBloqueada"),
    @NamedQuery(name = "ActaGrado.findByFechaIngresoActa", query = "SELECT a FROM ActaGrado a WHERE a.fechaIngresoActa = :fechaIngresoActa"),
    @NamedQuery(name = "ActaGrado.findByEvidencia", query = "SELECT a FROM ActaGrado a WHERE a.evidencia = :evidencia"),
    @NamedQuery(name = "ActaGrado.findByIdUsuario", query = "SELECT a FROM ActaGrado a WHERE a.idUsuario = :idUsuario"),
    @NamedQuery(name = "ActaGrado.findByNombreSecretario", query = "SELECT a FROM ActaGrado a WHERE a.nombreSecretario = :nombreSecretario"),
    @NamedQuery(name = "ActaGrado.findByNombreAutoridad", query = "SELECT a FROM ActaGrado a WHERE a.nombreAutoridad = :nombreAutoridad"),
    @NamedQuery(name = "ActaGrado.findByPeriodo", query = "SELECT a FROM ActaGrado a WHERE a.periodo = :periodo"),
    @NamedQuery(name = "ActaGrado.findByFechaGenerarActa", query = "SELECT a FROM ActaGrado a WHERE a.fechaGenerarActa = :fechaGenerarActa"),
    @NamedQuery(name = "ActaGrado.findByHoraGenerarActa", query = "SELECT a FROM ActaGrado a WHERE a.horaGenerarActa = :horaGenerarActa"),
    @NamedQuery(name = "ActaGrado.findByActaGeneradaTrue", query = "SELECT a FROM ActaGrado a WHERE a.actaGenerada = true"),
    @NamedQuery(name = "ActaGrado.findByActaGeneradaFacultad", query = "SELECT a FROM ActaGrado a WHERE a.actaGenerada = :actaGenerada and a.cedulaEstudiante.idTitulo.idCarrera.idFacultad = :facultad")})

public class ActaGrado implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_ACTA= "ActaGrado.findByActaGenerada";
    public static final String FIND_BY_FACULTAD = "ActaGrado.findByActaGeneradaFacultad";
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdActaGrado")
    private Integer idActaGrado;
    @Size(max = 255)
    @Column(name = "NumeroActaGrado")
    private String numeroActaGrado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NotaComplexivoGeneral")
    private Float notaComplexivoGeneral;
    @Column(name = "NotaComplexivoProfesional")
    private Float notaComplexivoProfesional;
    @Column(name = "NotaGraciaGeneral")
    private Float notaGraciaGeneral;
    @Column(name = "NotaGraciaProfesional")
    private Float notaGraciaProfesional;
    @Column(name = "NotaTrabajoInvestigaci\u00f3n")
    private Float notaTrabajoInvestigación;
    @Column(name = "PromedioEstudios")
    private Float promedioEstudios;
    @Column(name = "ActaGenerada")
    private Boolean actaGenerada;
    @Column(name = "ActaBloqueada")
    private Boolean actaBloqueada;
    @Column(name = "FechaIngresoActa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoActa;
    @Size(max = 200)
    @Column(name = "Evidencia")
    private String evidencia;
    @Column(name = "IdUsuario")
    private Integer idUsuario;
    @Size(max = 50)
    @Column(name = "NombreSecretario")
    private String nombreSecretario;
    @Size(max = 50)
    @Column(name = "NombreAutoridad")
    private String nombreAutoridad;
    @Size(max = 50)
    @Column(name = "Periodo")
    private String periodo;
    @Column(name = "FechaGenerarActa")
    @Temporal(TemporalType.DATE)
    private Date fechaGenerarActa;
    @Column(name = "HoraGenerarActa")
    @Temporal(TemporalType.TIME)
    private Date horaGenerarActa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numActa")
    private List<ActaEditada> actaEditadaList;
    @JoinColumn(name = "CedulaEstudiante", referencedColumnName = "Cedula")
    @ManyToOne
    private Estudiante cedulaEstudiante;

    public ActaGrado() {
    }

    public ActaGrado(Integer idActaGrado) {
        this.idActaGrado = idActaGrado;
    }

    public Integer getIdActaGrado() {
        return idActaGrado;
    }

    public void setIdActaGrado(Integer idActaGrado) {
        this.idActaGrado = idActaGrado;
    }

    public String getNumeroActaGrado() {
        return numeroActaGrado;
    }

    public void setNumeroActaGrado(String numeroActaGrado) {
        this.numeroActaGrado = numeroActaGrado;
    }

    public Float getNotaComplexivoGeneral() {
        return notaComplexivoGeneral;
    }

    public void setNotaComplexivoGeneral(Float notaComplexivoGeneral) {
        this.notaComplexivoGeneral = notaComplexivoGeneral;
    }

    public Float getNotaComplexivoProfesional() {
        return notaComplexivoProfesional;
    }

    public void setNotaComplexivoProfesional(Float notaComplexivoProfesional) {
        this.notaComplexivoProfesional = notaComplexivoProfesional;
    }

    public Float getNotaGraciaGeneral() {
        return notaGraciaGeneral;
    }

    public void setNotaGraciaGeneral(Float notaGraciaGeneral) {
        this.notaGraciaGeneral = notaGraciaGeneral;
    }

    public Float getNotaGraciaProfesional() {
        return notaGraciaProfesional;
    }

    public void setNotaGraciaProfesional(Float notaGraciaProfesional) {
        this.notaGraciaProfesional = notaGraciaProfesional;
    }

    public Float getNotaTrabajoInvestigación() {
        return notaTrabajoInvestigación;
    }

    public void setNotaTrabajoInvestigación(Float notaTrabajoInvestigación) {
        this.notaTrabajoInvestigación = notaTrabajoInvestigación;
    }

    public Float getPromedioEstudios() {
        return promedioEstudios;
    }

    public void setPromedioEstudios(Float promedioEstudios) {
        this.promedioEstudios = promedioEstudios;
    }

    public Boolean getActaGenerada() {
        return actaGenerada;
    }

    public void setActaGenerada(Boolean actaGenerada) {
        this.actaGenerada = actaGenerada;
    }

    public Boolean getActaBloqueada() {
        return actaBloqueada;
    }

    public void setActaBloqueada(Boolean actaBloqueada) {
        this.actaBloqueada = actaBloqueada;
    }

    public Date getFechaIngresoActa() {
        return fechaIngresoActa;
    }

    public void setFechaIngresoActa(Date fechaIngresoActa) {
        this.fechaIngresoActa = fechaIngresoActa;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreSecretario() {
        return nombreSecretario;
    }

    public void setNombreSecretario(String nombreSecretario) {
        this.nombreSecretario = nombreSecretario;
    }

    public String getNombreAutoridad() {
        return nombreAutoridad;
    }

    public void setNombreAutoridad(String nombreAutoridad) {
        this.nombreAutoridad = nombreAutoridad;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getFechaGenerarActa() {
        return fechaGenerarActa;
    }

    public void setFechaGenerarActa(Date fechaGenerarActa) {
        this.fechaGenerarActa = fechaGenerarActa;
    }

    public Date getHoraGenerarActa() {
        return horaGenerarActa;
    }

    public void setHoraGenerarActa(Date horaGenerarActa) {
        this.horaGenerarActa = horaGenerarActa;
    }

    @XmlTransient
    public List<ActaEditada> getActaEditadaList() {
        return actaEditadaList;
    }

    public void setActaEditadaList(List<ActaEditada> actaEditadaList) {
        this.actaEditadaList = actaEditadaList;
    }

    public Estudiante getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    public void setCedulaEstudiante(Estudiante cedulaEstudiante) {
        this.cedulaEstudiante = cedulaEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActaGrado != null ? idActaGrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActaGrado)) {
            return false;
        }
        ActaGrado other = (ActaGrado) object;
        if ((this.idActaGrado == null && other.idActaGrado != null) || (this.idActaGrado != null && !this.idActaGrado.equals(other.idActaGrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uteq.model.ActaGrado[ idActaGrado=" + idActaGrado + " ]";
    }
    
}
