/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "Titulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Titulo.findAll", query = "SELECT t FROM Titulo t")
    , @NamedQuery(name = "Titulo.findByIdtitulo", query = "SELECT t FROM Titulo t WHERE t.idtitulo = :idtitulo")
    , @NamedQuery(name = "Titulo.findByTitulo", query = "SELECT t FROM Titulo t WHERE t.titulo = :titulo")})
public class Titulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Idtitulo")
    private Integer idtitulo;
    @Size(max = 255)
    @Column(name = "Titulo")
    private String titulo;
    @JoinColumn(name = "IdCarrera", referencedColumnName = "IdCarrera")
    @ManyToOne
    private Carrera idCarrera;
    @OneToMany(mappedBy = "idTitulo")
    private List<Estudiante> estudianteList;
    @OneToMany(mappedBy = "idTitulo")
    private List<DenominacionTitulo> denominacionTituloList;

    public Titulo() {
    }

    public Titulo(Integer idtitulo) {
        this.idtitulo = idtitulo;
    }

    public Integer getIdtitulo() {
        return idtitulo;
    }

    public void setIdtitulo(Integer idtitulo) {
        this.idtitulo = idtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Carrera getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @XmlTransient
    public List<DenominacionTitulo> getDenominacionTituloList() {
        return denominacionTituloList;
    }

    public void setDenominacionTituloList(List<DenominacionTitulo> denominacionTituloList) {
        this.denominacionTituloList = denominacionTituloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtitulo != null ? idtitulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Titulo)) {
            return false;
        }
        Titulo other = (Titulo) object;
        if ((this.idtitulo == null && other.idtitulo != null) || (this.idtitulo != null && !this.idtitulo.equals(other.idtitulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uteq.model.Titulo[ idtitulo=" + idtitulo + " ]";
    }
    
}
