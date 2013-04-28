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
@Table(name = "rolle", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolle.findAll", query = "SELECT r FROM Rolle r"),
    @NamedQuery(name = "Rolle.findByBrukernavn", query = "SELECT r FROM Rolle r WHERE r.brukernavn = :brukernavn"),
    @NamedQuery(name = "Rolle.findByRollen", query = "SELECT r FROM Rolle r WHERE r.rollen = :rollen")})
public class Rolle implements Serializable {
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
    @Column(name = "rollen")
    private String rollen;

    /**
     *
     */
    public Rolle() {
    }

    /**
     *
     * @param brukernavn
     */
    public Rolle(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    /**
     *
     * @param brukernavn
     * @param rollen
     */
    public Rolle(String brukernavn, String rollen) {
        this.brukernavn = brukernavn;
        this.rollen = rollen;
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
    public String getRollen() {
        return rollen;
    }

    /**
     *
     * @param rollen
     */
    public void setRollen(String rollen) {
        this.rollen = rollen;
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
        if (!(object instanceof Rolle)) {
            return false;
        }
        Rolle other = (Rolle) object;
        if ((this.brukernavn == null && other.brukernavn != null) || (this.brukernavn != null && !this.brukernavn.equals(other.brukernavn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Rolle[ brukernavn=" + brukernavn + " ]";
    }
    
}
