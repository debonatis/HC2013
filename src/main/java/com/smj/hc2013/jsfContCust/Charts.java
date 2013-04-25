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
import com.smj.hc2013.session.UtkjoringFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@RequestScoped
public class Charts implements Serializable {

    private CartesianChartModel linearModelYear;
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
    private Ordre ordre = new Ordre();
    private Ordretabell ordreT = new Ordretabell();
    private Bruker bruker = new Bruker();
    private Retter rett = new Retter();
    private Utkjoring utkjoring = new Utkjoring();

    public Charts() {
        createLinearModelYear();
    }

    public CartesianChartModel getLinearModel() {
        return linearModelYear;
    }

    private void createLinearModelYear() {
        linearModelYear = new CartesianChartModel();

        LineChartSeries boys = new LineChartSeries();
        boys.setLabel("boys");
        
        

        boys.set(2004, 120);
        boys.set(2005, 100);
        boys.set(2006, 44);
        boys.set(2007, 150);
        boys.set(2008, 25);

        LineChartSeries girls = new LineChartSeries();
        girls.setLabel("Girls");
        girls.setMarkerStyle("diamond");

        girls.set(2004, 52);
        girls.set(2005, 60);
        girls.set(2006, 110);
        girls.set(2007, 135);
        girls.set(2008, 120);

        linearModelYear.addSeries(boys);
        linearModelYear.addSeries(girls);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
