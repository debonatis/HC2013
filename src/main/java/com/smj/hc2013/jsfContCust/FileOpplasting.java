/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author deb
 */
@ManagedBean
@RequestScoped
public class FileOpplasting extends HttpServlet {

    public boolean skrivFil(InputStream is, String filename) throws ServletException, IOException {



        try {
            String outputfile = getServletContext().getRealPath("upload/" + filename);

            FileOutputStream os = new FileOutputStream(outputfile);


            int ch = is.read();
            while (ch != -1) {
                os.write(ch);
                ch = is.read();
            }
            os.close();

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
