/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.session;

import com.smj.hc2013.model.Salg;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author deb
 */
@Stateless
public class SalgFacade extends AbstractFacade<Salg> {
    @PersistenceContext(unitName = "com.smj_HC2013_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public SalgFacade() {
        super(Salg.class);
    }
    
}
