/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author deb
 */
@Entity
@Table(name = "ordre", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordre.findAll", query = "SELECT o FROM Ordre o"),
    @NamedQuery(name = "Ordre.findByBrukernavn", query = "SELECT o FROM Ordre o WHERE o.ordrePK.brukernavn = :brukernavn"),
    @NamedQuery(name = "Ordre.findBySalgsnummer", query = "SELECT o FROM Ordre o WHERE o.ordrePK.salgsnummer = :salgsnummer"),
    @NamedQuery(name = "Ordre.findByDatoEndret", query = "SELECT o FROM Ordre o WHERE o.datoEndret = :datoEndret"),
    @NamedQuery(name = "Ordre.findByDatoLevert", query = "SELECT o FROM Ordre o WHERE o.datoLevert = :datoLevert"),
    @NamedQuery(name = "Ordre.findByBekreftet", query = "SELECT o FROM Ordre o WHERE o.bekreftet = :bekreftet"),
    @NamedQuery(name = "Ordre.findByBetaltstatus", query = "SELECT o FROM Ordre o WHERE o.betaltstatus = :betaltstatus"),
    @NamedQuery(name = "Ordre.findBySelskapnr", query = "SELECT o FROM Ordre o WHERE o.selskapnr = :selskapnr"),
    @NamedQuery(name = "Ordre.findByLevAdresse", query = "SELECT o FROM Ordre o WHERE o.levAdresse = :levAdresse")})
public class Ordre implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @EmbeddedId
    protected OrdrePK ordrePK;
    @Column(name = "datoEndret")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datoEndret;
    @Column(name = "datoLevert")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datoLevert;
    @Column(name = "bekreftet")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bekreftet;
    @Size(max = 10)
    @Column(name = "betaltstatus")
    private String betaltstatus;
    @Column(name = "selskapnr")
    private Integer selskapnr;
    @Size(max = 50)
    @Column(name = "levAdresse")
    private String levAdresse;

    /**
     *
     * @return
     */
    public String getLevAdresse() {
        return levAdresse;
    }

    /**
     *
     * @param levAdresse
     */
    public void setLevAdresse(String levAdresse) {
        this.levAdresse = levAdresse;
    }

    /**
     *
     */
    public Ordre() {
    }

    /**
     *
     * @param ordrePK
     */
    public Ordre(OrdrePK ordrePK) {
        this.ordrePK = ordrePK;
    }

    /**
     *
     * @param brukernavn
     * @param salgsnummer
     */
    public Ordre(String brukernavn, String salgsnummer) {
        this.ordrePK = new OrdrePK(brukernavn, salgsnummer);
    }

    /**
     *
     * @return
     */
    public OrdrePK getOrdrePK() {
        return ordrePK;
    }

    /**
     *
     * @param ordrePK
     */
    public void setOrdrePK(OrdrePK ordrePK) {
        this.ordrePK = ordrePK;
    }

    /**
     *
     * @return
     */
    public Date getDatoEndret() {
        return datoEndret;
    }

    /**
     *
     * @param datoEndret
     */
    public void setDatoEndret(Date datoEndret) {
        this.datoEndret = datoEndret;
    }

    /**
     *
     * @return
     */
    public Date getDatoLevert() {
        return datoLevert;
    }

    /**
     *
     * @param datoLevert
     */
    public void setDatoLevert(Date datoLevert) {
        this.datoLevert = datoLevert;
    }

    /**
     *
     * @return
     */
    public Date getBekreftet() {
        return bekreftet;
    }

    /**
     *
     * @param bekreftet
     */
    public void setBekreftet(Date bekreftet) {
        this.bekreftet = bekreftet;
    }

    /**
     *
     * @return
     */
    public String getBetaltstatus() {
        return betaltstatus;
    }

    /**
     *
     * @param betaltstatus
     */
    public void setBetaltstatus(String betaltstatus) {
        this.betaltstatus = betaltstatus;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordrePK != null ? ordrePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordre)) {
            return false;
        }
        Ordre other = (Ordre) object;
        if ((this.ordrePK == null && other.ordrePK != null) || (this.ordrePK != null && !this.ordrePK.equals(other.ordrePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Ordre[ ordrePK=" + ordrePK + " ]";
    }
}
