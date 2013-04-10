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
public class UtkjoringPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "brukernavn")
    private String brukernavn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "salgsnummer")
    private String salgsnummer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bilnr")
    private int bilnr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "sjoforBrukernavn")
    private String sjoforBrukernavn;

    public UtkjoringPK() {
    }

    public UtkjoringPK(String brukernavn, String salgsnummer, int bilnr, String sjoforBrukernavn) {
        this.brukernavn = brukernavn;
        this.salgsnummer = salgsnummer;
        this.bilnr = bilnr;
        this.sjoforBrukernavn = sjoforBrukernavn;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getSalgsnummer() {
        return salgsnummer;
    }

    public void setSalgsnummer(String salgsnummer) {
        this.salgsnummer = salgsnummer;
    }

    public int getBilnr() {
        return bilnr;
    }

    public void setBilnr(int bilnr) {
        this.bilnr = bilnr;
    }

    public String getSjoforBrukernavn() {
        return sjoforBrukernavn;
    }

    public void setSjoforBrukernavn(String sjoforBrukernavn) {
        this.sjoforBrukernavn = sjoforBrukernavn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brukernavn != null ? brukernavn.hashCode() : 0);
        hash += (salgsnummer != null ? salgsnummer.hashCode() : 0);
        hash += (int) bilnr;
        hash += (sjoforBrukernavn != null ? sjoforBrukernavn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UtkjoringPK)) {
            return false;
        }
        UtkjoringPK other = (UtkjoringPK) object;
        if ((this.brukernavn == null && other.brukernavn != null) || (this.brukernavn != null && !this.brukernavn.equals(other.brukernavn))) {
            return false;
        }
        if ((this.salgsnummer == null && other.salgsnummer != null) || (this.salgsnummer != null && !this.salgsnummer.equals(other.salgsnummer))) {
            return false;
        }
        if (this.bilnr != other.bilnr) {
            return false;
        }
        if ((this.sjoforBrukernavn == null && other.sjoforBrukernavn != null) || (this.sjoforBrukernavn != null && !this.sjoforBrukernavn.equals(other.sjoforBrukernavn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.UtkjoringPK[ brukernavn=" + brukernavn + ", salgsnummer=" + salgsnummer + ", bilnr=" + bilnr + ", sjoforBrukernavn=" + sjoforBrukernavn + " ]";
    }
    
}
