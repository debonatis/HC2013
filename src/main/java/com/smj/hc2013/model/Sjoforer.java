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
@Table(name = "sjoforer", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sjoforer.findAll", query = "SELECT s FROM Sjoforer s"),
    @NamedQuery(name = "Sjoforer.findByBrukernavn", query = "SELECT s FROM Sjoforer s WHERE s.brukernavn = :brukernavn"),
    @NamedQuery(name = "Sjoforer.findByForekortklasse", query = "SELECT s FROM Sjoforer s WHERE s.forekortklasse = :forekortklasse")})
public class Sjoforer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "brukernavn")
    private String brukernavn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Forekortklasse")
    private String forekortklasse;

    /**
     *
     */
    public Sjoforer() {
    }

    /**
     *
     * @param brukernavn
     */
    public Sjoforer(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    /**
     *
     * @param brukernavn
     * @param forekortklasse
     */
    public Sjoforer(String brukernavn, String forekortklasse) {
        this.brukernavn = brukernavn;
        this.forekortklasse = forekortklasse;
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
    public String getForekortklasse() {
        return forekortklasse;
    }

    /**
     *
     * @param forekortklasse
     */
    public void setForekortklasse(String forekortklasse) {
        this.forekortklasse = forekortklasse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brukernavn != null ? brukernavn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sjoforer)) {
            return false;
        }
        Sjoforer other = (Sjoforer) object;
        if ((this.brukernavn == null && other.brukernavn != null) || (this.brukernavn != null && !this.brukernavn.equals(other.brukernavn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Sjoforer[ brukernavn=" + brukernavn + " ]";
    }
    
}
