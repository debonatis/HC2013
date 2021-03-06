/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.jsfContl.util.JavaMail;
import com.smj.hc2013.jsfContl.util.PdfMaker;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

/**
 *Getter and setters are not described
 * @author deb
 */
@ManagedBean
@ViewScoped
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

    /**
     * init of Entity Objects
     */
    public Bestilling() {
        selskaper = new Selskaper();
        selskapKunde = new SelskapKunde();
        salg = new Salg();
        selgere = new Selgere();
        utkjoring = new Utkjoring();
        ordre = new Ordre();
        ordreT = new Ordretabell();

    }

    /**
     *init of Entity Objects
     */
    public void prepareCreate() {
        selskaper = new Selskaper();
        selskapKunde = new SelskapKunde();
        salg = new Salg();
        selgere = new Selgere();
        utkjoring = new Utkjoring();
        ordre = new Ordre();
        ordreT = new Ordretabell();

    }

    /**
     *
     * @return Object selected
     */
    public Ordre getOrdre() {
        return ordre;
    }

    /**
     *
     * @param ordre
     */
    public void setOrdre(Ordre ordre) {
        this.ordre = ordre;
    }

    /**
     *
     * @return
     */
    public List<OrdreBestilling> getSettAntallList() {
        return settAntallList;
    }

    /**
     *
     * @return List of firms
     */
    public String[] getBridList() {

        List<Selskaper> hjelp = selskaperFacade.findAll();
        List<SelskapKunde> hjelp3 = selskapKuneFacade.findAll();
        BrukerBehandling brukerInfo = new BrukerBehandling();
        List<String> hjelp2 = new ArrayList<>();



        for (Selskaper e : hjelp) {
            for (SelskapKunde se : hjelp3) {
                if ((se.getSelskapKundePK().getBrukernavn().equalsIgnoreCase(brukerInfo.getUserData()))) {
                    e.setBrId(byttHvisNull(e.getBrId(), "?"));                    
                        hjelp2.add(e.getBrId());
                    
                }
            }
        }
        return hjelp2.toArray(new String[]{});
    }

    /**
     *
     * @param settAntallList
     */
    public void setSettAntallList(List<OrdreBestilling> settAntallList) {
        this.settAntallList = settAntallList;
    }

    /**
     *
     * @return
     */
    public List<Retter> getRetter() {
        return retter;
    }

    /**
     *
     * @param retter
     */
    public void setRetter(List<Retter> retter) {
        this.retter = retter;
    }

    /**
     *
     * @return
     */
    public List<Retter> getMaal() {
        return maal;
    }

    /**
     *
     * @param maal
     */
    public void setMaal(List<Retter> maal) {
        this.maal = maal;
    }

    private void oppdaterRetterList() {
        retter = retterFacade.findAll();
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        oppdaterRetterList();
        maal = new ArrayList<Retter>();
        retterPick = new DualListModel<Retter>(retter, maal);
        settAntallList = new LinkedList<OrdreBestilling>();
    }

    /**
     *
     * @return
     */
    public DualListModel<Retter> getRetterPick() {
        return retterPick;
    }

    /**
     *
     * @param retterPick
     */
    public void setRetterPick(DualListModel<Retter> retterPick) {
        this.retterPick = retterPick;
    }

    /**
     *
     * @param <T>
     * @param objectToCheck
     * @param defaultValue
     * @return
     */
    public static <T> T byttHvisNull(T objectToCheck, T defaultValue) {
        return objectToCheck == null ? defaultValue : objectToCheck;
    }

    private String getSumPris(int v, int b) {
        String hjelp = "";
        int x = v * b;
        hjelp = ((Integer) x).toString();
        return hjelp;
    }

    /**
     * saves the pick in Database
     * @throws Exception
     */
    public void savePick() throws Exception {
        int i = 0;
        try {

            if ((selgereFacade.count() == 0) && (kundeFacade.count() == 0)) {
                return;
            }
            List<Selskaper> Lselskaper = selskaperFacade.findAll();

            for (OrdreBestilling ob : settAntallList) {
                salg.setSalgsnummer(getUUID().toString());
                salg.setSumSalg(getSumPris(ob.getAntall(), ob.getRett().getPris()));
                salgFacade.create(salg);
                ordre = new Ordre("web", salg.getSalgsnummer());
                ordre.setLevAdresse(ob.getLeveringsAdresse());
                ordre.setDatoEndret(new Date(System.currentTimeMillis()));
                ordre.setBetaltstatus("Pending");
                if (!(ob.getSelskap() == null)) {
                    for (Selskaper s : Lselskaper) {
                        if (ob.getSelskap().equalsIgnoreCase(s.getBrId())) {
                            ordre.setSelskapnr(s.getSelskapnr());
                        }
                    }
                }
                ordreFacade.create(ordre);
                BrukerBehandling brukerInfo = new BrukerBehandling();
                ordreT = new Ordretabell("web", salg.getSalgsnummer(), brukerInfo.getUserData());
                ordreT.setStatus("Pending");
                ordreT.setRettnummer(ob.getRett().getRettnummer());
                ordreT.setAntall(ob.getAntall());
                ordreT.setLevDato(ob.getLevDato());
                ordretabellFacade.create(ordreT);

                i++;
                prepareCreate();

            }
        } catch (Exception e) {

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Not Transferred");
            msg.setDetail("Maybe som faulty inputs?");

            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

        selgere = selgereFacade.find("web");
        String antall = selgere.getAntSalg();
        int y = Integer.parseInt(antall);
        selgere.setAntSalgInt(y + i);
        selgereFacade.edit(selgere);
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail("You have ordered " + settAntallList.size() + "items ");

        FacesContext.getCurrentInstance().addMessage(null, msg);




    }

    /**
     *
     * @return
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     *
     * @param skip
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     *
     * @return
     */
    public boolean isMailVe() {
        return MailVe;
    }

    /**
     *
     * @param MailVe
     */
    public void setMailVe(boolean MailVe) {
        this.MailVe = MailVe;
    }

    /**
     * removes selected item
     * @param item
     */
    public void removeFromsetAntalle(OrdreBestilling item) {
        settAntallList.remove(item);
    }

    /**
     * Gives information to logger about wich step in wizard
     * @param event
     * @return
     */
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

    /**
     * updates the list on transer in picklist
     * @param event
     */
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

    /**
     * 
     * @return saleId
     */
    public UUID getUUID() {
        UUID idOne = UUID.randomUUID();
        return idOne;
    }

    /**
     * makes and send pdf contanig purchases
     * @return Pdf file in end result
     */
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
