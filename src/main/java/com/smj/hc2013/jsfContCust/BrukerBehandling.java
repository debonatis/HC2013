package com.smj.hc2013.jsfContCust;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.smj.hc2013.jsfContl.util.JsfUtil;
import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.session.BrukerFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Cacheable;
import javax.servlet.RequestDispatcher;
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
    private static final String[] roller = {"admin", "bruker", "kokk", "customer", "salg"};
    private boolean adminOK;
    private String rolle = "";
    private String userData = "";
    private String username;
    private String password;
    private String originalURL;

    
//public void init() {
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
//
//        if (originalURL == null) {
//            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
//        } else {
//            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
//
//            if (originalQuery != null) {
//                originalURL += "?" + originalQuery;
//            }
//        }
//    }
    @EJB
    private BrukerFacade userService;

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(username, password);
            Bruker user = userService.find(username);
            externalContext.getSessionMap().put("user", user);
            externalContext.redirect(originalURL);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
