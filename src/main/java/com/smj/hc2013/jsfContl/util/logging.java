/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

import com.smj.hc2013.jsfContCust.BrukerBehandling;
import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.model.Timer;
import com.smj.hc2013.session.BrukerFacade;
import com.smj.hc2013.session.TimerFacade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author deb
 */
@ManagedBean
@SessionScoped
public class logging extends BrukerBehandling {

    @EJB
    private BrukerFacade brukerF;
    @EJB
    private TimerFacade timerF;
    private List<Timer> liste = Collections.synchronizedList(new ArrayList<Timer>());
    private Bruker bruker;
    private Timer timeO;
    private Date start;
    private Date stop;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStop() {
        return stop;
    }

    public void setStop(Date stop) {
        this.stop = stop;
    }

    public Bruker getBruker() {
        bruker = brukerF.find(getUserData());
        return bruker;

    }

    public void regWorkHours() {

        timeO = new Timer();
        timeO.setBrukernavn(getBruker().getBrukernavn());
//        timeO.setTimeId(getStart());        
//        timeO.setArbeidsTimer(getStop());
        timerF.create(timeO);
    }

    public synchronized List<Timer> getLoggedInUsers() {
        liste = timerF.findAll();
        return liste;
    }
}
