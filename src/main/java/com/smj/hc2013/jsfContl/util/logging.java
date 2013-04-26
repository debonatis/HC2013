/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

import com.smj.hc2013.model.Bruker;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author deb
 */
@ManagedBean
@ApplicationScoped
@DeclareRoles({"Admin"})
public class logging extends BrukerTellerHelp{
    
    public static synchronized List<Bruker> getLoggedInUsers(){
        return getBrukere();
    }
    
}
