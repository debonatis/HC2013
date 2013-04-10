/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author deb
 */
@Embeddable
public class SelskapKundePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "brukernavn")
    private String brukernavn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "selskapnr")
    private int selskapnr;

    public SelskapKundePK() {
    }

    public SelskapKundePK(String brukernavn, int selskapnr) {
        this.brukernavn = brukernavn;
        this.selskapnr = selskapnr;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public int getSelskapnr() {
        return selskapnr;
    }

    public void setSelskapnr(int selskapnr) {
        this.selskapnr = selskapnr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brukernavn != null ? brukernavn.hashCode() : 0);
        hash += (int) selskapnr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SelskapKundePK)) {
            return false;
        }
        SelskapKundePK other = (SelskapKundePK) object;
        if ((this.brukernavn == null && other.brukernavn != null) || (this.brukernavn != null && !this.brukernavn.equals(other.brukernavn))) {
            return false;
        }
        if (this.selskapnr != other.selskapnr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.SelskapKundePK[ brukernavn=" + brukernavn + ", selskapnr=" + selskapnr + " ]";
    }
    
}
