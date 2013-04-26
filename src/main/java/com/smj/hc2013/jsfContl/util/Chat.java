/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

import com.smj.hc2013.model.Bruker;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author deb
 */
public class Chat {
    
      
    private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();  
      
    private List<Bruker> users;  
  
    private String privateMessage;  
      
    private String globalMessage;  
      
    private String username;  
      
    private boolean loggedIn;  
      
    private String privateUser;  
      
    private final static String CHANNEL = "/chat/";  
  
    public void setUsers(List<Bruker> users) {  
        this.users = users;  
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
      
    public void login() {  
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object forsporrselobject = context.getRequest();
        HttpServletRequest foresporrsel = (HttpServletRequest) forsporrselobject;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        
          
       
       
            users.add(new Bruker(foresporrsel.getRemoteUser())); 
            username = foresporrsel.getRemoteUser();
            pushContext.push(CHANNEL, username + " joined the channel.");  
            requestContext.execute("subscriber.connect('/" + username + "')");  
            loggedIn = true;  
         
    }  
      
    public void disconnect() {  
        //remove user and update ui  
        users.remove(username);  
        RequestContext.getCurrentInstance().update("form:users");  
          
        //push leave information  
        pushContext.push(CHANNEL, username + " left the channel.");  
          
        //reset state  
        loggedIn = false;  
        username = null;  
    }  
}  
    

