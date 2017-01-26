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
@Table(name = "TipoTitulacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTitulacion.findAll", query = "SELECT t FROM TipoTitulacion t")
    , @NamedQuery(name = "TipoTitulacion.findByIdTipoTitulacion", query = "SELECT t FROM TipoTitulacion t WHERE t.idTipoTitulacion = :idTipoTitulacion")
    , @NamedQuery(name = "TipoTitulacion.findByTipoTitulacion", query = "SELECT t FROM TipoTitulacion t WHERE t.tipoTitulacion = :tipoTitulacion")})
public class TipoTitulacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdTipoTitulacion")
    private Integer idTipoTitulacion;
    @Size(max = 255)
    @Column(name = "TipoTitulacion")
    private String tipoTitulacion;
    @OneToMany(mappedBy = "idTipoTitulacion")
    private List<Estudiante> estudianteList;

    public TipoTitulacion() {
    }

    public TipoTitulacion(Integer idTipoTitulacion) {
        this.idTipoTitulacion = idTipoTitulacion;
    }

    public Integer getIdTipoTitulacion() {
        return idTipoTitulacion;
    }

    public void setIdTipoTitulacion(Integer idTipoTitulacion) {
        this.idTipoTitulacion = idTipoTitulacion;
    }

    public String getTipoTitulacion() {
        return tipoTitulacion;
    }

    public void setTipoTitulacion(String tipoTitulacion) {
        this.tipoTitulacion = tipoTitulacion;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoTitulacion != null ? idTipoTitulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTitulacion)) {
            return false;
        }
        TipoTitulacion other = (TipoTitulacion) object;
        if ((this.idTipoTitulacion == null && other.idTipoTitulacion != null) || (this.idTipoTitulacion != null && !this.idTipoTitulacion.equals(other.idTipoTitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uteq.model.TipoTitulacion[ idTipoTitulacion=" + idTipoTitulacion + " ]";
    }
    
}
