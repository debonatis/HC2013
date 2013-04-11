/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author deb
 */
@ManagedBean
@RequestScoped
public class FileOpplasting extends HttpServlet {
    
    private String fil;
    
    private InputStream is;
    
    private String filename;
    
    private FileOutputStream os;

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public String getFil() {
        return fil;
    }

    public void setFil(String fil) {
        this.fil = fil;
    }

    public FileOutputStream getOs() {
        return os;
    }

    public void setOs(FileOutputStream os) {
        this.os = os;
    }

    public boolean skrivFil() throws ServletException, IOException {

        try {
            fil = getServletContext().getRealPath("upload/" + filename);

            os = new FileOutputStream(fil);


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
