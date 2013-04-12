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
import com.smj.hc2013.model.Salg;
import com.smj.hc2013.model.Selgere;
import com.smj.hc2013.model.SelskapKunde;
import com.smj.hc2013.model.Selskaper;
import com.smj.hc2013.session.BostedFacade;
import com.smj.hc2013.session.BrukerFacade;
import com.smj.hc2013.session.KundeFacade;
import com.smj.hc2013.session.RolleFacade;
import com.smj.hc2013.session.SalgFacade;
import com.smj.hc2013.session.SelgereFacade;
import com.smj.hc2013.session.SelskapKundeFacade;
import com.smj.hc2013.session.SelskaperFacade;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author deb
 */
@ManagedBean(name = "registrering")
@RequestScoped
public class Registrering implements Serializable {

    @EJB
    private BostedFacade bostedFacade;
    @EJB
    private RolleFacade rolleFacade;
    @EJB
    private BrukerFacade brukerFacade;
    @EJB
    private KundeFacade kundeFacade;
    @EJB
    private SelskapKundeFacade selskapKundeFacade;
    @EJB
    private SelskaperFacade selskaperFacade;
    @EJB
    private SelgereFacade SelgereFacade;
    @EJB
    private SalgFacade SalgFacade;
    private Bruker bruker = new Bruker();
    private Rolle rolle = new Rolle();
    private Bosted bosted = new Bosted();
    private Kunde kunde = new Kunde();
    private Selskaper selskaper = new Selskaper();
    private SelskapKunde selskapKunde = new SelskapKunde();
    private Salg salg = new Salg();
    private Selgere selgere = new Selgere();
    private boolean skip;
    private static final Logger logger = Logger.getLogger(Registrering.class.getName());

    private void prepareCreate() {

        bruker = new Bruker();
        rolle = new Rolle();
        bosted = new Bosted();
        kunde = new Kunde();
        selskaper = new Selskaper();
        selskapKunde = new SelskapKunde();
        salg = new Salg();
        selgere = new Selgere();

    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Selskaper getSelskaper() {
        return selskaper;
    }

    public void setSelskaper(Selskaper selskaper) {
        this.selskaper = selskaper;
    }

    public SelskapKunde getSelskapKunde() {
        return selskapKunde;
    }

    public void setSelskapKunde(SelskapKunde selskapKunde) {
        this.selskapKunde = selskapKunde;
    }

    public Salg getSalg() {
        return salg;
    }

    public void setSalg(Salg salg) {
        this.salg = salg;
    }

    public Selgere getSelgere() {
        return selgere;
    }

    public void setSelgere(Selgere selgere) {
        this.selgere = selgere;
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

    private boolean getBostedFinsIkke() {
        for (Bosted b : bostedFacade.findAll()) {
            if (b.getPostnummer().intValue() == bosted.getPostnummer().intValue()) {
                return false;
            }
        }
        return true;

    }

    public void save() {

        try {
        
        if (getBostedFinsIkke()) {
            bostedFacade.create(bosted);
        }
        bruker.setPostnummer(bosted.getPostnummer());
        brukerFacade.create(bruker);
        rolle.setBrukernavn(bruker.getBrukernavn());
        rolle.setRollen("customer");
        rolleFacade.create(rolle);
        kunde.setBrukernavn(bruker.getBrukernavn());
        kunde.setAvslag(0);
        kundeFacade.create(kunde);
        selskaperFacade.create(selskaper);
        for (Selskaper s : selskaperFacade.findAll()) {
            if (s.getBrId().equalsIgnoreCase(selskaper.getBrId())) {
                selskaper = s;
                selskapKunde = new SelskapKunde(bruker.getBrukernavn(), selskaper.getSelskapnr());
                selskapKundeFacade.create(selskapKunde);
            }
        }


        JsfUtil.addMessage("Welcome :" + bruker.getFornavn());
        prepareCreate();
    }
    catch (Exception e

    
        ) {
            JsfUtil.addMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + "This could be double registration or faulty registration inputs! Check your inputs");

    }
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
