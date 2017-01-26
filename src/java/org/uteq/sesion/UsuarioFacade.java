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
import org.uteq.model.Usuario;

/**
 *
 * @author Moises
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "ActasGrado-2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario findUserPwd(String user, String pwd) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("usuario", user);
        parameters.put("password", pwd);
        return super.findResultado(Usuario.FIND_BY_USER_PWD, parameters);
    }

}
