/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.List;
import javax.ejb.Local;
import org.uteq.model.ActaGrado;

/**
 *
 * @author Moises
 */
@Local
public interface ActaGradoFacadeLocal {

    void create(ActaGrado actaGrado);

    void edit(ActaGrado actaGrado);

    void remove(ActaGrado actaGrado);

    ActaGrado find(Object id);

    List<ActaGrado> findAll();

    List<ActaGrado> findRange(int[] range);
    
    List<ActaGrado> findTrue();
    
    List<ActaGrado> findTrueFacultadd();

    int count();
    
}
