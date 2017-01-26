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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "DenominacionTitulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DenominacionTitulo.findAll", query = "SELECT d FROM DenominacionTitulo d")
    ,
    @NamedQuery(name = "DenominacionTitulo.findByIdDenominacionTitulo", query = "SELECT d FROM DenominacionTitulo d WHERE d.idDenominacionTitulo = :idDenominacionTitulo")
    ,
    @NamedQuery(name = "DenominacionTitulo.findByDenominacionTitulo", query = "SELECT d FROM DenominacionTitulo d WHERE d.denominacionTitulo = :denominacionTitulo")
    ,
    @NamedQuery(name = "DenominacionTitulo.findByGenero", query = "SELECT d FROM DenominacionTitulo d WHERE d.genero = :genero")
    ,
    @NamedQuery(name = "DenominacionTitulo.findByGenTit", query = "Select d FROM DenominacionTitulo d WHERE d.idTitulo.idtitulo = :titulo and d.genero = :genero")})
public class DenominacionTitulo implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_GEN_IT = "DenominacionTitulo.findByGenTit";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdDenominacionTitulo")
    private Integer idDenominacionTitulo;
    @Size(max = 200)
    @Column(name = "DenominacionTitulo")
    private String denominacionTitulo;
    @Size(max = 1)
    @Column(name = "Genero")
    private String genero;
    @JoinColumn(name = "IdTitulo", referencedColumnName = "Idtitulo")
    @ManyToOne
    private Titulo idTitulo;

    public DenominacionTitulo() {
    }

    public DenominacionTitulo(Integer idDenominacionTitulo) {
        this.idDenominacionTitulo = idDenominacionTitulo;
    }

    public Integer getIdDenominacionTitulo() {
        return idDenominacionTitulo;
    }

    public void setIdDenominacionTitulo(Integer idDenominacionTitulo) {
        this.idDenominacionTitulo = idDenominacionTitulo;
    }

    public String getDenominacionTitulo() {
        return denominacionTitulo;
    }

    public void setDenominacionTitulo(String denominacionTitulo) {
        this.denominacionTitulo = denominacionTitulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Titulo getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(Titulo idTitulo) {
        this.idTitulo = idTitulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDenominacionTitulo != null ? idDenominacionTitulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DenominacionTitulo)) {
            return false;
        }
        DenominacionTitulo other = (DenominacionTitulo) object;
        if ((this.idDenominacionTitulo == null && other.idDenominacionTitulo != null) || (this.idDenominacionTitulo != null && !this.idDenominacionTitulo.equals(other.idDenominacionTitulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uteq.model.DenominacionTitulo[ idDenominacionTitulo=" + idDenominacionTitulo + " ]";
    }

}
