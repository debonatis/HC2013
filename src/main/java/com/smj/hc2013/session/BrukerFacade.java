/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.session;

import com.smj.hc2013.model.Bruker;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author deb
 */
@Stateless
public class BrukerFacade extends AbstractFacade<Bruker> {
    @PersistenceContext(unitName = "com.smj_HC2013_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BrukerFacade() {
        super(Bruker.class);
    }
    
}
