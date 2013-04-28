/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.jsfContl.util.JavaMail;
import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.session.BrukerFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "forgotPassword")
@SessionScoped
public class ForgotPassword extends JavaMail implements Serializable {

    private String username;
    private String email;
    @EJB
    private BrukerFacade brukerFacade;

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     */
    public void apply() {

        try {
            boolean sjekk = false;
            List<Bruker> brukerL = brukerFacade.findAll();
            Bruker br = new Bruker();
            for (Bruker b : brukerL) {
                if (b.getEmail().equalsIgnoreCase(getEmail())) {
                    br = b;
                    sjekk = true;
                }
            }
            if (sjekk) {
                sendMail1(br.getEmail(), "Here is your password, " + br.getFornavn() + "", "Du får huske det til senere da!");
                FacesMessage msg = new FacesMessage();
                msg.setSeverity(FacesMessage.SEVERITY_INFO);
                msg.setSummary("Success");
                msg.setDetail("You will soon get your password");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage();
                msg.setSeverity(FacesMessage.SEVERITY_INFO);
                msg.setSummary("We did'nt find your mail address");
                msg.setDetail("Try to make a new user instead");

                FacesContext.getCurrentInstance().addMessage(null, msg);
            }


        } catch (Exception e) {
        }
    }
}
