/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.jsfContCust.Interface.DataTableInt;
import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.model.Ordre;
import com.smj.hc2013.model.OrdreUtkjoring;
import com.smj.hc2013.model.Ordretabell;
import com.smj.hc2013.model.Retter;
import com.smj.hc2013.model.Utkjoring;
import com.smj.hc2013.session.BrukerFacade;
import com.smj.hc2013.session.OrdreFacade;
import com.smj.hc2013.session.OrdretabellFacade;
import com.smj.hc2013.session.RetterFacade;
import com.smj.hc2013.session.UtkjoringFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author deb
 */
@ManagedBean
@SessionScoped
public class OversiktKokk implements DataTableInt {

    private List<OrdreUtkjoring> utListe = new ArrayList<>();
    private List<Retter> retterL;
    private List<Ordre> ordreL;
    private List<Ordretabell> ordreTabellL;
    private List<Bruker> brukerL;
    private List<Utkjoring> utkjoringL;
    private OrdreUtkjoring selected = new OrdreUtkjoring();
    private OrdreUtkjoring setter = new OrdreUtkjoring();    
    @EJB
    private RetterFacade retterFacade;
    @EJB
    private OrdretabellFacade ordretabellFacade;
    @EJB
    private OrdreFacade ordreFacade;
    @EJB
    private BrukerFacade brukerFacade;
    @EJB
    private UtkjoringFacade utkjoringFacade;
    private Ordre ordre = new Ordre();
    private Ordretabell ordreT = new Ordretabell();
    private Bruker bruker = new Bruker();
    private Retter rett = new Retter();
    private Utkjoring utkjoring = new Utkjoring();
    private int bilnr = 0;
   
    
    

    /**
     *
     * @return
     */
    public int getBilnr() {
        return bilnr;
    }

    /**
     *
     * @param bilnr
     */
    public void setBilnr(int bilnr) {
        this.bilnr = bilnr;
    }

    /**
     *
     * @return
     */
    @Override
    public OrdreUtkjoring getSelected() {
        return selected;
    }

    /**
     *
     * @return
     */
    @Override
    public OrdreUtkjoring getSetter() {
        return setter;
    }

    /**
     *
     * @return
     */
    @Override
    public List<OrdreUtkjoring> getUtListe() {
        return utListe;
    }

    /**
     *
     */
    @PostConstruct
    @Override
    public void init() {
        utListe = new ArrayList<>();
        brukerL = brukerFacade.findAll();
        ordreL = ordreFacade.findAll();
        ordreTabellL = ordretabellFacade.findAll();
        retterL = retterFacade.findAll();
        utkjoringL = utkjoringFacade.findAll();

        for (Ordretabell ot : ordreTabellL) {
            if (ot.getStatus().equalsIgnoreCase("pending")) {
                ordreT = ot;
                for (Ordre o : ordreL) {
                    if (ot.getOrdretabellPK().getSalgsnummer().equalsIgnoreCase(o.getOrdrePK().getSalgsnummer())) {
                        ordre = o;
                    }
                }
                for (Bruker b : brukerL) {
                    if (ot.getOrdretabellPK().getKundebrukernavn().equalsIgnoreCase(b.getBrukernavn())) {
                        bruker = b;
                    }
                }
                for (Retter r : retterL) {
                    if (ot.getRettnummer().equalsIgnoreCase(r.getRettnummer())) {
                        rett = r;
                    }
                }

                setter = new OrdreUtkjoring();
                setter.setBruker(bruker);
                setter.setOrdre(ordre);
                setter.setOrdreTabell(ordreT);
                setter.setRett(rett);
                setter.setUtkojring(utkjoring);
                utListe.add(setter);



            }
        }


    }

    /**
     *
     */
    @Override
    public void save() {
         brukerFacade.edit(selected.getBruker());
         selected.getOrdre().setBekreftet(new Date(System.currentTimeMillis()));
        ordreFacade.edit(selected.getOrdre());
        ordretabellFacade.edit(selected.getOrdreTabell());
        retterFacade.edit(selected.getRett());
        utkjoring = new Utkjoring(selected.getBruker().getBrukernavn(), selected.getOrdre().getOrdrePK().getSalgsnummer(), 1, "simonD");
        utkjoring.setUtkorinKogstatus("Pending");
        utkjoringFacade.edit(utkjoring);
        
        
        init();
    }

    /**
     *
     * @param selected
     */
    @Override
    public void setSelected(OrdreUtkjoring selected) {
        this.selected = selected;
    }

    /**
     *
     * @param setter
     */
    @Override
    public void setSetter(OrdreUtkjoring setter) {
        this.setter = setter;
    }

    /**
     *
     * @param utListe
     */
    @Override
    public void setUtListe(List<OrdreUtkjoring> utListe) {
        this.utListe = utListe;
    }
}
