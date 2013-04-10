/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.jsfContl.util.JsfUtil;
import com.smj.hc2013.model.Bosted;
import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.model.Kunde;
import com.smj.hc2013.model.Rolle;
import com.smj.hc2013.session.BostedFacade;
import com.smj.hc2013.session.BrukerFacade;
import com.smj.hc2013.session.KundeFacade;
import com.smj.hc2013.session.RolleFacade;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author deb
 */
@Named(value = "registrering")
@SessionScoped
public class Registrering implements Serializable {

    @EJB
    private BostedFacade bostedFacade;
    @EJB
    private RolleFacade rolleFacade;
    @EJB
    private BrukerFacade brukerFacade;
    @EJB
    private KundeFacade kundeFacade;
    private Bruker bruker;
    private Rolle rolle;
    private Bosted bosted;
    private Kunde kunde;
    private boolean skip;
    private static final Logger logger = Logger.getLogger(Registrering.class.getName());

    private void prepareCreate() {

        bruker = new Bruker();
        rolle = new Rolle();
        bosted = new Bosted();
        kunde = new Kunde();
    }

    public Bruker getBruker() {
        return bruker;
    }

    public void setBruker(Bruker bruker) {
        this.bruker = bruker;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    public Bosted getBosted() {
        return bosted;
    }

    public void setBosted(Bosted bosted) {
        this.bosted = bosted;
    }

    public void save() {

        bruker.setPostnummer(bosted.getPostnummer());
        bostedFacade.create(bosted);
        brukerFacade.create(bruker);
        rolle.setBrukernavn(bruker.getBrukernavn());
        rolle.setRollen("customer");
        rolleFacade.create(rolle);
        kunde.setBrukernavn(bruker.getBrukernavn());
        kunde.setAvslag(0);
        kundeFacade.create(kunde);





        JsfUtil.addMessage("Welcome :" + bruker.getFornavn());

        prepareCreate();
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        logger.log(Level.INFO, "Current wizard step:{0}", event.getOldStep());
        logger.log(Level.INFO, "Next step:{0}", event.getNewStep());

        if (skip) {
            skip = false;
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
}
