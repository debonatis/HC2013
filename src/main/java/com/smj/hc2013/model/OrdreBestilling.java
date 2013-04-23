/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.util.Date;

/**
 *
 * @author deb
 */
public class OrdreBestilling {

    private Retter rett;
    private int antall;
    private Date levDato;
    private String leveringsAdresse;
    private String selskap = "";
    

    public OrdreBestilling() {
    }

    public OrdreBestilling(Retter rett, int antall) {
        this.rett = rett;
        this.antall = antall;
    }

    public Date getLevDato() {
        return levDato;
    }

    public void setLevDato(Date levDato) {
        this.levDato = levDato;
    }
    
    

    public Retter getRett() {
        return rett;
    }

    public void setRett(Retter rett) {
        this.rett = rett;
    }

    public int getAntall() {
        return antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    public String getLeveringsAdresse() {
        return leveringsAdresse;
    }

    public void setLeveringsAdresse(String leveringsAdresse) {
        this.leveringsAdresse = leveringsAdresse;
    }

    public String getSelskap() {
        return selskap;
    }

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
