/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

/**
 *
 * @author deb
 */
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
import com.smj.hc2013.session.SalgFacade;
import com.smj.hc2013.session.UtkjoringFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@RequestScoped
public class Charts implements Serializable {

    private CartesianChartModel linearModelAll;
    private CartesianChartModel chartModelUser;
    private List<OrdreUtkjoring> utListe = new ArrayList<>();
    private List<Retter> retterL;
    private List<Ordre> ordreL;
    private List<Ordretabell> ordreTabellL;
    private List<Bruker> brukerL;
    private List<Utkjoring> utkjoringL;
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
    @EJB
    private SalgFacade salgFacade;
    private Ordre ordre = new Ordre();
    private Ordretabell ordreT = new Ordretabell();
    private Bruker bruker = new Bruker();
    private Retter rett = new Retter();
    private Utkjoring utkjoring = new Utkjoring();
    private BrukerBehandling brukersjekk = new BrukerBehandling();
    private Date til;
    private Date fra;
    private Date tilU;
    private Date fraU;
   

    public Date getTilU() {
        return tilU;
    }

    public void setTilU(Date tilU) {
        this.tilU = tilU;
    }

    public Date getFraU() {
        return fraU;
    }

    public void setFraU(Date fraU) {
        this.fraU = fraU;
    }

   @PostConstruct
    private void init() {
        fra = new Date(2000, 1, 1);
        til = new Date(System.currentTimeMillis());
    }

    public Date getTil() {
        return til;
    }

    public void setTil(Date til) {
        this.til = til;
    }

    public Date getFra() {
        return fra;
    }

    public void setFra(Date fra) {
        this.fra = fra;
    }

    public CartesianChartModel getLinearModelAll() {
        createLinearModel1();
        return linearModelAll;
    }

    public void setLinearModelAll(CartesianChartModel linearModelAll) {
        this.linearModelAll = linearModelAll;
    }

    public CartesianChartModel getChartModelUser() {
        createLinearModel2();
        return chartModelUser;
    }

    public void setChartModelUser(CartesianChartModel chartModelUser) {
        this.chartModelUser = chartModelUser;
    }

    

    private void createLinearModel1() {
        linearModelAll = new CartesianChartModel();

        LineChartSeries salg = new LineChartSeries();
        salg.setLabel("Sum slag per dato");

        for (Ordretabell ot : ordretabellFacade.findAll()) {
            if (ot.getLevDato().after(fra) && ot.getLevDato().before(til) && ot.getStatus().equalsIgnoreCase("ok") && ot.getOrdretabellPK().getSelgerbrukernavn().equalsIgnoreCase(brukersjekk.getUserData())) {
                salg.set(ot.getLevDato(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
            }
        }


        LineChartSeries totsalg = new LineChartSeries();
        totsalg.setLabel("Totals sales");
        totsalg.setMarkerStyle("diamond");


        for (Ordretabell ot : ordretabellFacade.findAll()) {
            salg.set(ot.getLevDato(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
        }

        LineChartSeries totsalgCustomer = new LineChartSeries();
        totsalgCustomer.setLabel("Totals sales on Customer");
        totsalgCustomer.setMarkerStyle("diamond");


        for (Ordretabell ot : ordretabellFacade.findAll()) {
            salg.set(ot.getOrdretabellPK().getKundebrukernavn(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
        }




        linearModelAll.addSeries(salg);
        linearModelAll.addSeries(totsalg);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void createLinearModel2() {
        chartModelUser = new CartesianChartModel();

        for (Bruker b : brukerFacade.findAll()) {
            ChartSeries salg = new ChartSeries();
            salg.setLabel("Sales ");
            for (Ordretabell ot : ordretabellFacade.findAll()) {
                if (ot.getLevDato().after(fra) && ot.getLevDato().before(til) && ot.getStatus().equalsIgnoreCase("ok") && ot.getOrdretabellPK().getSelgerbrukernavn().equalsIgnoreCase(b.getBrukernavn())) {
                    salg.set(ot.getLevDato(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
                }
            }
            chartModelUser.addSeries(salg);
        }
















        

    }
}
