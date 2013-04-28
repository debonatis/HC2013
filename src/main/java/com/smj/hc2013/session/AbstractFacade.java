/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.session;

import com.smj.hc2013.varsling.Mottaker;
import com.smj.hc2013.varsling.Varsler;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @param <T> 
 * @author deb
 */
public abstract class AbstractFacade<T> implements Varsler {

    private Class<T> entityClass;
    private static List<Mottaker> MOTTAKERE;

    /**
     * On init the subclasses set their object type
     * @param entityClass
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     *
     * @param o
     */
    @Override
    public void registerVarsler(Mottaker o) {
        MOTTAKERE.add(o);
    }

    /**
     *
     * @param o
     */
    @Override
    public void removeVarsler(Mottaker o) {
        MOTTAKERE.remove(o);
    }

    /**
     *
     * @param entity
     * @param entityName
     */
    @Override
    public void notifyVarslerMottaker(Object entity, String entityName) {
        for (Mottaker m : MOTTAKERE) {
            m.update(entity, entityName);
        }
    }

    /**
     *
     * @return
     */
    protected abstract EntityManager getEntityManager();

    /**
     * Create the desired object in Database
     * @param entity
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     *Makes an edit on the desired Object
     * @param entity
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Removes a desired Object in Database
     * @param entity
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Find an object in database with the help of primaryKey
     * @param id
     * @return
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Returns a list containg the hole table
     * @return
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Retuns desired list of  items from a Database table
     * @param range
     * @return
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * Items count in Database table
     * @return
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
