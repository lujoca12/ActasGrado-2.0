/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.uteq.model.DenominacionTitulo;

/**
 *
 * @author Moises
 */
@Stateless
public class DenominacionTituloFacade extends AbstractFacade<DenominacionTitulo> implements DenominacionTituloFacadeLocal {

    @PersistenceContext(unitName = "ActasGrado-2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DenominacionTituloFacade() {
        super(DenominacionTitulo.class);
    }

    @Override
    public DenominacionTitulo findTituloGenero(Integer titulo, String genero) {
        DenominacionTitulo dt = new DenominacionTitulo();
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("genero", genero);
            parameters.put("titulo", titulo);
            dt = findResultado(DenominacionTitulo.FIND_BY_GEN_IT, parameters);
        } catch (Exception e) {
            e.getMessage();
            dt = null;
        }
        return dt;
    }

}
