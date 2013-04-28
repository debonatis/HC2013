/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author deb
 */
public class JavaMail {  
   

       

    private Session getMailpeering() throws NamingException {
        Context c = new InitialContext();
        return (Session) c.lookup("java:comp/env/mail/peering");
    }

    public void sendMail1(String email, String subject, String body) throws NamingException, MessagingException {
        Session mailpeering = getMailpeering();
        MimeMessage message = new MimeMessage(mailpeering);
        message.setSubject(subject);
        message.setRecipients(RecipientType.TO, InternetAddress.parse(email, false));
        message.setText(body);
        Transport.send(message);
    }
}
