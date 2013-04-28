package com.smj.hc2013.jsfContCust;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *Getter and setters are not described
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

    /**
     * Check off role
     * @param brukerOK
     */
    public void setBrukerOK(boolean brukerOK) {
        this.brukerOK = brukerOK;
    }

    /**
     * Check off role
     * @param kokkOK
     */
    public void setKokkOK(boolean kokkOK) {
        this.kokkOK = kokkOK;
    }

    /**
     *Check off role
     * @param customerOK
     */
    public void setCustomerOK(boolean customerOK) {
        this.customerOK = customerOK;
    }

    /**
     *Check off role
     * @param salgOK
     */
    public void setSalgOK(boolean salgOK) {
        this.salgOK = salgOK;
    }

    /**
     *Check off role
     * @param sjoforOK
     */
    public void setSjoforOK(boolean sjoforOK) {
        this.sjoforOK = sjoforOK;
    }
    private boolean salgOK;
    private boolean sjoforOK;

    /**
     * Invalidates "this" session
     * @throws IOException
     */
    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
    }

    /**
     *
     * @param rolle
     */
    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    /**
     *
     * @param userData
     */
    public void setUserData(String userData) {
        this.userData = userData;
    }

    /**
     *Check off role
     * @return
     */
    public boolean isAdminOK() {
        this.adminOK = (getRolle().equals("admin")) ? true : false;
        return adminOK;
    }

    /**
     *Check off role
     * @return
     */
    public boolean isBrukerOK() {
        return (getRolle().equals("bruker")) ? true : false;
    }

    /**
     *Check off role
     * @return
     */
    public boolean isKokkOK() {
        return (getRolle().equals("kokk")) ? true : false;
    }

    /**
     *Check off role
     * @return
     */
    public boolean isSalgOK() {
        return (getRolle().equals("salg")) ? true : false;
    }

    /**
     *Check off role
     * @return
     */
    public boolean isCustomerOK() {
        return (getRolle().equals("customer")) ? true : false;
    }

    /**
     *Check off role
     * @return
     */
    public boolean isSjoforOK() {
        return (getRolle().equals("sjofor")) ? true : false;
    }

    /**
     *Check off role
     * @param adminOK
     */
    public void setAdminOK(boolean adminOK) {
        this.adminOK = adminOK;
    }

    /**
     *
     * @return the given role of the user
     */
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

    /**
     *
     * @return the name of the user
     */
    public String getUserData() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object forsporrselobject = context.getRequest();
        
        if (!(forsporrselobject instanceof HttpServletRequest)) {
            logger.log(Level.SEVERE, "Det forespurte objektet er av type {0}", forsporrselobject.getClass());

        }
        HttpServletRequest foresporrsel = (HttpServletRequest) forsporrselobject;       
        return (foresporrsel.getRemoteUser());
    }

    /**
     * redirests to registration site
     * @return
     */
    public String reg() {
        return "/faces/login/registrering?faces-redirect=true";
    }

    /**
     * invalidates the session 
     * @return
     */
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

    /**
     * 
     * @param rolle
     * @return  true or false if user has role or not
     */
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
