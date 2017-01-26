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
import org.uteq.model.Estudiante;
import org.uteq.model.EstudianteExComplexivo;

/**
 *
 * @author Moises
 */
@Stateless
public class EstudianteExComplexivoFacade extends AbstractFacade<EstudianteExComplexivo> implements EstudianteExComplexivoFacadeLocal {

    @PersistenceContext(unitName = "ActasGrado-2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteExComplexivoFacade() {
        super(EstudianteExComplexivo.class);
    }    
    
}
