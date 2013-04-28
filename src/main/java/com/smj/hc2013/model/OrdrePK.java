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
public class OrdrePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "brukernavn")
    private String brukernavn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "salgsnummer")
    private String salgsnummer;

    /**
     *
     */
    public OrdrePK() {
    }

    /**
     *
     * @param brukernavn
     * @param salgsnummer
     */
    public OrdrePK(String brukernavn, String salgsnummer) {
        this.brukernavn = brukernavn;
        this.salgsnummer = salgsnummer;
    }

    /**
     *
     * @return
     */
    public String getBrukernavn() {
        return brukernavn;
    }

    /**
     *
     * @param brukernavn
     */
    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    /**
     *
     * @return
     */
    public String getSalgsnummer() {
        return salgsnummer;
    }

    /**
     *
     * @param salgsnummer
     */
    public void setSalgsnummer(String salgsnummer) {
        this.salgsnummer = salgsnummer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brukernavn != null ? brukernavn.hashCode() : 0);
        hash += (salgsnummer != null ? salgsnummer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdrePK)) {
            return false;
        }
        OrdrePK other = (OrdrePK) object;
        if ((this.brukernavn == null && other.brukernavn != null) || (this.brukernavn != null && !this.brukernavn.equals(other.brukernavn))) {
            return false;
        }
        if ((this.salgsnummer == null && other.salgsnummer != null) || (this.salgsnummer != null && !this.salgsnummer.equals(other.salgsnummer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.OrdrePK[ brukernavn=" + brukernavn + ", salgsnummer=" + salgsnummer + " ]";
    }
    
}
