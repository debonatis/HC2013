/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.model.Ordre;
import com.smj.hc2013.model.OrdreUtkjoring;
import com.smj.hc2013.model.Ordretabell;
import com.smj.hc2013.model.Retter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author deb
 */
@ManagedBean
@ViewScoped
public class OrderOverview extends OversiktKokk {

    BrukerBehandling bruker = new BrukerBehandling();

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
    
    public List<?> getUtListe2(){
        throw new NotImplementedException();
    }
}
