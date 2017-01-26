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
@Table(name = "TipoCalificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCalificacion.findAll", query = "SELECT t FROM TipoCalificacion t")
    , @NamedQuery(name = "TipoCalificacion.findByIdTipoCalificacion", query = "SELECT t FROM TipoCalificacion t WHERE t.idTipoCalificacion = :idTipoCalificacion")
    , @NamedQuery(name = "TipoCalificacion.findByTipoCalificacion", query = "SELECT t FROM TipoCalificacion t WHERE t.tipoCalificacion = :tipoCalificacion")})
public class TipoCalificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdTipoCalificacion")
    private Integer idTipoCalificacion;
    @Size(max = 50)
    @Column(name = "TipoCalificacion")
    private String tipoCalificacion;
    @JoinColumn(name = "IdTipoUsuario", referencedColumnName = "IdTipoUsuario")
    @ManyToOne(optional = false)
    private TipoUsuario idTipoUsuario;

    public TipoCalificacion() {
    }

    public TipoCalificacion(Integer idTipoCalificacion) {
        this.idTipoCalificacion = idTipoCalificacion;
    }

    public Integer getIdTipoCalificacion() {
        return idTipoCalificacion;
    }

    public void setIdTipoCalificacion(Integer idTipoCalificacion) {
        this.idTipoCalificacion = idTipoCalificacion;
    }

    public String getTipoCalificacion() {
        return tipoCalificacion;
    }

    public void setTipoCalificacion(String tipoCalificacion) {
        this.tipoCalificacion = tipoCalificacion;
    }

    public TipoUsuario getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(TipoUsuario idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCalificacion != null ? idTipoCalificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCalificacion)) {
            return false;
        }
        TipoCalificacion other = (TipoCalificacion) object;
        if ((this.idTipoCalificacion == null && other.idTipoCalificacion != null) || (this.idTipoCalificacion != null && !this.idTipoCalificacion.equals(other.idTipoCalificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uteq.model.TipoCalificacion[ idTipoCalificacion=" + idTipoCalificacion + " ]";
    }
    
}
