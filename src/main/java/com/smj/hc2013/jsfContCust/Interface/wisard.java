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
 * @param <T> 
 * @author deb
 */
public interface wisard<T> {

    /**
     *
     * @return
     */
    T getUser();

    /**
     *
     * @return
     */
    boolean isSkip();

    /**
     *
     * @param event
     * @return
     */
    String onFlowProcess(FlowEvent event);

    /**
     *
     * @param actionEvent
     */
    void save(ActionEvent actionEvent);

    /**
     *
     * @param skip
     */
    void setSkip(boolean skip);

    /**
     *
     * @param user
     */
    void setUser(T user);
    
}
