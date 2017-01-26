/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.sesion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.uteq.model.ActaGrado;
import org.uteq.util.SessionUtil;

/**
 *
 * @author Moises
 */
@Stateless
public class ActaGradoFacade extends AbstractFacade<ActaGrado> implements ActaGradoFacadeLocal {

    @PersistenceContext(unitName = "ActasGrado-2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActaGradoFacade() {
        super(ActaGrado.class);
    }

    @Override
    public List<ActaGrado> findTrue() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("actaGenerada", true);
        return super.findResultados(ActaGrado.FIND_BY_ACTA, parameters);
    }

    @Override
    public List<ActaGrado> findTrueFacultadd() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("actaGenerada", true);
        parameters.put("facultad", SessionUtil.getUsuario().getIdFacultad());
        return super.findResultados(ActaGrado.FIND_BY_FACULTAD, parameters);
    }

}
