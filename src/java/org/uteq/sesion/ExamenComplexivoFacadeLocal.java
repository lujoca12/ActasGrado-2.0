/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.List;
import javax.ejb.Local;
import org.uteq.model.ExamenComplexivo;

/**
 *
 * @author Moises
 */
@Local
public interface ExamenComplexivoFacadeLocal {

    void create(ExamenComplexivo examenComplexivo);

    void edit(ExamenComplexivo examenComplexivo);

    void remove(ExamenComplexivo examenComplexivo);

    ExamenComplexivo find(Object id);

    List<ExamenComplexivo> findAll();

    List<ExamenComplexivo> findRange(int[] range);

    int count();
    
}
