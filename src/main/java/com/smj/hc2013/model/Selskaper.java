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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author deb
 */
@Entity
@Table(name = "selskaper", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Selskaper.findAll", query = "SELECT s FROM Selskaper s"),
    @NamedQuery(name = "Selskaper.findBySelskapnr", query = "SELECT s FROM Selskaper s WHERE s.selskapnr = :selskapnr"),
    @NamedQuery(name = "Selskaper.findByAvslag", query = "SELECT s FROM Selskaper s WHERE s.avslag = :avslag"),
    @NamedQuery(name = "Selskaper.findByAkkumulertSalg", query = "SELECT s FROM Selskaper s WHERE s.akkumulertSalg = :akkumulertSalg"),
    @NamedQuery(name = "Selskaper.findByBrId", query = "SELECT s FROM Selskaper s WHERE s.BrId = :BrId"),
    @NamedQuery(name = "Selskaper.findBySAdresse", query = "SELECT s FROM Selskaper s WHERE s.adresse = :adresse")})
public class Selskaper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "selskapnr")
    private Integer selskapnr;
    @Column(name = "avslag")
    private Integer avslag;
    @Column(name = "akkumulertSalg")
    private Integer akkumulertSalg;
    @Column(name = "BrId")
    private String BrId;
    @Column(name = "adresse")
    private String adresse;

    /**
     *
     */
    public Selskaper() {
    }

    /**
     *
     * @return
     */
    public String getBrId() {
        return BrId;
    }

    /**
     *
     * @param BrId
     */
    public void setBrId(String BrId) {
        this.BrId = BrId;
    }

    /**
     *
     * @return
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     *
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     *
     * @param selskapnr
     */
    public Selskaper(Integer selskapnr) {
        this.selskapnr = selskapnr;
    }

    /**
     *
     * @return
     */
    public Integer getSelskapnr() {
        return selskapnr;
    }

    /**
     *
     * @param selskapnr
     */
    public void setSelskapnr(Integer selskapnr) {
        this.selskapnr = selskapnr;
    }

    /**
     *
     * @return
     */
    public Integer getAvslag() {
        return avslag;
    }

    /**
     *
     * @param avslag
     */
    public void setAvslag(Integer avslag) {
        this.avslag = avslag;
    }

    /**
     *
     * @return
     */
    public Integer getAkkumulertSalg() {
        return akkumulertSalg;
    }

    /**
     *
     * @param akkumulertSalg
     */
    public void setAkkumulertSalg(Integer akkumulertSalg) {
        this.akkumulertSalg = akkumulertSalg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (selskapnr != null ? selskapnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Selskaper)) {
            return false;
        }
        Selskaper other = (Selskaper) object;
        if ((this.selskapnr == null && other.selskapnr != null) || (this.selskapnr != null && !this.selskapnr.equals(other.selskapnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Selskaper[ selskapnr=" + selskapnr + " ]";
    }
}
