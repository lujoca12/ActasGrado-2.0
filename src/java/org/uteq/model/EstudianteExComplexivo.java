/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "EstudianteExComplexivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudianteExComplexivo.findAll", query = "SELECT e FROM EstudianteExComplexivo e")
    , @NamedQuery(name = "EstudianteExComplexivo.findByIdEstudianteExComplexivo", query = "SELECT e FROM EstudianteExComplexivo e WHERE e.idEstudianteExComplexivo = :idEstudianteExComplexivo")})
public class EstudianteExComplexivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEstudianteExComplexivo")
    private Integer idEstudianteExComplexivo;
    @JoinColumn(name = "CedulaEstudiante", referencedColumnName = "Cedula")
    @ManyToOne(optional = false)
    private Estudiante cedulaEstudiante;
    @JoinColumn(name = "IdExamenComplexivo", referencedColumnName = "IdExamenComplexivo")
    @ManyToOne(optional = false)
    private ExamenComplexivo idExamenComplexivo;

    public EstudianteExComplexivo() {
    }

    public EstudianteExComplexivo(Integer idEstudianteExComplexivo) {
        this.idEstudianteExComplexivo = idEstudianteExComplexivo;
    }

    public Integer getIdEstudianteExComplexivo() {
        return idEstudianteExComplexivo;
    }

    public void setIdEstudianteExComplexivo(Integer idEstudianteExComplexivo) {
        this.idEstudianteExComplexivo = idEstudianteExComplexivo;
    }

    public Estudiante getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    public void setCedulaEstudiante(Estudiante cedulaEstudiante) {
        this.cedulaEstudiante = cedulaEstudiante;
    }

    public ExamenComplexivo getIdExamenComplexivo() {
        return idExamenComplexivo;
    }

    public void setIdExamenComplexivo(ExamenComplexivo idExamenComplexivo) {
        this.idExamenComplexivo = idExamenComplexivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudianteExComplexivo != null ? idEstudianteExComplexivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteExComplexivo)) {
            return false;
        }
        EstudianteExComplexivo other = (EstudianteExComplexivo) object;
        if ((this.idEstudianteExComplexivo == null && other.idEstudianteExComplexivo != null) || (this.idEstudianteExComplexivo != null && !this.idEstudianteExComplexivo.equals(other.idEstudianteExComplexivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uteq.model.EstudianteExComplexivo[ idEstudianteExComplexivo=" + idEstudianteExComplexivo + " ]";
    }
    
}
