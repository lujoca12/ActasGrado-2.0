/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.List;
import javax.ejb.Local;
import org.uteq.model.DenominacionTitulo;

/**
 *
 * @author Moises
 */
@Local
public interface DenominacionTituloFacadeLocal {

    void create(DenominacionTitulo denominacionTitulo);

    void edit(DenominacionTitulo denominacionTitulo);

    void remove(DenominacionTitulo denominacionTitulo);

    DenominacionTitulo find(Object id);

    List<DenominacionTitulo> findAll();

    List<DenominacionTitulo> findRange(int[] range);
    
    DenominacionTitulo findTituloGenero(Integer titulo, String genero);

    int count();
    
}
