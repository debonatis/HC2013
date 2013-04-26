/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.session.BrukerFacade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
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
    
    @EJB
    private static BrukerFacade brukerF;
    private static List<Bruker> liste = Collections.synchronizedList(new ArrayList<Bruker>());
    private Bruker bruker;

    public Bruker getBruker() {
        return bruker;
    }

    public void setBruker(Bruker bruker) {
        this.bruker = bruker;
    }
    
    
    public static synchronized List<Bruker> getLoggedInUsers(){
        for(Bruker b:getBrukere()){
          Bruker f = brukerF.find(b);
            liste.add(f);
        }
        return liste;
    }
    
}
