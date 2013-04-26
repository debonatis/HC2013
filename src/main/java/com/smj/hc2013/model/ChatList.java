/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author deb
 */
@ManagedBean
@ApplicationScoped
public class ChatList extends ArrayList<String> {

    public ChatList() {
        super();
    }
}
