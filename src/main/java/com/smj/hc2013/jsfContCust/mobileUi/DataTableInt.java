/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust.mobileUi;

import com.smj.hc2013.model.OrdreUtkjoring;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author deb
 */
public interface DataTableInt {

    OrdreUtkjoring getSelected();

    OrdreUtkjoring getSetter();

    List<OrdreUtkjoring> getUtListe();

    @PostConstruct
    void init();

    void save();

    void setSelected(OrdreUtkjoring selected);

    void setSetter(OrdreUtkjoring setter);

    void setUtListe(List<OrdreUtkjoring> utListe);
    
}
