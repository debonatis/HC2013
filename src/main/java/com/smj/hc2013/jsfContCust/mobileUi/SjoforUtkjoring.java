/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust.mobileUi;

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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author deb
 */
@ManagedBean
@ViewScoped
public class SjoforUtkjoring implements DataTableInt {

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

    @Override
    public OrdreUtkjoring getSetter() {
        return setter;
    }

    @Override
    public void setSetter(OrdreUtkjoring setter) {
        this.setter = setter;
    }

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
            if(ot.getStatus().equalsIgnoreCase("OK")){
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

           for(Utkjoring ut: utkjoringL){
               if((ot.getOrdretabellPK().getSalgsnummer().equalsIgnoreCase(ut.getUtkjoringPK().getSalgsnummer())) && (ut.getUtkorinKogstatus().equalsIgnoreCase("Pending"))){
                   utkjoring = ut;
                   setter.setUtkojring(utkjoring);
                   utListe.add(setter);
               }
           }
            
            }
        }

    }

    @Override
    public List<OrdreUtkjoring> getUtListe() {        
        return utListe;
    }

    @Override
    public void setUtListe(List<OrdreUtkjoring> utListe) {
        this.utListe = utListe;
    }

    @Override
    public OrdreUtkjoring getSelected() {
        return selected;
    }

    @Override
    public void setSelected(OrdreUtkjoring selected) {
        this.selected = selected;
    }

    @Override
    public void save() {

        brukerFacade.edit(selected.getBruker());
        ordreFacade.edit(selected.getOrdre());
        ordretabellFacade.edit(selected.getOrdreTabell());
        retterFacade.edit(selected.getRett());
        
        init();



    }
}
