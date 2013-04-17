/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

import com.smj.hc2013.model.Bruker;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author deb
 */
public class JavaMail {

    @Resource(name = "mail/peering")
    private Session session;

    public boolean sendMail(Bruker to, String subject, Object e) {

        try {

            Message msg = new MimeMessage(session);
            msg.setSubject(subject);
            msg.setRecipient(RecipientType.TO,
                    new InternetAddress(
                    to.getEmail(),
                    to.getFornavn()));

            msg.setFrom(new InternetAddress(
                    "peering@bamvpn.net",
                    "HC"));


            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Here are the orders.");

            // Multipart message.  
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

          

            // Attachment file from file.  
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setFileName("README2.txt");
            DataSource src = new FileDataSource("file.txt");
            messageBodyPart.setDataHandler(new DataHandler(src));
            multipart.addBodyPart(messageBodyPart);           

            // Add multipart message to email.  
            msg.setContent(multipart);

            // Send email.  
            Transport.send(msg);

        } catch (Exception x) {
        }

        return true;
    }
}
