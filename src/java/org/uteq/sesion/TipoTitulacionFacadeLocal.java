/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.List;
import javax.ejb.Local;
import org.uteq.model.TipoTitulacion;

/**
 *
 * @author Moises
 */
@Local
public interface TipoTitulacionFacadeLocal {

    void create(TipoTitulacion tipoTitulacion);

    void edit(TipoTitulacion tipoTitulacion);

    void remove(TipoTitulacion tipoTitulacion);

    TipoTitulacion find(Object id);

    List<TipoTitulacion> findAll();

    List<TipoTitulacion> findRange(int[] range);

    int count();
    
}
