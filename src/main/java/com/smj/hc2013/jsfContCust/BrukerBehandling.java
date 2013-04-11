package com.smj.hc2013.jsfContCust;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.smj.hc2013.jsfContl.util.JsfUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.Cacheable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author deb
 */
@Named("bruker")
@SessionScoped
@Cacheable(false)
public class BrukerBehandling implements Serializable {

    private static final Logger logger = Logger.getLogger("com.corejsf");
    private static final String[] roller = {"admin", "bruker", "kokk", "customer", "salg"};
    private boolean adminOK;

    public boolean isAdminOK() {
        this.adminOK = (getRolle().equals("admin")) ? true : false;
        return adminOK;
    }

    public void setAdminOK(boolean adminOK) {
        this.adminOK = adminOK;
    }
    

    public static String getRolle() {
        for (String r : roller) {
            if (isInRole(r)) {
                return r;
            }
        }
        logout();
        return "NO ROLE, logging you out!";
    }
    
    public static String getUserData() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object forsporrselobject = context.getRequest();
        if (!(forsporrselobject instanceof HttpServletRequest)) {
            logger.log(Level.SEVERE, "Det forespurte objektet er av type {0}", forsporrselobject.getClass());
           
        }
        HttpServletRequest foresporrsel = (HttpServletRequest) forsporrselobject;
        return (foresporrsel.getRemoteUser());
    }

    public static String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        session.invalidate();

        try {
            request.logout();
            request.logout();
        } catch (ServletException e) {
        }
        return "ok";
    }
    
     public String logout2() {
    String result="/index?faces-redirect=true";
     
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
     
    try {
      request.logout();
    } catch (ServletException e) {
      JsfUtil.addErrorMessage("Failes to log you our!");       
      result = "/loginError?faces-redirect=true";
    }
     
    return result;
  }

    public static boolean isInRole(String k) {
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
