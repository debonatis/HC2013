/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust.mobileUi;

import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author deb
 */
public class SjoforUtkjoring {
    
    private List<OrdreUtkjoring> utListe;
    private OrdreUtkjoring selected = new OrdreUtkjoring();
    
   @PostConstruct 
    public void init(){
        
    }
    
}
