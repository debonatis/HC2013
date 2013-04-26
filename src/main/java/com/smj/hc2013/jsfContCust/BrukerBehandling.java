package com.smj.hc2013.jsfContCust;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.smj.hc2013.jsfContl.util.BrukerTellerHelp;
import com.smj.hc2013.jsfContl.util.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Cacheable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author deb
 */
@ManagedBean(name = "bruker")
@SessionScoped
@Cacheable(false)
public class BrukerBehandling implements Serializable {

    private static final Logger logger = Logger.getLogger("com.corejsf");
    private static final String[] roller = {"admin", "bruker", "kokk", "customer", "salg", "sjofor"};
    private boolean adminOK;
    private String rolle = "";
    private String userData = "";
    private boolean brukerOK;
    private boolean kokkOK;
    private boolean customerOK;

    public void setBrukerOK(boolean brukerOK) {
        this.brukerOK = brukerOK;
    }

    public void setKokkOK(boolean kokkOK) {
        this.kokkOK = kokkOK;
    }

    public void setCustomerOK(boolean customerOK) {
        this.customerOK = customerOK;
    }

    public void setSalgOK(boolean salgOK) {
        this.salgOK = salgOK;
    }

    public void setSjoforOK(boolean sjoforOK) {
        this.sjoforOK = sjoforOK;
    }
    private boolean salgOK;
    private boolean sjoforOK;

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public boolean isAdminOK() {
        this.adminOK = (getRolle().equals("admin")) ? true : false;
        return adminOK;
    }

    public boolean isBrukerOK() {
        return (getRolle().equals("bruker")) ? true : false;
    }

    public boolean isKokkOK() {
        return (getRolle().equals("kokk")) ? true : false;
    }

    public boolean isSalgOK() {
        return (getRolle().equals("salg")) ? true : false;
    }

    public boolean isCustomerOK() {
        return (getRolle().equals("customer")) ? true : false;
    }

    public boolean isSjoforOK() {
        return (getRolle().equals("sjofor")) ? true : false;
    }

    public void setAdminOK(boolean adminOK) {
        this.adminOK = adminOK;
    }

    public String getRolle() {
        for (String r : roller) {
            if (isInRole(r)) {
                return r;
            }
        }
        try {
            logout();
        } catch (Exception e) {
        }
        return "NO ROLE, logging you out!";
    }

    public String getUserData() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object forsporrselobject = context.getRequest();
        
        if (!(forsporrselobject instanceof HttpServletRequest)) {
            logger.log(Level.SEVERE, "Det forespurte objektet er av type {0}", forsporrselobject.getClass());

        }
        HttpServletRequest foresporrsel = (HttpServletRequest) forsporrselobject;
        BrukerTellerHelp instance = BrukerTellerHelp.getInstance(foresporrsel.getServletContext());
        if(instance.getCount(foresporrsel.getRemoteAddr())> 3){
            foresporrsel.getSession().invalidate();
        }
        return (foresporrsel.getRemoteUser());
    }

    public String reg() {
        return "/faces/login/registrering?faces-redirect=true";
    }

    public String logout2() {
        String result = "/faces/login/index?faces-redirect=true";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            request.logout();
        } catch (ServletException e) {
            JsfUtil.addErrorMessage("Failes to log you out!");
            result = "/faces/login/ikkelogin?faces-redirect=true";
        }

        return result;
    }

    public boolean isInRole(String k) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object forsporrselobject = context.getRequest();
        if (!(forsporrselobject instanceof HttpServletRequest)) {
            logger.log(Level.SEVERE, "Det forespurte objektet er av type {0}", forsporrselobject.getClass());
            return false;
        }
        HttpServletRequest foresporrsel = (HttpServletRequest) forsporrselobject;
        return foresporrsel.isUserInRole(k);
    }
}
