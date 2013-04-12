/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.model.Ordre;
import com.smj.hc2013.model.OrdreBestilling;
import com.smj.hc2013.model.Ordretabell;
import com.smj.hc2013.model.Retter;
import com.smj.hc2013.session.OrdretabellFacade;
import com.smj.hc2013.session.RetterFacade;
import com.smj.hc2013.session.SalgFacade;
import com.smj.hc2013.session.SelgereFacade;
import com.smj.hc2013.session.SelskapKundeFacade;
import com.smj.hc2013.session.SelskaperFacade;
import com.smj.hc2013.session.SjoforerFacade;
import com.smj.hc2013.session.UtkjoringFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author deb
 */
@ManagedBean
@SessionScoped
public class Bestilling implements Serializable {

    private List<Retter> retter;
    private List<Retter> maal;
    private List<OrdreBestilling> settAntallList;
    @EJB
    private RetterFacade retterFacade;
    @EJB
    private OrdretabellFacade ordretabellFacade;
    @EJB
    private OrdretabellFacade ordreFacade;
    @EJB
    private SalgFacade salgFacade;
    @EJB
    private SelgereFacade selgereFacade;
    @EJB
    private SelskaperFacade selskaperFacade;
    @EJB
    private SelskapKundeFacade selskapKuneFacade;
    @EJB
    private SjoforerFacade sjoforerFacade;
    @EJB
    private UtkjoringFacade utkjoringFacade;
    private Ordre ordre = new Ordre();
    private Ordretabell ordreT = new Ordretabell();
    private Retter selected = new Retter();
    private boolean skip;
    private static Logger logger = Logger.getLogger(Bestilling.class.getName());
    private DualListModel<Retter> retterPick;

    public Ordre getOrdre() {
        return ordre;
    }

    public void setOrdre(Ordre ordre) {
        this.ordre = ordre;
    }

    public List<OrdreBestilling> getSettAntallList() {
        settAntallList.clear();        
        return settAntallList;
    }

    public void setSettAntallList(List<OrdreBestilling> settAntallList) {
        this.settAntallList = settAntallList;
    }

    public List<Retter> getRetter() {
        return retter;
    }

    public void setRetter(List<Retter> retter) {
        this.retter = retter;
    }

    public List<Retter> getMaal() {
        return maal;
    }

    public void setMaal(List<Retter> maal) {
        this.maal = maal;
    }

    private void oppdaterRetterList() {
        retter = retterFacade.findAll();
    }

    @PostConstruct
    public void init() {
        oppdaterRetterList();
        maal = new ArrayList<Retter>();
        retterPick = new DualListModel<Retter>(retter, maal);
        settAntallList = new LinkedList<OrdreBestilling>();
    }

    public DualListModel<Retter> getRetterPick() {
        return retterPick;
    }

    public void setRetterPick(DualListModel<Retter> retterPick) {
        this.retterPick = retterPick;
    }

    public void savePick() {


        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + BrukerBehandling.getUserData());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());

        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();        
        Integer nPK = 0;
        if(event.isAdd()){
        for (Object item : event.getItems()) {
            String PK = (String) item;
            Pattern p = Pattern.compile("-?\\d+");
            Matcher m = p.matcher(PK);
             for(Integer n;m.find();) {
               n = Integer.parseInt(m.group());
               nPK = n;
            }
            Retter rett = retterFacade.find(Integer.toString(nPK));
            builder.append("You picked: " + rett.getNavn()).append("<br />");
            settAntallList.add(new OrdreBestilling(rett, 0));
            
        }
        } else if (event.isRemove()){
            
            for (Object item : event.getItems()) {
            String PK = (String) item;
            Pattern p = Pattern.compile("-?\\d+");
            Matcher m = p.matcher(PK);
             for(Integer n;m.find();) {
               n = Integer.parseInt(m.group());
               nPK = n;
            }
            Retter rett = retterFacade.find(Integer.toString(nPK));
            builder.append("You removed: " + rett.getNavn()).append("<br />");
            settAntallList.remove(new OrdreBestilling(rett, 0));
            
        }
            
        }
        


        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public UUID getUUID() {
        UUID idOne = UUID.randomUUID();
        return idOne;
    }
}
