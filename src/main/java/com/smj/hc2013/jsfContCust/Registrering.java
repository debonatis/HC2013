/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.jsfContCust.Interface.RegMethod;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 * Getter and setters are not described
 * @author deb
 */
@ManagedBean(name = "registrering")
@SessionScoped
public class Registrering implements RegMethod {

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

    /**
     *
     * @return
     */
    @Override
    public Kunde getKunde() {
        return kunde;
    }

    /**
     *
     * @param kunde
     */
    @Override
    public void setKunde(Object kunde) {
        this.kunde = (Kunde) kunde;
    }

    /**
     *
     * @return
     */
    @Override
    public Selskaper getSelskaper() {
        return selskaper;
    }

    /**
     *
     * @param selskaper
     */
    @Override
    public void setSelskaper(Object selskaper) {
        this.selskaper = (Selskaper) selskaper;
    }

    /**
     *
     * @return
     */
    @Override
    public SelskapKunde getSelskapKunde() {
        return selskapKunde;
    }

    /**
     *
     * @param selskapKunde
     */
    @Override
    public void setSelskapKunde(Object selskapKunde) {
        this.selskapKunde = (SelskapKunde) selskapKunde;
    }

    /**
     *
     * @return
     */
    @Override
    public Salg getSalg() {
        return salg;
    }

    /**
     *
     * @param salg
     */
    @Override
    public void setSalg(Object salg) {
        this.salg = (Salg) salg;
    }

    /**
     *
     * @return
     */
    @Override
    public Selgere getSelgere() {
        return selgere;
    }

    /**
     *
     * @param selgere
     */
    @Override
    public void setSelgere(Object selgere) {
        this.selgere = (Selgere) selgere;
    }

    /**
     *
     * @return
     */
    @Override
    public Bruker getBruker() {
        return bruker;
    }

    /**
     *
     * @param bruker
     */
    @Override
    public void setBruker(Object bruker) {
        this.bruker = (Bruker) bruker;
    }

    /**
     *
     * @return
     */
    @Override
    public Rolle getRolle() {
        return rolle;
    }

    /**
     *
     * @param rolle
     */
    @Override
    public void setRolle(Object rolle) {
        this.rolle = (Rolle) rolle;
    }

    /**
     *
     * @return
     */
    @Override
    public Bosted getBosted() {
        return bosted;
    }

    /**
     *
     * @param bosted
     */
    @Override
    public void setBosted(Object bosted) {
        this.bosted = (Bosted) bosted;
    }

    private boolean getBostedFinsIkke() {
        for (Bosted b : bostedFacade.findAll()) {
            if (b.getPostnummer().intValue() == bosted.getPostnummer().intValue()) {
                return false;
            }
        }
        return true;

    }

    /**
     * percist user data in database
     */
    @Override
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
            if (!selskaper.getBrId().isEmpty()) {
                selskaperFacade.edit(selskaper);
            
            for (Selskaper s : selskaperFacade.findAll()) {
                if (s.getBrId().equalsIgnoreCase(selskaper.getBrId())) {
                    selskaper = s;
                    selskapKunde = new SelskapKunde(bruker.getBrukernavn(), selskaper.getSelskapnr());
                    selskapKundeFacade.create(selskapKunde);
                }
            }
            }


           FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("You are registerd");
        msg.setDetail("Welcome: " + bruker.getFornavn());

        FacesContext.getCurrentInstance().addMessage(null, msg);
            prepareCreate();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("You are not registerd");
        msg.setDetail("Maybe faulty inputs?");

        FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isSkip() {
        return skip;
    }

    /**
     *
     * @param skip
     */
    @Override
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     * 
     * @param event
     * @return returns given wisard step
     */
    @Override
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
