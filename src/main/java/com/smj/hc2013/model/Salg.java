/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "salg", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salg.findAll", query = "SELECT s FROM Salg s"),
    @NamedQuery(name = "Salg.findBySalgsnummer", query = "SELECT s FROM Salg s WHERE s.salgsnummer = :salgsnummer"),
    @NamedQuery(name = "Salg.findBySumSalg", query = "SELECT s FROM Salg s WHERE s.sumSalg = :sumSalg")})
public class Salg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "salgsnummer")
    private String salgsnummer;
    @Size(max = 10)
    @Column(name = "sumSalg")
    private String sumSalg;

    /**
     *
     */
    public Salg() {
    }

    /**
     *
     * @param salgsnummer
     */
    public Salg(String salgsnummer) {
        this.salgsnummer = salgsnummer;
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

    /**
     *
     * @return
     */
    public String getSumSalg() {
        return sumSalg;
    }

    /**
     *
     * @param sumSalg
     */
    public void setSumSalg(String sumSalg) {
        this.sumSalg = sumSalg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salgsnummer != null ? salgsnummer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salg)) {
            return false;
        }
        Salg other = (Salg) object;
        if ((this.salgsnummer == null && other.salgsnummer != null) || (this.salgsnummer != null && !this.salgsnummer.equals(other.salgsnummer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Salg[ salgsnummer=" + salgsnummer + " ]";
    }
    
}
