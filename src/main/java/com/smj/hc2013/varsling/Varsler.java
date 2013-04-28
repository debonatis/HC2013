/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.varsling;

/**
 *
 * @author deb
 */
public interface Varsler {

    /**
     *
     * @param o
     */
    public void registerVarsler(Mottaker o);

    /**
     *
     * @param o
     */
    public void removeVarsler(Mottaker o);

    /**
     *
     * @param entity
     * @param entityName
     */
    public void notifyVarslerMottaker(Object entity, String entityName);
}
