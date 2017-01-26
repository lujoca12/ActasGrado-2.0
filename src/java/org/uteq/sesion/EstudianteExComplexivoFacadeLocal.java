/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.List;
import javax.ejb.Local;
import org.uteq.model.Estudiante;
import org.uteq.model.EstudianteExComplexivo;

/**
 *
 * @author Moises
 */
@Local
public interface EstudianteExComplexivoFacadeLocal {

    void create(EstudianteExComplexivo estudianteExComplexivo);

    void edit(EstudianteExComplexivo estudianteExComplexivo);

    void remove(EstudianteExComplexivo estudianteExComplexivo);

    EstudianteExComplexivo find(Object id);

    List<EstudianteExComplexivo> findAll();

    List<EstudianteExComplexivo> findRange(int[] range);

    int count();
    
}
