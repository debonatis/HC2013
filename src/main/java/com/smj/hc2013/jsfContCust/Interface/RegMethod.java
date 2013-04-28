/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust.Interface;

import java.io.Serializable;
import org.primefaces.event.FlowEvent;

/**
 * Inteface made for easy beanclass configuring
 * @param <T> 
 * @author deb
 */
public interface RegMethod<T> extends Serializable {

    /**
     *
     * @return
     */
    T getBosted();

    /**
     *
     * @return
     */
    T getBruker();

    /**
     *
     * @return
     */
    T getKunde();

    /**
     *
     * @return
     */
    T getRolle();

    /**
     *
     * @return
     */
    T getSalg();

    /**
     *
     * @return
     */
    T getSelgere();

    /**
     *
     * @return
     */
    T getSelskapKunde();

    /**
     *
     * @return
     */
    T getSelskaper();

    /**
     *
     * @return
     */
    boolean isSkip();

    /**
     *
     * @param event
     * @return
     */
    String onFlowProcess(FlowEvent event);

    /**
     *
     */
    void save();

    /**
     *
     * @param bosted
     */
    void setBosted(T bosted);

    /**
     *
     * @param bruker
     */
    void setBruker(T bruker);

    /**
     *
     * @param kunde
     */
    void setKunde(T kunde);

    /**
     *
     * @param rolle
     */
    void setRolle(T rolle);

    /**
     *
     * @param salg
     */
    void setSalg(T salg);

    /**
     *
     * @param selgere
     */
    void setSelgere(T selgere);

    /**
     *
     * @param selskapKunde
     */
    void setSelskapKunde(T selskapKunde);

    /**
     *
     * @param selskaper
     */
    void setSelskaper(T selskaper);

    /**
     *
     * @param skip
     */
    void setSkip(boolean skip);
    
}
