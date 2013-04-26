/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

import com.smj.hc2013.model.Bruker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author deb
 */
public class BrukerTellerHelp implements ServletContextListener, HttpSessionListener, ServletRequestListener {

    private static final String ATTRIBUTE_NAME = "com.smj.hc2013.jsfContl.util.BrukerTellerHelp";
    private Map<HttpSession, String> sessions = new ConcurrentHashMap<>();
    private static List<Bruker> brukere = Collections.synchronizedList(new ArrayList<Bruker>());

    public static List<Bruker> getBrukere() {
        return brukere;
    }

    public static void setBrukere(List<Bruker> brukere) {
        BrukerTellerHelp.brukere = brukere;
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute(ATTRIBUTE_NAME, this);
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        HttpSession session = request.getSession();
        if (!(request.getRemoteUser() == null)) {
            brukere.add(new Bruker(request.getRemoteUser()));
        }

        if (session.isNew()) {
            sessions.put(session, request.getRemoteAddr());

        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession());
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // NOOP. Useless since we can't obtain IP here.
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        if (!(request.getRemoteUser() == null)) {
            brukere.remove(new Bruker(request.getRemoteUser()));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    public synchronized static BrukerTellerHelp getInstance(ServletContext context) {
        return (BrukerTellerHelp) context.getAttribute(ATTRIBUTE_NAME);
    }

    public synchronized int getCount(String remoteAddr) {
        return Collections.frequency(sessions.values(), remoteAddr);
    }
}
