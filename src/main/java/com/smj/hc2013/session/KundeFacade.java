/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.session;

import com.smj.hc2013.model.Kunde;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author deb
 */
@Stateless
public class KundeFacade extends AbstractFacade<Kunde> {
    @PersistenceContext(unitName = "com.smj_HC2013_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KundeFacade() {
        super(Kunde.class);
    }
    
}
