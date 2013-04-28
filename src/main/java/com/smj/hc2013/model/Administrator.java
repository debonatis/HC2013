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
@Table(name = "administrator", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrator.findAll", query = "SELECT a FROM Administrator a"),
    @NamedQuery(name = "Administrator.findByBrukernavn", query = "SELECT a FROM Administrator a WHERE a.brukernavn = :brukernavn")})
public class Administrator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "brukernavn")
    private String brukernavn;

    /**
     *
     */
    public Administrator() {
    }

    /**
     *
     * @param brukernavn
     */
    public Administrator(String brukernavn) {
        this.brukernavn = brukernavn;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brukernavn != null ? brukernavn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) object;
        if ((this.brukernavn == null && other.brukernavn != null) || (this.brukernavn != null && !this.brukernavn.equals(other.brukernavn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Administrator[ brukernavn=" + brukernavn + " ]";
    }
    
}
