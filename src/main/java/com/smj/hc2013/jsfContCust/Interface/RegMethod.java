/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust.Interface;

import com.smj.hc2013.model.Bosted;
import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.model.Kunde;
import com.smj.hc2013.model.Rolle;
import com.smj.hc2013.model.Salg;
import com.smj.hc2013.model.Selgere;
import com.smj.hc2013.model.SelskapKunde;
import com.smj.hc2013.model.Selskaper;
import java.io.Serializable;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author deb
 */
public interface RegMethod extends Serializable {

    Bosted getBosted();

    Bruker getBruker();

    Kunde getKunde();

    Rolle getRolle();

    Salg getSalg();

    Selgere getSelgere();

    SelskapKunde getSelskapKunde();

    Selskaper getSelskaper();

    boolean isSkip();

    String onFlowProcess(FlowEvent event);

    void save();

    void setBosted(Bosted bosted);

    void setBruker(Bruker bruker);

    void setKunde(Kunde kunde);

    void setRolle(Rolle rolle);

    void setSalg(Salg salg);

    void setSelgere(Selgere selgere);

    void setSelskapKunde(SelskapKunde selskapKunde);

    void setSelskaper(Selskaper selskaper);

    void setSkip(boolean skip);
    
}
