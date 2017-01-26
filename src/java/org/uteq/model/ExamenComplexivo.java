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
@Table(name = "ExamenComplexivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamenComplexivo.findAll", query = "SELECT e FROM ExamenComplexivo e")
    , @NamedQuery(name = "ExamenComplexivo.findByIdExamenComplexivo", query = "SELECT e FROM ExamenComplexivo e WHERE e.idExamenComplexivo = :idExamenComplexivo")
    , @NamedQuery(name = "ExamenComplexivo.findByAulaEvaluacion", query = "SELECT e FROM ExamenComplexivo e WHERE e.aulaEvaluacion = :aulaEvaluacion")
    , @NamedQuery(name = "ExamenComplexivo.findByFechaEvaluacion", query = "SELECT e FROM ExamenComplexivo e WHERE e.fechaEvaluacion = :fechaEvaluacion")
    , @NamedQuery(name = "ExamenComplexivo.findByDelegadoCarrera", query = "SELECT e FROM ExamenComplexivo e WHERE e.delegadoCarrera = :delegadoCarrera")
    , @NamedQuery(name = "ExamenComplexivo.findByDelegadoUPA", query = "SELECT e FROM ExamenComplexivo e WHERE e.delegadoUPA = :delegadoUPA")
    , @NamedQuery(name = "ExamenComplexivo.findByDenominacionExC", query = "SELECT e FROM ExamenComplexivo e WHERE e.denominacionExC = :denominacionExC")})
public class ExamenComplexivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdExamenComplexivo")
    private Integer idExamenComplexivo;
    @Size(max = 50)
    @Column(name = "AulaEvaluacion")
    private String aulaEvaluacion;
    @Column(name = "FechaEvaluacion")
    @Temporal(TemporalType.DATE)
    private Date fechaEvaluacion;
    @Size(max = 50)
    @Column(name = "DelegadoCarrera")
    private String delegadoCarrera;
    @Size(max = 50)
    @Column(name = "DelegadoUPA")
    private String delegadoUPA;
    @Size(max = 50)
    @Column(name = "DenominacionExC")
    private String denominacionExC;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamenComplexivo")
    private List<EstudianteExComplexivo> estudianteExComplexivoList;

    public ExamenComplexivo() {
    }

    public ExamenComplexivo(Integer idExamenComplexivo) {
        this.idExamenComplexivo = idExamenComplexivo;
    }

    public Integer getIdExamenComplexivo() {
        return idExamenComplexivo;
    }

    public void setIdExamenComplexivo(Integer idExamenComplexivo) {
        this.idExamenComplexivo = idExamenComplexivo;
    }

    public String getAulaEvaluacion() {
        return aulaEvaluacion;
    }

    public void setAulaEvaluacion(String aulaEvaluacion) {
        this.aulaEvaluacion = aulaEvaluacion;
    }

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public String getDelegadoCarrera() {
        return delegadoCarrera;
    }

    public void setDelegadoCarrera(String delegadoCarrera) {
        this.delegadoCarrera = delegadoCarrera;
    }

    public String getDelegadoUPA() {
        return delegadoUPA;
    }

    public void setDelegadoUPA(String delegadoUPA) {
        this.delegadoUPA = delegadoUPA;
    }

    public String getDenominacionExC() {
        return denominacionExC;
    }

    public void setDenominacionExC(String denominacionExC) {
        this.denominacionExC = denominacionExC;
    }

    @XmlTransient
    public List<EstudianteExComplexivo> getEstudianteExComplexivoList() {
        return estudianteExComplexivoList;
    }

    public void setEstudianteExComplexivoList(List<EstudianteExComplexivo> estudianteExComplexivoList) {
        this.estudianteExComplexivoList = estudianteExComplexivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenComplexivo != null ? idExamenComplexivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenComplexivo)) {
            return false;
        }
        ExamenComplexivo other = (ExamenComplexivo) object;
        if ((this.idExamenComplexivo == null && other.idExamenComplexivo != null) || (this.idExamenComplexivo != null && !this.idExamenComplexivo.equals(other.idExamenComplexivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uteq.model.ExamenComplexivo[ idExamenComplexivo=" + idExamenComplexivo + " ]";
    }
    
}
