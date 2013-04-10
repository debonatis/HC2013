/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author deb
 */
@Entity
@Table(name = "selskapKunde", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SelskapKunde.findAll", query = "SELECT s FROM SelskapKunde s"),
    @NamedQuery(name = "SelskapKunde.findByBrukernavn", query = "SELECT s FROM SelskapKunde s WHERE s.selskapKundePK.brukernavn = :brukernavn"),
    @NamedQuery(name = "SelskapKunde.findBySelskapnr", query = "SELECT s FROM SelskapKunde s WHERE s.selskapKundePK.selskapnr = :selskapnr")})
public class SelskapKunde implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SelskapKundePK selskapKundePK;

    public SelskapKunde() {
    }

    public SelskapKunde(SelskapKundePK selskapKundePK) {
        this.selskapKundePK = selskapKundePK;
    }

    public SelskapKunde(String brukernavn, int selskapnr) {
        this.selskapKundePK = new SelskapKundePK(brukernavn, selskapnr);
    }

    public SelskapKundePK getSelskapKundePK() {
        return selskapKundePK;
    }

    public void setSelskapKundePK(SelskapKundePK selskapKundePK) {
        this.selskapKundePK = selskapKundePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (selskapKundePK != null ? selskapKundePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SelskapKunde)) {
            return false;
        }
        SelskapKunde other = (SelskapKunde) object;
        if ((this.selskapKundePK == null && other.selskapKundePK != null) || (this.selskapKundePK != null && !this.selskapKundePK.equals(other.selskapKundePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.SelskapKunde[ selskapKundePK=" + selskapKundePK + " ]";
    }
    
}
