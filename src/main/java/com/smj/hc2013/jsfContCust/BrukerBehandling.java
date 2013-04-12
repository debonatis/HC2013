package com.smj.hc2013.jsfContCust;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.smj.hc2013.jsfContl.util.JsfUtil;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author deb
 */
@ManagedBean(name ="bruker")
@SessionScoped
@Cacheable(false)
public class BrukerBehandling implements Serializable {

    private static final Logger logger = Logger.getLogger("com.corejsf");
    private static final String[] roller = {"admin", "bruker", "kokk", "customer", "salg"};
    private boolean adminOK;
    private String rolle = "";
    private String userData = "";

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

    public void setAdminOK(boolean adminOK) {
        this.adminOK = adminOK;
    }
    

    public String getRolle() {
        for (String r : roller) {
            if (isInRole(r)) {
                return r;
            }
        }
        logout();
        return "NO ROLE, logging you out!";
    }
    
    public String getUserData() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object forsporrselobject = context.getRequest();
        if (!(forsporrselobject instanceof HttpServletRequest)) {
            logger.log(Level.SEVERE, "Det forespurte objektet er av type {0}", forsporrselobject.getClass());
           
        }
        HttpServletRequest foresporrsel = (HttpServletRequest) forsporrselobject;
        return (foresporrsel.getRemoteUser());
    }

    public String logout() {
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
    String result="/faces/login/index?faces-redirect=true";
     
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
     
    try {
      request.logout();
      request.logout();
    } catch (ServletException e) {
      JsfUtil.addErrorMessage("Failes to log you our!");       
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
