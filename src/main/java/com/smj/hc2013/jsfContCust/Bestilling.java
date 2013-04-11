/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.model.Ordre;
import com.smj.hc2013.model.OrdreBestilling;
import com.smj.hc2013.model.OrdrePK;
import com.smj.hc2013.model.Ordretabell;
import com.smj.hc2013.model.OrdretabellPK;
import com.smj.hc2013.model.Retter;
import com.smj.hc2013.session.OrdretabellFacade;
import com.smj.hc2013.session.RetterFacade;
import com.smj.hc2013.session.SalgFacade;
import com.smj.hc2013.session.SelgereFacade;
import com.smj.hc2013.session.SelskapKundeFacade;
import com.smj.hc2013.session.SelskaperFacade;
import com.smj.hc2013.session.SjoforerFacade;
import com.smj.hc2013.session.UtkjoringFacade;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
    private Ordre ordre;
    private OrdrePK OrdrePK;
    private Ordretabell ordreT;
    private OrdretabellPK ordreTId;
    private Retter selected;
    private boolean skip;
    private static Logger logger = Logger.getLogger(Bestilling.class.getName());
    private DualListModel<Retter> retterPick;

    public Ordre getOrdre() {
        return ordre;
    }

    public void setOrdre(Ordre ordre) {
        this.ordre = ordre;
    }

    public OrdrePK getOrdrePK() {
        return OrdrePK;
    }

    public void setOrdrePK(OrdrePK OrdrePK) {
        this.OrdrePK = OrdrePK;
    }

    public List<OrdreBestilling> getSettAntallList() {
        settAntallList.clear();
        for (Retter r : maal) {
            settAntallList.add(new OrdreBestilling(r, 0));
        }
        return settAntallList;
    }

    public void setSettAntallList(List<OrdreBestilling> settAntallList) {
        this.settAntallList = settAntallList;
    }

    private void oppdaterRetterList() {
        retter = retterFacade.findAll();
    }

    public Bestilling() {
        ordreT = new Ordretabell();
        ordreTId = new OrdretabellPK();
        selected = new Retter();
        ordre = new Ordre();

    }

    public DualListModel<Retter> getRetterPick() {
        oppdaterRetterList();
        maal = new ArrayList<Retter>();
        retterPick = new DualListModel<Retter>(retter, maal);
        return retterPick;
    }

    public void setRetterPick(DualListModel<Retter> retterPick) {
        this.retterPick = retterPick;
    }

    public void save(ActionEvent actionEvent) {


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
        for (Object item : event.getItems()) {
            builder.append(((Retter) item).getNavn()).append("<br />");
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
