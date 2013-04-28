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
@Table(name = "bosted", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bosted.findAll", query = "SELECT b FROM Bosted b"),
    @NamedQuery(name = "Bosted.findByPostnummer", query = "SELECT b FROM Bosted b WHERE b.postnummer = :postnummer"),
    @NamedQuery(name = "Bosted.findByPoststed", query = "SELECT b FROM Bosted b WHERE b.poststed = :poststed")})
public class Bosted implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "postnummer")
    private Integer postnummer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "poststed")
    private String poststed;

    /**
     *
     */
    public Bosted() {
    }

    /**
     *
     * @param postnummer
     */
    public Bosted(Integer postnummer) {
        this.postnummer = postnummer;
    }

    /**
     *
     * @param postnummer
     * @param poststed
     */
    public Bosted(Integer postnummer, String poststed) {
        this.postnummer = postnummer;
        this.poststed = poststed;
    }

    /**
     *
     * @return
     */
    public Integer getPostnummer() {
        return postnummer;
    }

    /**
     *
     * @param postnummer
     */
    public void setPostnummer(Integer postnummer) {
        this.postnummer = postnummer;
    }

    /**
     *
     * @return
     */
    public String getPoststed() {
        return poststed;
    }

    /**
     *
     * @param poststed
     */
    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postnummer != null ? postnummer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bosted)) {
            return false;
        }
        Bosted other = (Bosted) object;
        if ((this.postnummer == null && other.postnummer != null) || (this.postnummer != null && !this.postnummer.equals(other.postnummer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Bosted[ postnummer=" + postnummer + " ]";
    }
    
}
