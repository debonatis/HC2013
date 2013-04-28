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
@Table(name = "selgere", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Selgere.findAll", query = "SELECT s FROM Selgere s"),
    @NamedQuery(name = "Selgere.findByBrukernavn", query = "SELECT s FROM Selgere s WHERE s.brukernavn = :brukernavn"),
    @NamedQuery(name = "Selgere.findBySalgPerManed", query = "SELECT s FROM Selgere s WHERE s.salgPerManed = :salgPerManed"),
    @NamedQuery(name = "Selgere.findByOpparbeidetProvisjon", query = "SELECT s FROM Selgere s WHERE s.opparbeidetProvisjon = :opparbeidetProvisjon"),
    @NamedQuery(name = "Selgere.findByAntSalg", query = "SELECT s FROM Selgere s WHERE s.antSalg = :antSalg"),
    @NamedQuery(name = "Selgere.findByTimer", query = "SELECT s FROM Selgere s WHERE s.timer = :timer")})
public class Selgere implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "brukernavn")
    private String brukernavn;
    @Size(max = 10)
    @Column(name = "salgPerManed")
    private String salgPerManed;
    @Size(max = 10)
    @Column(name = "opparbeidetProvisjon")
    private String opparbeidetProvisjon;
    @Size(max = 10)
    @Column(name = "antSalg")
    private String antSalg;
    @Size(max = 10)
    @Column(name = "timer")
    private String timer;

    /**
     *
     */
    public Selgere() {
    }

    /**
     *
     * @param brukernavn
     */
    public Selgere(String brukernavn) {
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

    /**
     *
     * @return
     */
    public String getSalgPerManed() {
        return salgPerManed;
    }

    /**
     *
     * @param salgPerManed
     */
    public void setSalgPerManed(String salgPerManed) {
        this.salgPerManed = salgPerManed;
    }

    /**
     *
     * @return
     */
    public String getOpparbeidetProvisjon() {
        return opparbeidetProvisjon;
    }

    /**
     *
     * @param opparbeidetProvisjon
     */
    public void setOpparbeidetProvisjon(String opparbeidetProvisjon) {
        this.opparbeidetProvisjon = opparbeidetProvisjon;
    }

    /**
     *
     * @return
     */
    public String getAntSalg() {
        return antSalg;
    }

    /**
     *
     * @param antSalg
     */
    public void setAntSalg(String antSalg) {
        this.antSalg = antSalg;
    }

    /**
     *
     * @return
     */
    public String getTimer() {
        return timer;
    }

    /**
     *
     * @param timer
     */
    public void setTimer(String timer) {
        this.timer = timer;
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
        if (!(object instanceof Selgere)) {
            return false;
        }
        Selgere other = (Selgere) object;
        if ((this.brukernavn == null && other.brukernavn != null) || (this.brukernavn != null && !this.brukernavn.equals(other.brukernavn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Selgere[ brukernavn=" + brukernavn + " ]";
    }

    /**
     *
     * @param i
     */
    public void setAntSalgInt(int i) {
        this.antSalg = ((Integer) i).toString(); 
    }
    
}
