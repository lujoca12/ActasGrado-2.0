/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.List;
import javax.ejb.Local;
import org.uteq.model.TipoCalificacion;

/**
 *
 * @author Moises
 */
@Local
public interface TipoCalificacionFacadeLocal {

    void create(TipoCalificacion tipoCalificacion);

    void edit(TipoCalificacion tipoCalificacion);

    void remove(TipoCalificacion tipoCalificacion);

    TipoCalificacion find(Object id);

    List<TipoCalificacion> findAll();

    List<TipoCalificacion> findRange(int[] range);

    int count();
    
}
