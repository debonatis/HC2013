/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author deb
 */
public class OrdreBestilling {

    private Retter rett;
    private int antall;
    private Date levDato;
    @NotNull
    private String leveringsAdresse;    
    private String selskap = "";
    

    /**
     *
     */
    public OrdreBestilling() {
    }

    /**
     *
     * @param rett
     * @param antall
     */
    public OrdreBestilling(Retter rett, int antall) {
        this.rett = rett;
        this.antall = antall;
    }

    /**
     *
     * @return
     */
    public Date getLevDato() {
        return levDato;
    }

    /**
     *
     * @param levDato
     */
    public void setLevDato(Date levDato) {
        this.levDato = levDato;
    }
    
    

    /**
     *
     * @return
     */
    public Retter getRett() {
        return rett;
    }

    /**
     *
     * @param rett
     */
    public void setRett(Retter rett) {
        this.rett = rett;
    }

    /**
     *
     * @return
     */
    public int getAntall() {
        return antall;
    }

    /**
     *
     * @param antall
     */
    public void setAntall(int antall) {
        this.antall = antall;
    }

    /**
     *
     * @return
     */
    public String getLeveringsAdresse() {
        return leveringsAdresse;
    }

    /**
     *
     * @param leveringsAdresse
     */
    public void setLeveringsAdresse(String leveringsAdresse) {
        this.leveringsAdresse = leveringsAdresse;
    }

    /**
     *
     * @return
     */
    public String getSelskap() {
        return selskap;
    }

    /**
     *
     * @param selskap
     */
    public void setSelskap(String selskap) {
        this.selskap = selskap;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rett != null ? rett.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {       
        if (!(object instanceof OrdreBestilling)) {
            return false;
        }
        OrdreBestilling other = (OrdreBestilling) object;
        if ((this.rett == null && other.rett != null) || (this.rett != null && !this.rett.equals(other.rett))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Ordre[ ordrePK=" + rett + " ]";
    }
}
