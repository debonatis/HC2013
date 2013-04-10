/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author deb
 */
@Entity
@Table(name = "utkjoring", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utkjoring.findAll", query = "SELECT u FROM Utkjoring u"),
    @NamedQuery(name = "Utkjoring.findByBrukernavn", query = "SELECT u FROM Utkjoring u WHERE u.utkjoringPK.brukernavn = :brukernavn"),
    @NamedQuery(name = "Utkjoring.findBySalgsnummer", query = "SELECT u FROM Utkjoring u WHERE u.utkjoringPK.salgsnummer = :salgsnummer"),
    @NamedQuery(name = "Utkjoring.findByBilnr", query = "SELECT u FROM Utkjoring u WHERE u.utkjoringPK.bilnr = :bilnr"),
    @NamedQuery(name = "Utkjoring.findBySjoforBrukernavn", query = "SELECT u FROM Utkjoring u WHERE u.utkjoringPK.sjoforBrukernavn = :sjoforBrukernavn"),
    @NamedQuery(name = "Utkjoring.findByUtkorinKogstatus", query = "SELECT u FROM Utkjoring u WHERE u.utkorinKogstatus = :utkorinKogstatus")})
public class Utkjoring implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UtkjoringPK utkjoringPK;
    @Size(max = 10)
    @Column(name = "utkorinKogstatus")
    private String utkorinKogstatus;

    public Utkjoring() {
    }

    public Utkjoring(UtkjoringPK utkjoringPK) {
        this.utkjoringPK = utkjoringPK;
    }

    public Utkjoring(String brukernavn, String salgsnummer, int bilnr, String sjoforBrukernavn) {
        this.utkjoringPK = new UtkjoringPK(brukernavn, salgsnummer, bilnr, sjoforBrukernavn);
    }

    public UtkjoringPK getUtkjoringPK() {
        return utkjoringPK;
    }

    public void setUtkjoringPK(UtkjoringPK utkjoringPK) {
        this.utkjoringPK = utkjoringPK;
    }

    public String getUtkorinKogstatus() {
        return utkorinKogstatus;
    }

    public void setUtkorinKogstatus(String utkorinKogstatus) {
        this.utkorinKogstatus = utkorinKogstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (utkjoringPK != null ? utkjoringPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utkjoring)) {
            return false;
        }
        Utkjoring other = (Utkjoring) object;
        if ((this.utkjoringPK == null && other.utkjoringPK != null) || (this.utkjoringPK != null && !this.utkjoringPK.equals(other.utkjoringPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Utkjoring[ utkjoringPK=" + utkjoringPK + " ]";
    }
    
}
