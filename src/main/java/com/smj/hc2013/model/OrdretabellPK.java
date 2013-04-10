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
public class OrdretabellPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "selgerbrukernavn")
    private String selgerbrukernavn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "salgsnummer")
    private String salgsnummer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "kundebrukernavn")
    private String kundebrukernavn;

    public OrdretabellPK() {
    }

    public OrdretabellPK(String selgerbrukernavn, String salgsnummer, String kundebrukernavn) {
        this.selgerbrukernavn = selgerbrukernavn;
        this.salgsnummer = salgsnummer;
        this.kundebrukernavn = kundebrukernavn;
    }

    public String getSelgerbrukernavn() {
        return selgerbrukernavn;
    }

    public void setSelgerbrukernavn(String selgerbrukernavn) {
        this.selgerbrukernavn = selgerbrukernavn;
    }

    public String getSalgsnummer() {
        return salgsnummer;
    }

    public void setSalgsnummer(String salgsnummer) {
        this.salgsnummer = salgsnummer;
    }

    public String getKundebrukernavn() {
        return kundebrukernavn;
    }

    public void setKundebrukernavn(String kundebrukernavn) {
        this.kundebrukernavn = kundebrukernavn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (selgerbrukernavn != null ? selgerbrukernavn.hashCode() : 0);
        hash += (salgsnummer != null ? salgsnummer.hashCode() : 0);
        hash += (kundebrukernavn != null ? kundebrukernavn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdretabellPK)) {
            return false;
        }
        OrdretabellPK other = (OrdretabellPK) object;
        if ((this.selgerbrukernavn == null && other.selgerbrukernavn != null) || (this.selgerbrukernavn != null && !this.selgerbrukernavn.equals(other.selgerbrukernavn))) {
            return false;
        }
        if ((this.salgsnummer == null && other.salgsnummer != null) || (this.salgsnummer != null && !this.salgsnummer.equals(other.salgsnummer))) {
            return false;
        }
        if ((this.kundebrukernavn == null && other.kundebrukernavn != null) || (this.kundebrukernavn != null && !this.kundebrukernavn.equals(other.kundebrukernavn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.OrdretabellPK[ selgerbrukernavn=" + selgerbrukernavn + ", salgsnummer=" + salgsnummer + ", kundebrukernavn=" + kundebrukernavn + " ]";
    }
    
}
