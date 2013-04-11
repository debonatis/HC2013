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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author deb
 */
@ManagedBean(name = "registrering")
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
    @EJB
    private SelskapKundeFacade selskapKundeFacade;
    @EJB
    private SelskaperFacade selskaperFacade;
    @EJB
    private SelgereFacade SelgereFacade;
    @EJB
    private SalgFacade SalgFacade;
    private Bruker bruker;
    private Rolle rolle;
    private Bosted bosted;
    private Kunde kunde;
    private Selskaper selskaper;
    private SelskapKunde selskapKunde;
    private Salg salg;
    private Selgere selgere;
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

    public Registrering() {
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

    public void save() {

//        bruker.setPostnummer(bosted.getPostnummer());
//        bostedFacade.create(bosted);
//        brukerFacade.create(bruker);
//        rolle.setBrukernavn(bruker.getBrukernavn());
//        rolle.setRollen("customer");
//        rolleFacade.create(rolle);
//        kunde.setBrukernavn(bruker.getBrukernavn());
//        kunde.setAvslag(0);
//        kundeFacade.create(kunde);





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
