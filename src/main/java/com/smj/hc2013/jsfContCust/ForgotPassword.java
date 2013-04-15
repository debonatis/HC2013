/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.MessagingException;
import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.session.BrukerFacade;
import com.smj.hc2013.session.OrdreFacade;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "forgotPassword")
@SessionScoped
public class ForgotPassword implements Serializable {

    private String email;
    
    @EJB
    private BrukerFacade brukerFacade;
    
    String url = "jdbc:mysql://bamvpn.net:3306/waplj_prosjekt";
    String driver = "com.mysql.jdbc.Driver";

    public String getEmail() {
        return email;
    }

    public void setEmail(String theEmail) {
        email = theEmail;
    }

    public void apply() {
        Bruker bruker = new Bruker();
        if (bruker.getEmail() != null) {
            Properties props = System.getProperties();

            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", "smtp.live.com");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("fabregas4_91@hotmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

                message.setSubject("This is the Subject Line!");

                FacesMessage fm = new FacesMessage("Email sent");
                FacesContext.getCurrentInstance().addMessage(null, fm);
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        } else {
            FacesMessage fm = new FacesMessage("Email was not found");
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }
}
