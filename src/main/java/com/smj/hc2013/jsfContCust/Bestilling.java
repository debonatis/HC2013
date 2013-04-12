/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.jsfContl.util.JsfUtil;
import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.model.Ordre;
import com.smj.hc2013.model.OrdreBestilling;
import com.smj.hc2013.model.Ordretabell;
import com.smj.hc2013.model.Retter;
import com.smj.hc2013.model.Salg;
import com.smj.hc2013.model.Selgere;
import com.smj.hc2013.model.SelskapKunde;
import com.smj.hc2013.model.Selskaper;
import com.smj.hc2013.model.Utkjoring;
import com.smj.hc2013.session.KundeFacade;
import com.smj.hc2013.session.OrdreFacade;
import com.smj.hc2013.session.OrdretabellFacade;
import com.smj.hc2013.session.RetterFacade;
import com.smj.hc2013.session.SalgFacade;
import com.smj.hc2013.session.SelgereFacade;
import com.smj.hc2013.session.SelskapKundeFacade;
import com.smj.hc2013.session.SelskaperFacade;
import com.smj.hc2013.session.UtkjoringFacade;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

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
    private OrdreFacade ordreFacade;
    @EJB
    private SalgFacade salgFacade;
    @EJB
    private SelgereFacade selgereFacade;
    @EJB
    private SelskaperFacade selskaperFacade;
    @EJB
    private SelskapKundeFacade selskapKuneFacade;
    @EJB
    private UtkjoringFacade utkjoringFacade;
    @EJB
    private KundeFacade kundeFacade;
    private Ordre ordre = new Ordre();
    private Ordretabell ordreT = new Ordretabell();
    private Salg salg;
    private Selgere selgere;
    private Selskaper selskaper;
    private SelskapKunde selskapKunde;
    private Utkjoring utkjoring;
    private boolean skip;
    private static Logger logger = Logger.getLogger(Bestilling.class.getName());
    private DualListModel<Retter> retterPick;
    private StreamedContent file;
    private final static String FILNAVN = "Oversikt.pdf";
    private boolean MailVe = false;

    public Bestilling() {
        selskaper = new Selskaper();
        selskapKunde = new SelskapKunde();
        salg = new Salg();
        selgere = new Selgere();
        utkjoring = new Utkjoring();
        ordre = new Ordre();
        ordreT = new Ordretabell();

    }

    public Ordre getOrdre() {
        return ordre;
    }

    public void setOrdre(Ordre ordre) {
        this.ordre = ordre;
    }

    public List<OrdreBestilling> getSettAntallList() {
        return settAntallList;
    }

    public String[] getBridList() {

        List<Selskaper> hjelp = selskaperFacade.findAll();

        String hjelp2[] = new String[hjelp.size() + 1];
        int i = 0;
        for (Selskaper e : hjelp) {
            hjelp2[i] = e.getBrId();
        }
        return hjelp2;
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

    private String getAvslagUni() {
        return "0";
    }

    public void savePick() throws Exception {
        
        try{
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        if ((selgereFacade.count() == 0) && (kundeFacade.count() == 0)) {
            return;
        }
        List<Selskaper> Lselskaper = selskaperFacade.findAll();

        for (OrdreBestilling ob : settAntallList) {
            salg.setSalgsnummer(getUUID().toString());
            salg.setSumSalg(getAvslagUni());
            salgFacade.create(salg);
            ordre = new Ordre("simonD", salg.getSalgsnummer());
            ordre.setLevAdresse(ob.getLeveringsAdresse());
            ordre.setDatoEndret(new Date(System.currentTimeMillis()));
            ordre.setBetaltstatus("Pending");
            for (Selskaper s : Lselskaper) {
                if (ob.getSelskap().equalsIgnoreCase(s.getBrId())) {
                    ordre.setSelskapnr(s.getSelskapnr());
                }
            }
            ordreFacade.create(ordre);
           Bruker bruker = (Bruker) externalContext.getSessionMap().get("user");
            ordreT = new Ordretabell("simonD", salg.getSalgsnummer(),bruker.getBrukernavn());
            ordreT.setStatus("Pending");
            ordreT.setRettnummer(ob.getRett().getRettnummer());
            ordreT.setAntall(ob.getAntall());
            ordretabellFacade.create(ordreT);

        }
        } catch (Exception e){
            JsfUtil.addSuccessMessage("Something is not right!");
        }



        JsfUtil.addSuccessMessage("You have succsessfully placed your order//s");
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public boolean isMailVe() {
        return MailVe;
    }

    public void setMailVe(boolean MailVe) {
        this.MailVe = MailVe;
    }

    public void removeFromsetAntalle(OrdreBestilling item) {
        settAntallList.remove(item);
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
        if (event.isAdd()) {
            for (Object item : event.getItems()) {
                String PK = (String) item;
                Pattern p = Pattern.compile("-?\\d+");
                Matcher m = p.matcher(PK);
                for (Integer n; m.find();) {
                    n = Integer.parseInt(m.group());
                    nPK = n;
                }
                Retter rett = retterFacade.find(Integer.toString(nPK));
                builder.append("You picked: " + rett.getNavn()).append("<br />");
                settAntallList.add(new OrdreBestilling(rett, 0));

            }
        } else if (event.isRemove()) {

            for (Object item : event.getItems()) {
                String PK = (String) item;
                Pattern p = Pattern.compile("-?\\d+");
                Matcher m = p.matcher(PK);
                for (Integer n; m.find();) {
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

    public StreamedContent getFile() {




        PdfMaker.makePdf(getSettAntallList(), FILNAVN, FacesContext.getCurrentInstance().getExternalContext().getRealPath("//bruker"));

        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/bruker/" + FILNAVN);
        file = new DefaultStreamedContent(stream, "image/jpg", "Oversikt.pdf");
        if (MailVe) {
            JavaMail mick = new JavaMail();

        }
        return file;
    }
}
