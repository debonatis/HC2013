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
import com.smj.hc2013.model.Ordretabell;
import com.smj.hc2013.session.BrukerFacade;
import com.smj.hc2013.session.OrdretabellFacade;
import com.smj.hc2013.session.SalgFacade;
import java.io.Serializable;
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

@ManagedBean
@RequestScoped
public class Charts implements Serializable {

    private CartesianChartModel categoryModel;
    private CartesianChartModel linearModelAll;
    private CartesianChartModel chartModelUser;
    @EJB
    private OrdretabellFacade ordretabellFacade;
    @EJB
    private BrukerFacade brukerFacade;
    @EJB
    private SalgFacade salgFacade;
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
        createLinearModel2();
        createLinearModel1();
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

        return linearModelAll;
    }

    public void setLinearModelAll(CartesianChartModel linearModelAll) {
        this.linearModelAll = linearModelAll;
    }

    public CartesianChartModel getChartModelUser() {

        return chartModelUser;
    }

    public void setChartModelUser(CartesianChartModel chartModelUser) {
        this.chartModelUser = chartModelUser;
    }

    private void createLinearModel1() {
        linearModelAll = new CartesianChartModel();
        List<Ordretabell> t = ordretabellFacade.findAll();
        ChartSeries salg = new ChartSeries();
        salg.setLabel("Sum slag per dato");
        for (Ordretabell ot : t) {
            if (!(ot.getLevDato() == null)) {
                if (ot.getLevDato().after(fra) && ot.getLevDato().before(til) && ot.getStatus().equalsIgnoreCase("ok") && ot.getOrdretabellPK().getSelgerbrukernavn().equalsIgnoreCase(brukersjekk.getUserData())) {
                    salg.set(ot.getLevDato(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
                }
            }
        }
        ChartSeries totsalg = new ChartSeries();
        totsalg.setLabel("Totals sales");
        for (Ordretabell ot : t) {
            if (!(ot.getLevDato() == null)) {
                totsalg.set(ot.getLevDato(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
            }
        }
        ChartSeries totsalgCustomer = new ChartSeries();
        totsalgCustomer.setLabel("Totals sales on Customer");
        for (Ordretabell ot : t) {
            totsalgCustomer.set(ot.getOrdretabellPK().getKundebrukernavn(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
        }
        linearModelAll.addSeries(salg);
        linearModelAll.addSeries(totsalg);
        linearModelAll.addSeries(totsalgCustomer);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void createLinearModel2() {
        chartModelUser = new CartesianChartModel();
        List<Bruker> br = brukerFacade.findAll();
        List<Ordretabell> t = ordretabellFacade.findAll();

        for (Bruker b : br) {
            ChartSeries salg = new ChartSeries();
            salg.setLabel("Sales on " + b.getBrukernavn());
            for (Ordretabell ot : t) {
                if (!(ot.getLevDato() == null)) {
                    if (ot.getLevDato().after(fra) && ot.getLevDato().before(til) && ot.getStatus().equalsIgnoreCase("ok") && ot.getOrdretabellPK().getSelgerbrukernavn().equalsIgnoreCase(b.getBrukernavn())) {
                        salg.set(ot.getLevDato(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
                    }
                }
            }
            chartModelUser.addSeries(salg);
        }
    }

    public CartesianChartModel getCategoryModel() {
        createCategoryModel();
        return categoryModel;
    }

    private void createCategoryModel() {
        categoryModel = new CartesianChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");

        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");

        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        categoryModel.addSeries(boys);
        categoryModel.addSeries(girls);
    }
}
