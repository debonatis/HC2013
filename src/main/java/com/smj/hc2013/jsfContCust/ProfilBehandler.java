/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.jsfContCust.Interface.wisard;
import com.smj.hc2013.model.Bruker;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author deb
 */
@ManagedBean
@ViewScoped
public class ProfilBehandler extends BrukerBehandling implements wisard {

    private Bruker bruker = new Bruker();
    private boolean skip;
    private static Logger logger = Logger.getLogger(ProfilBehandler.class.getName());

    @Override
    public Object getUser() {
       return bruker;
    }

    @Override
    public boolean isSkip() {
       return skip;
    }

    @Override
    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());

        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    @Override
    public void save(ActionEvent actionEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSkip(boolean skip) {
       this.skip = skip;
    }

    @Override
    public void setUser(Object user) {
       this.bruker = (Bruker) user;
    }
}
