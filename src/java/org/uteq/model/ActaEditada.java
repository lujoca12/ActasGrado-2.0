/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "ActaEditada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActaEditada.findAll", query = "SELECT a FROM ActaEditada a")
    , @NamedQuery(name = "ActaEditada.findById", query = "SELECT a FROM ActaEditada a WHERE a.id = :id")
    , @NamedQuery(name = "ActaEditada.findByMotivo", query = "SELECT a FROM ActaEditada a WHERE a.motivo = :motivo")
    , @NamedQuery(name = "ActaEditada.findByPromedioAnterior", query = "SELECT a FROM ActaEditada a WHERE a.promedioAnterior = :promedioAnterior")
    , @NamedQuery(name = "ActaEditada.findByFecha", query = "SELECT a FROM ActaEditada a WHERE a.fecha = :fecha")})
public class ActaEditada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "Motivo")
    private String motivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PromedioAnterior")
    private Float promedioAnterior;
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "NumActa", referencedColumnName = "IdActaGrado")
    @ManyToOne(optional = false)
    private ActaGrado numActa;
    @JoinColumn(name = "IdUsuario", referencedColumnName = "IdUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public ActaEditada() {
    }

    public ActaEditada(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Float getPromedioAnterior() {
        return promedioAnterior;
    }

    public void setPromedioAnterior(Float promedioAnterior) {
        this.promedioAnterior = promedioAnterior;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ActaGrado getNumActa() {
        return numActa;
    }

    public void setNumActa(ActaGrado numActa) {
        this.numActa = numActa;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActaEditada)) {
            return false;
        }
        ActaEditada other = (ActaEditada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uteq.model.ActaEditada[ id=" + id + " ]";
    }
    
}
