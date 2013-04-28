/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust.Interface;

import com.smj.hc2013.model.OrdreUtkjoring;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 * Inteface made for easy beanclass configuring
 * @author deb
 */
public interface DataTableInt {

    /**
     *
     * @return
     */
    OrdreUtkjoring getSelected();

    /**
     *
     * @return
     */
    OrdreUtkjoring getSetter();

    /**
     *
     * @return
     */
    List<OrdreUtkjoring> getUtListe();

    /**
     *
     */
    @PostConstruct
    void init();

    /**
     *
     */
    void save();

    /**
     *
     * @param selected
     */
    void setSelected(OrdreUtkjoring selected);

    /**
     *
     * @param setter
     */
    void setSetter(OrdreUtkjoring setter);

    /**
     *
     * @param utListe
     */
    void setUtListe(List<OrdreUtkjoring> utListe);
    
}
