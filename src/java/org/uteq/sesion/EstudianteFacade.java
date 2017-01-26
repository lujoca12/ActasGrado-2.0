/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.uteq.model.Estudiante;
import org.uteq.model.EstudianteExComplexivo;
import org.uteq.model.Facultad;

/**
 *
 * @author Moises
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> implements EstudianteFacadeLocal {

    @PersistenceContext(unitName = "ActasGrado-2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    @Override
    public List<Estudiante> findEstudianteEntitiesA(Integer titulo, Integer tipoTitulacion, Integer periodo) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("titulacion", tipoTitulacion);
        parameters.put("titulo", titulo);
        parameters.put("periodo", periodo);
        return super.findResultados(Estudiante.FIND_BY_TITULO, parameters);
    }

    @Override
    public List<Estudiante> findEstudianteEntitiesG(Integer titulo, Integer tipoTitulacion, Integer periodo) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("titulacion", tipoTitulacion);
        parameters.put("titulo", titulo);
        parameters.put("periodo", periodo);
        return super.findResultados(Estudiante.FIND_BY_TITULOG, parameters);
    }

    @Override
    public List<Estudiante> findByPeriodo(int periodo, int carrera, int titulacion, String cond) {
        String query = "select e.cedulaEstudiante from EstudianteExComplexivo e JOIN e.cedulaEstudiante.actaGradoList a where e.idExamenComplexivo.idExamenComplexivo = " + periodo + " and e.cedulaEstudiante.idTitulo.idCarrera.idCarrera = " + carrera + " " + cond;
        return super.findResultadosQ(query);
    }

    @Override
    public Estudiante findByExComplexivo(Estudiante e) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("estudiante", e);
        return super.findResultado(Estudiante.FIND_BY_EXCOMPLEX, parameters);
    }

    @Override
    public List<Estudiante> findByFacultad(Facultad f) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("facultad", f);
        return super.findResultados(Estudiante.FIND_BY_FACULTAD, parameters);
    }

}
