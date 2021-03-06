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
 *Getters and setters are not described
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

    /**
     *
     * @return
     */
    public Date getStart() {
        return start;
    }

    /**
     *
     * @param start
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     *
     * @return
     */
    public Date getStop() {
        return stop;
    }

    /**
     *
     * @param stop
     */
    public void setStop(Date stop) {
        this.stop = stop;
    }

    /**
     *
     * @return
     */
    public Bruker getBruker() {
        bruker = brukerF.find(getUserData());
        return bruker;

    }

    /**
     * Perist working hours in Database
     */
    public void regWorkHours() {

        timeO = new Timer();
        timeO.setBrukernavn(getBruker().getBrukernavn());
        timeO.setTimeId(getStart());
        timeO.setArbeidsTimer(getStop());
        timerF.create(timeO);
    }

    /**
     * Populates list
     * @return
     */
    public synchronized List<Timer> getLoggedInUsers() {
        liste = timerF.findAll();
        return liste;
    }
}
