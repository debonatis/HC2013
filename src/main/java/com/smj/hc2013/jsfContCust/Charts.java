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
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@ViewScoped
public class Charts implements Serializable {

    private CartesianChartModel currentUser;
    private CartesianChartModel linearModelAll;
    private CartesianChartModel chartModelUser;
    private CartesianChartModel users;
    private CartesianChartModel web;
    private List<Ordretabell> t; 
    @EJB
    private OrdretabellFacade ordretabellFacade;
    @EJB
    private BrukerFacade brukerFacade;
    @EJB
    private SalgFacade salgFacade;
    private BrukerBehandling brukersjekk = new BrukerBehandling();
    private Date fra = new Date(100, 1, 1);
    private Date til = new Date(System.currentTimeMillis());

    
     
    
    

    public CartesianChartModel getWeb() {
        
        return web;
    }

    public void setWeb(CartesianChartModel web) {
        this.web = web;
    }
    

    public CartesianChartModel getCurrentUser() {
        
        return currentUser;
    }

    public void setCurrentUser(CartesianChartModel currentUser) {
        this.currentUser = currentUser;
    }

    public CartesianChartModel getUsers() {
       
        return users;
    }

    public void setUsers(CartesianChartModel users) {
        this.users = users;
    }
    
     @PostConstruct
    public void init() { 
         t = ordretabellFacade.findAll();
         t = Collections.synchronizedList(t);
         createCurrentUser();
         createLinearModel1();
         createLinearModel2();
         createUsers();
         createWebSales();
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
       

        LineChartSeries totsalg = new LineChartSeries();
        totsalg.setLabel("Totals sales per day");
        for (Ordretabell ot : t) {
            if (!(ot.getLevDato() == null)) {
                totsalg.set(ot.getLevDato().getDate(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
            }
        }
        linearModelAll.addSeries(totsalg);

    }

    private void createCurrentUser() {
        currentUser = new CartesianChartModel();
        
        LineChartSeries salg = new LineChartSeries();
        salg.setLabel("Totale sales per date on current salesman (kr)");
        for (Ordretabell ot : t) {
            if (!(ot.getLevDato() == null)) {
                if (ot.getLevDato().after(fra) && ot.getLevDato().before(til) && ot.getOrdretabellPK().getSelgerbrukernavn().equalsIgnoreCase(brukersjekk.getUserData())) {
                    salg.set(ot.getLevDato().getDate(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
                }
            }
        }
        currentUser.addSeries(salg);
    }

    private void createUsers() {
        users = new CartesianChartModel();
        
        ChartSeries totsalgperCustomer = new ChartSeries();
        totsalgperCustomer.setLabel("Totals sales on Customer");
        for (Ordretabell ot : t) {
            totsalgperCustomer.set(ot.getOrdretabellPK().getKundebrukernavn(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
        }
        users.addSeries(totsalgperCustomer);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void createLinearModel2() {
        chartModelUser = new CartesianChartModel();
        List<Bruker> br = brukerFacade.findAll();
        

        for (Bruker b : br) {
            ChartSeries salg = new ChartSeries();
            salg.setLabel("Sales on " + b.getBrukernavn());
            for (Ordretabell ot : t) {
                if (!(ot.getLevDato() == null)) {
                    if (ot.getLevDato().after(fra) && ot.getLevDato().before(til) && ot.getOrdretabellPK().getSelgerbrukernavn().equalsIgnoreCase(b.getBrukernavn())) {
                        salg.set((ot.getLevDato().getYear() + "-" + ot.getLevDato().getMonth()), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
                    }
                }
            }
            chartModelUser.addSeries(salg);
        }
    }
     private void createWebSales() {
        web = new CartesianChartModel();
        
        LineChartSeries salg = new LineChartSeries();
        salg.setLabel("Totale sales per date on on web site (kr)");
        for (Ordretabell ot : t) {
            if (!(ot.getLevDato() == null)) {
                if (ot.getLevDato().after(fra) && ot.getLevDato().before(til) && ot.getOrdretabellPK().getSelgerbrukernavn().equalsIgnoreCase("web")) {
                    salg.set(ot.getLevDato().getDate(), Integer.parseInt(salgFacade.find(ot.getOrdretabellPK().getSalgsnummer()).getSumSalg()));
                }
            }
        }
        web.addSeries(salg);
    }
}
