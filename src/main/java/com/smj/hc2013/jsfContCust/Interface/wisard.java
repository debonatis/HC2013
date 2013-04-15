/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust.Interface;

import com.smj.hc2013.model.Bruker;
import java.awt.event.ActionEvent;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author deb
 */
public interface wisard<T> {

    T getUser();

    boolean isSkip();

    String onFlowProcess(FlowEvent event);

    void save(ActionEvent actionEvent);

    void setSkip(boolean skip);

    void setUser(T user);
    
}
