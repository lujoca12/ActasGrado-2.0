/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.uteq.model.ActaEditada;

/**
 *
 * @author Moises
 */
@Stateless
public class ActaEditadaFacade extends AbstractFacade<ActaEditada> implements ActaEditadaFacadeLocal {

    @PersistenceContext(unitName = "ActasGrado-2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActaEditadaFacade() {
        super(ActaEditada.class);
    }
    
}
