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
@Table(name = "Facultad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f")
    , @NamedQuery(name = "Facultad.findByIdFacultad", query = "SELECT f FROM Facultad f WHERE f.idFacultad = :idFacultad")
    , @NamedQuery(name = "Facultad.findByFacultad", query = "SELECT f FROM Facultad f WHERE f.facultad = :facultad")
    , @NamedQuery(name = "Facultad.findByDenominacionAutoridad", query = "SELECT f FROM Facultad f WHERE f.denominacionAutoridad = :denominacionAutoridad")
    , @NamedQuery(name = "Facultad.findByGeneroAutoridad", query = "SELECT f FROM Facultad f WHERE f.generoAutoridad = :generoAutoridad")
    , @NamedQuery(name = "Facultad.findByAutoridad", query = "SELECT f FROM Facultad f WHERE f.autoridad = :autoridad")
    , @NamedQuery(name = "Facultad.findByDenominacionSecretario", query = "SELECT f FROM Facultad f WHERE f.denominacionSecretario = :denominacionSecretario")
    , @NamedQuery(name = "Facultad.findByGeneroSecretario", query = "SELECT f FROM Facultad f WHERE f.generoSecretario = :generoSecretario")
    , @NamedQuery(name = "Facultad.findBySecretario", query = "SELECT f FROM Facultad f WHERE f.secretario = :secretario")
    , @NamedQuery(name = "Facultad.findBySecuencialActa", query = "SELECT f FROM Facultad f WHERE f.secuencialActa = :secuencialActa")
    , @NamedQuery(name = "Facultad.findByPresentarSecuencial", query = "SELECT f FROM Facultad f WHERE f.presentarSecuencial = :presentarSecuencial")})
public class Facultad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdFacultad")
    private Integer idFacultad;
    @Size(max = 255)
    @Column(name = "Facultad")
    private String facultad;
    @Size(max = 255)
    @Column(name = "DenominacionAutoridad")
    private String denominacionAutoridad;
    @Size(max = 1)
    @Column(name = "GeneroAutoridad")
    private String generoAutoridad;
    @Size(max = 100)
    @Column(name = "Autoridad")
    private String autoridad;
    @Size(max = 100)
    @Column(name = "DenominacionSecretario")
    private String denominacionSecretario;
    @Size(max = 1)
    @Column(name = "GeneroSecretario")
    private String generoSecretario;
    @Size(max = 100)
    @Column(name = "Secretario")
    private String secretario;
    @Size(max = 10)
    @Column(name = "SecuencialActa")
    private String secuencialActa;
    @Column(name = "PresentarSecuencial")
    private Boolean presentarSecuencial;
    @OneToMany(mappedBy = "idFacultad")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "idFacultad")
    private List<Carrera> carreraList;

    public Facultad() {
    }

    public Facultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }

    public Integer getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getDenominacionAutoridad() {
        return denominacionAutoridad;
    }

    public void setDenominacionAutoridad(String denominacionAutoridad) {
        this.denominacionAutoridad = denominacionAutoridad;
    }

    public String getGeneroAutoridad() {
        return generoAutoridad;
    }

    public void setGeneroAutoridad(String generoAutoridad) {
        this.generoAutoridad = generoAutoridad;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public String getDenominacionSecretario() {
        return denominacionSecretario;
    }

    public void setDenominacionSecretario(String denominacionSecretario) {
        this.denominacionSecretario = denominacionSecretario;
    }

    public String getGeneroSecretario() {
        return generoSecretario;
    }

    public void setGeneroSecretario(String generoSecretario) {
        this.generoSecretario = generoSecretario;
    }

    public String getSecretario() {
        return secretario;
    }

    public void setSecretario(String secretario) {
        this.secretario = secretario;
    }

    public String getSecuencialActa() {
        return secuencialActa;
    }

    public void setSecuencialActa(String secuencialActa) {
        this.secuencialActa = secuencialActa;
    }

    public Boolean getPresentarSecuencial() {
        return presentarSecuencial;
    }

    public void setPresentarSecuencial(Boolean presentarSecuencial) {
        this.presentarSecuencial = presentarSecuencial;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacultad != null ? idFacultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.idFacultad == null && other.idFacultad != null) || (this.idFacultad != null && !this.idFacultad.equals(other.idFacultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uteq.model.Facultad[ idFacultad=" + idFacultad + " ]";
    }
    
}
