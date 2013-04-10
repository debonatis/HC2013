/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "utkjoringsBil", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UtkjoringsBil.findAll", query = "SELECT u FROM UtkjoringsBil u"),
    @NamedQuery(name = "UtkjoringsBil.findByBilnr", query = "SELECT u FROM UtkjoringsBil u WHERE u.bilnr = :bilnr"),
    @NamedQuery(name = "UtkjoringsBil.findByRegnummer", query = "SELECT u FROM UtkjoringsBil u WHERE u.regnummer = :regnummer"),
    @NamedQuery(name = "UtkjoringsBil.findByStatus", query = "SELECT u FROM UtkjoringsBil u WHERE u.status = :status")})
public class UtkjoringsBil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bilnr")
    private Integer bilnr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "regnummer")
    private String regnummer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;

    public UtkjoringsBil() {
    }

    public UtkjoringsBil(Integer bilnr) {
        this.bilnr = bilnr;
    }

    public UtkjoringsBil(Integer bilnr, String regnummer, String status) {
        this.bilnr = bilnr;
        this.regnummer = regnummer;
        this.status = status;
    }

    public Integer getBilnr() {
        return bilnr;
    }

    public void setBilnr(Integer bilnr) {
        this.bilnr = bilnr;
    }

    public String getRegnummer() {
        return regnummer;
    }

    public void setRegnummer(String regnummer) {
        this.regnummer = regnummer;
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
        hash += (bilnr != null ? bilnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UtkjoringsBil)) {
            return false;
        }
        UtkjoringsBil other = (UtkjoringsBil) object;
        if ((this.bilnr == null && other.bilnr != null) || (this.bilnr != null && !this.bilnr.equals(other.bilnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.UtkjoringsBil[ bilnr=" + bilnr + " ]";
    }
    
}
