/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.awt.font.NumericShaper.Range;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author deb
 */
@Entity
@Table(name = "ordretabell", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordretabell.findAll", query = "SELECT o FROM Ordretabell o"),
    @NamedQuery(name = "Ordretabell.findBySelgerbrukernavn", query = "SELECT o FROM Ordretabell o WHERE o.ordretabellPK.selgerbrukernavn = :selgerbrukernavn"),
    @NamedQuery(name = "Ordretabell.findBySalgsnummer", query = "SELECT o FROM Ordretabell o WHERE o.ordretabellPK.salgsnummer = :salgsnummer"),
    @NamedQuery(name = "Ordretabell.findByKundebrukernavn", query = "SELECT o FROM Ordretabell o WHERE o.ordretabellPK.kundebrukernavn = :kundebrukernavn"),
    @NamedQuery(name = "Ordretabell.findByRettnummer", query = "SELECT o FROM Ordretabell o WHERE o.rettnummer = :rettnummer"),
    @NamedQuery(name = "Ordretabell.findByStatus", query = "SELECT o FROM Ordretabell o WHERE o.status = :status"),
    @NamedQuery(name = "Ordretabell.findByAntall", query = "SELECT o FROM Ordretabell o WHERE o.antall = :antall")
})
public class Ordretabell implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdretabellPK ordretabellPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "rettnummer")
    private String rettnummer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    @Column(name = "antall")
    private Integer antall;

    public Ordretabell() {
    }

    public Ordretabell(OrdretabellPK ordretabellPK) {
        this.ordretabellPK = ordretabellPK;
    }

    public Ordretabell(OrdretabellPK ordretabellPK, String rettnummer, String status) {
        this.ordretabellPK = ordretabellPK;
        this.rettnummer = rettnummer;
        this.status = status;
    }

    public Ordretabell(String selgerbrukernavn, String salgsnummer, String kundebrukernavn) {
        this.ordretabellPK = new OrdretabellPK(selgerbrukernavn, salgsnummer, kundebrukernavn);
    }

    public OrdretabellPK getOrdretabellPK() {
        return ordretabellPK;
    }

    public void setOrdretabellPK(OrdretabellPK ordretabellPK) {
        this.ordretabellPK = ordretabellPK;
    }

    public Integer getAntall() {
        return antall;
    }

    public void setAntall(Integer antall) {
        this.antall = antall;
    }

    public String getRettnummer() {
        return rettnummer;
    }

    public void setRettnummer(String rettnummer) {
        this.rettnummer = rettnummer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordretabellPK != null ? ordretabellPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordretabell)) {
            return false;
        }
        Ordretabell other = (Ordretabell) object;
        if ((this.ordretabellPK == null && other.ordretabellPK != null) || (this.ordretabellPK != null && !this.ordretabellPK.equals(other.ordretabellPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Ordretabell[ ordretabellPK=" + ordretabellPK + " ]";
    }
}
