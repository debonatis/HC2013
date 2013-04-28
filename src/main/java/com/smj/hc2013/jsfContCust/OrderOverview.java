/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.model.OrdreUtkjoring;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *Getter and setters are not described
 * @author deb
 */
@ManagedBean
@ViewScoped
public class OrderOverview extends OversiktKokk {

    BrukerBehandling bruker = new BrukerBehandling();

    /**
     * returns list of users
     * @return list
     */
    @Override
    public List<OrdreUtkjoring> getUtListe() {
        List<OrdreUtkjoring> ordreL = new ArrayList<>();

        for (OrdreUtkjoring b : super.getUtListe()) {
            if ((b.getBruker().getBrukernavn().equalsIgnoreCase(bruker.getUserData()))) {
                ordreL.add(b);
            }
        }
        return ordreL;
    }
    
  
}
