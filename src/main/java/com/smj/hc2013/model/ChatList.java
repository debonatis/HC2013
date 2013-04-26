/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author deb
 */
@ManagedBean
@ApplicationScoped
public class ChatList{
    
    private List<String> users;
    
    @PostConstruct
    public void init() {
        this.users = new ArrayList<>();
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
    
    public void addUser(String user) {
        this.users.add(user);
    }
    
    public void removeUser(String user) {
        this.users.remove(user);
    }
    
    public boolean contains(String user) {
        return this.users.contains(user);
    }
}
