/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.List;
import javax.ejb.Local;
import org.uteq.model.Estudiante;
import org.uteq.model.Facultad;

/**
 *
 * @author Moises
 */
@Local
public interface EstudianteFacadeLocal {

    void create(Estudiante estudiante);

    void edit(Estudiante estudiante);

    void remove(Estudiante estudiante);

    Estudiante find(Object id);

    List<Estudiante> findAll();

    List<Estudiante> findRange(int[] range);
    
    List<Estudiante> findEstudianteEntitiesA(Integer titulo, Integer tipoTitulacion,Integer periodo);
    
    List<Estudiante> findEstudianteEntitiesG(Integer titulo, Integer tipoTitulacion,Integer periodo);
    
    List<Estudiante> findByPeriodo(int periodo, int carrera, int titulacion, String cond);
    
    Estudiante findByExComplexivo(Estudiante e);
    
    List<Estudiante> findByFacultad(Facultad f);

    int count();
    
}
