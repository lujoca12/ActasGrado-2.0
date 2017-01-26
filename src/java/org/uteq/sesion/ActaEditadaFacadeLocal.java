/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.List;
import javax.ejb.Local;
import org.uteq.model.ActaEditada;

/**
 *
 * @author Moises
 */
@Local
public interface ActaEditadaFacadeLocal {

    void create(ActaEditada actaEditada);

    void edit(ActaEditada actaEditada);

    void remove(ActaEditada actaEditada);

    ActaEditada find(Object id);

    List<ActaEditada> findAll();

    List<ActaEditada> findRange(int[] range);

    int count();
    
}
