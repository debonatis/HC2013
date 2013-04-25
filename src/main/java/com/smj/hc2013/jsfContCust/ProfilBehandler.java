/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.jsfContCust.Interface.wisard;
import com.smj.hc2013.jsfContl.util.JsfUtil;
import com.smj.hc2013.model.Administrator;
import com.smj.hc2013.model.Annet;
import com.smj.hc2013.model.Bosted;
import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.model.Kunde;
import com.smj.hc2013.model.Rolle;
import com.smj.hc2013.model.Selgere;
import com.smj.hc2013.model.SelskapKunde;
import com.smj.hc2013.model.Selskaper;
import com.smj.hc2013.model.Sjoforer;
import com.smj.hc2013.session.AdministratorFacade;
import com.smj.hc2013.session.AnnetFacade;
import com.smj.hc2013.session.BostedFacade;
import com.smj.hc2013.session.BrukerFacade;
import com.smj.hc2013.session.KundeFacade;
import com.smj.hc2013.session.RolleFacade;
import com.smj.hc2013.session.SelgereFacade;
import com.smj.hc2013.session.SelskapKundeFacade;
import com.smj.hc2013.session.SelskaperFacade;
import com.smj.hc2013.session.SjoforerFacade;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author deb
 */
@ManagedBean
@ViewScoped
public class ProfilBehandler extends BrukerBehandling implements wisard {

    private boolean skip;
    private static final Logger logger = Logger.getLogger(ProfilBehandler.class.getName());
    @EJB
    private BostedFacade bostedFacade;
    @EJB
    private RolleFacade rolleFacade;
    @EJB
    private BrukerFacade brukerFacade;
    @EJB
    private SelskaperFacade selskaperFacade;
    @EJB
    private KundeFacade kundeFacade;
    @EJB
    private AnnetFacade annetFacade;
    @EJB
    private SjoforerFacade sjoFacade;
    @EJB
    private AdministratorFacade adminFacade;
    @EJB
    private SelgereFacade selgerFacade;
    @EJB
    private SelskapKundeFacade selskapKundeFacade;
    private Bruker bruker = new Bruker();
    private Rolle rollen = new Rolle();
    private Bosted bosted = new Bosted();
    private Kunde kunde = new Kunde();
    private Selgere selgere = new Selgere();
    private Annet annet = new Annet();
    private Sjoforer sjofor = new Sjoforer();
    private Administrator admin = new Administrator();
    private Selskaper selskaper = new Selskaper();
    private SelskapKunde selskapKunde = new SelskapKunde();
    private String type = "";

    public Bruker getBruker() {
        return bruker;
    }

    public void setBruker(Bruker bruker) {
        this.bruker = bruker;
    }

    public Rolle getRollen() {
        return rollen;
    }

    public void setRollen(Rolle rolle) {
        this.rollen = rolle;
    }

    public Bosted getBosted() {
        return bosted;
    }

    public void setBosted(Bosted bosted) {
        this.bosted = bosted;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Selgere getSelgere() {
        return selgere;
    }

    public void setSelgere(Selgere selgere) {
        this.selgere = selgere;
    }

    public Annet getAnnet() {
        return annet;
    }

    public void setAnnet(Annet annet) {
        this.annet = annet;
    }

    public Sjoforer getSjofor() {
        return sjofor;
    }

    public void setSjofor(Sjoforer sjofor) {
        this.sjofor = sjofor;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    @Override
    public Object getUser() {
        return bruker;
    }

    @Override
    public boolean isSkip() {
        return skip;
    }

    @Override
    public String onFlowProcess(FlowEvent event) {
        logger.log(Level.INFO, "Current wizard step:{0}", event.getOldStep());
        logger.log(Level.INFO, "Next step:{0}", event.getNewStep());

        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    @PostConstruct
    public void init() throws IOException {
        String brukernavn = getUserData();
        bruker = brukerFacade.find(brukernavn);
        bosted = bostedFacade.find(bruker.getPostnummer());
        String rolle = getRolle();
        switch (rolle) {
            case "kokk":
                annet = annetFacade.find(bruker.getBrukernavn());
                type = rolle;
                break;
            case "admin":
                admin = adminFacade.find(bruker.getBrukernavn());
                type = rolle;
                break;
            case "bruker":
                annet = annetFacade.find(bruker.getBrukernavn());
                type = rolle;
                break;
            case "customer":
                kunde = kundeFacade.find(bruker.getBrukernavn());
                type = rolle;
                break;
            case "salg":
                selgere = selgerFacade.find(bruker.getBrukernavn());
                type = rolle;
                break;
            case "sjofor":
                sjofor = sjoFacade.find(bruker.getBrukernavn());
                type = rolle;
                break;
            default:
                logout();
                break;
        }

    }

    private boolean getBostedFinsIkke() {
        for (Bosted b : bostedFacade.findAll()) {
            if (b.getPostnummer().intValue() == bosted.getPostnummer().intValue()) {
                return false;
            }
        }
        return true;

    }

    @Override
    public void save(ActionEvent actionEvent) {
        try {

            if (getBostedFinsIkke()) {
                bostedFacade.create(bosted);
            }
            bruker.setPostnummer(bosted.getPostnummer());
            brukerFacade.edit(bruker);
            if (!(selskaper.getBrId() == null)) {
                selskaperFacade.edit(selskaper);
                selskapKunde = new SelskapKunde(bruker.getBrukernavn(), selskaper.getSelskapnr());
                selskapKundeFacade.edit(selskapKunde);

            }

            JsfUtil.addMessage("You have successfully edited your profile, " + bruker.getFornavn());
            prepareCreate();
        } catch (Exception e) {
            JsfUtil.addMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + "This could be due to double registration or faulty registration inputs! Check your inputs");

        }
    }

    @Override
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    @Override
    public void setUser(Object user) {
        this.bruker = (Bruker) user;
    }

    private void prepareCreate() {

        bruker = new Bruker();
        bosted = new Bosted();
        selskaper = new Selskaper();
        selskapKunde = new SelskapKunde();
        selgere = new Selgere();

    }
}
