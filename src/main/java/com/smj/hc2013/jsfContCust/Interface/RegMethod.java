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
public interface RegMethod<T> extends Serializable {

    T getBosted();

    T getBruker();

    T getKunde();

    T getRolle();

    T getSalg();

    T getSelgere();

    T getSelskapKunde();

    T getSelskaper();

    boolean isSkip();

    String onFlowProcess(FlowEvent event);

    void save();

    void setBosted(T bosted);

    void setBruker(T bruker);

    void setKunde(T kunde);

    void setRolle(T rolle);

    void setSalg(T salg);

    void setSelgere(T selgere);

    void setSelskapKunde(T selskapKunde);

    void setSelskaper(T selskaper);

    void setSkip(boolean skip);
    
}
