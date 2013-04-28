/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

import com.smj.hc2013.model.Retter;
import com.smj.hc2013.session.RetterFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Getter and setters are not described
 * @author Martin
 */
@ManagedBean
@ViewScoped
public class Dishes implements Serializable {

    private Retter selected = new Retter();
    @EJB
    private RetterFacade rettF;
    private List<Retter> rettL;

    /**
     *
     * @return
     */
    public Retter getSelected() {
        return selected;
    }
    
    
    private void oppdaterRetterList() {
        rettL = rettF.findAll();
    }

    /**
     *
     * @param selected
     */
    public void setSelected(Retter selected) {
        this.selected = selected;
    }

    /**
     *
     * @return
     */
    public List<Retter> getRettL() {
        oppdaterRetterList();
       
        return rettL;
    }

    /**
     *
     * @param rettL
     */
    public void setRettL(List<Retter> rettL) {
        this.rettL = rettL;
    }
}
