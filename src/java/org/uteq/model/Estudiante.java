/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "ESTUDIANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e")
    ,
    @NamedQuery(name = "Estudiante.findByCedula", query = "SELECT e FROM Estudiante e WHERE e.cedula = :cedula")
    ,
    @NamedQuery(name = "Estudiante.findByApellidos", query = "SELECT e FROM Estudiante e WHERE e.apellidos = :apellidos")
    ,
    @NamedQuery(name = "Estudiante.findByNombres", query = "SELECT e FROM Estudiante e WHERE e.nombres = :nombres")
    ,
    @NamedQuery(name = "Estudiante.findByGenero", query = "SELECT e FROM Estudiante e WHERE e.genero = :genero")
    ,
    @NamedQuery(name = "Estudiante.findByTitulo", query = "Select e from Titulo t JOIN t.estudianteList e JOIN e.actaGradoList a JOIN e.estudianteExComplexivoList ex WHERE t.idtitulo = :titulo and e.idTipoTitulacion.idTipoTitulacion = :titulacion and (a.notaComplexivoGeneral+a.notaComplexivoProfesional) >= 7 and ex.idExamenComplexivo.idExamenComplexivo = :periodo ORDER BY e.apellidos,e.nombres ASC")
    ,
    @NamedQuery(name = "Estudiante.findByTituloG", query = "Select e from Titulo t JOIN t.estudianteList e JOIN e.actaGradoList a JOIN e.estudianteExComplexivoList ex WHERE t.idtitulo = :titulo and e.idTipoTitulacion.idTipoTitulacion = :titulacion and ex.idExamenComplexivo.idExamenComplexivo = :periodo and (a.notaGraciaGeneral+a.notaGraciaProfesional) >= 7 ORDER BY e.apellidos,e.nombres ASC")
    ,
    @NamedQuery(name = "Estudiante.findByExComplex", query = "select e.cedulaEstudiante from EstudianteExComplexivo e where e.cedulaEstudiante = :estudiante")
    ,
    @NamedQuery(name = "Estudiante.findByFacultad", query = "select e from Estudiante e where e.idTitulo.idCarrera.idFacultad  = :facultad")})

public class Estudiante implements Serializable {

    public static final String FIND_BY_TITULO = "Estudiante.findByTitulo";
    public static final String FIND_BY_TITULOG = "Estudiante.findByTituloG";
    public static final String FIND_BY_EXCOMPLEX = "Estudiante.findByExComplex";
    public static final String FIND_BY_FACULTAD = "Estudiante.findByFacultad";

    
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "Cedula")
    private String cedula;
    @Size(max = 255)
    @Column(name = "Apellidos")
    private String apellidos;
    @Size(max = 255)
    @Column(name = "Nombres")
    private String nombres;
    @Size(max = 1)
    @Column(name = "Genero")
    private String genero;
    @JoinColumn(name = "IdTipoTitulacion", referencedColumnName = "IdTipoTitulacion")
    @ManyToOne
    private TipoTitulacion idTipoTitulacion;
    @JoinColumn(name = "IdTitulo", referencedColumnName = "Idtitulo")
    @ManyToOne
    private Titulo idTitulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cedulaEstudiante")
    private List<EstudianteExComplexivo> estudianteExComplexivoList;
    @OneToMany(mappedBy = "cedulaEstudiante")
    private List<ActaGrado> actaGradoList;

    public Estudiante() {
    }

    public Estudiante(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public TipoTitulacion getIdTipoTitulacion() {
        return idTipoTitulacion;
    }

    public void setIdTipoTitulacion(TipoTitulacion idTipoTitulacion) {
        this.idTipoTitulacion = idTipoTitulacion;
    }

    public Titulo getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(Titulo idTitulo) {
        this.idTitulo = idTitulo;
    }

    @XmlTransient
    public List<EstudianteExComplexivo> getEstudianteExComplexivoList() {
        return estudianteExComplexivoList;
    }

    public void setEstudianteExComplexivoList(List<EstudianteExComplexivo> estudianteExComplexivoList) {
        this.estudianteExComplexivoList = estudianteExComplexivoList;
    }

    @XmlTransient
    public List<ActaGrado> getActaGradoList() {
        return actaGradoList;
    }

    public void setActaGradoList(List<ActaGrado> actaGradoList) {
        this.actaGradoList = actaGradoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uteq.model.Estudiante[ cedula=" + cedula + " ]";
    }

}
