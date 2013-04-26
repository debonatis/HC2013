/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

import com.smj.hc2013.jsfContCust.BrukerBehandling;
import com.smj.hc2013.model.ChatList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author deb
 */
@ManagedBean
@SessionScoped
public class Chat extends BrukerBehandling{

    private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();
    @ManagedProperty(value = "#{chatUsers}")
    private ChatList liste;
    private String privateMessage;
    private String globalMessage;
    private String username;
    private boolean loggedIn;
    private String privateUser;
    private final static String CHANNEL = "/HC";
    

    public void setListe(ChatList users) {
        this.liste = users;
    }

    public String getPrivateUser() {
        return privateUser;
    }

    public void setPrivateUser(String privateUser) {
        this.privateUser = privateUser;
    }

    public String getGlobalMessage() {
        return globalMessage;
    }

    public void setGlobalMessage(String globalMessage) {
        this.globalMessage = globalMessage;
    }

    public String getPrivateMessage() {
        return privateMessage;
    }

    public void setPrivateMessage(String privateMessage) {
        this.privateMessage = privateMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void sendGlobal() {
        pushContext.push(CHANNEL + "*", username + ": " + globalMessage);
        globalMessage = null;
    }

    public void sendPrivate() {
        pushContext.push(CHANNEL + privateUser, "[PM] " + username + ": " + privateMessage);
        privateMessage = null;
    }

    @PostConstruct
    public void login() {        
        liste.add(getUserData());        
        username = getUserData();
        pushContext.push(CHANNEL, username + " joined the channel.");
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("subscriber.connect('/" + username + "')");
        loggedIn = true;


    }

    public void disconnect() {
        liste.remove(username);
        RequestContext.getCurrentInstance().update("form:users");
        pushContext.push(CHANNEL, username + " left the channel.");
        loggedIn = false;
        username = null;
    }
}
