/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author deb
 */
@Entity
@Table(name = "timer", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timer.findAll", query = "SELECT a FROM Timer a"),
    @NamedQuery(name = "Timer.TimeId", query = "SELECT a FROM Timer a WHERE a.timeId = :timeId"),
    @NamedQuery(name = "Timer.Brukernavn", query = "SELECT a FROM Timer a WHERE a.brukernavn = :brukernavn"),
@NamedQuery(name = "Timer.ArbeidsTimer", query = "SELECT a FROM Timer a WHERE a.arbeidsTimer = :arbeidsTimer")})
public class Timer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Basic(optional = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "timeId")
    private Date timeId;
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "brukernavn")
    private String brukernavn;
    @Size(max = 10)
    @Column(name = "arbeidsTimer")   
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date arbeidsTimer;

    public Timer() {
    }

    public Date getTimeId() {
        return timeId;
    }

    public void setTimeId(Date timeId) {
        this.timeId = timeId;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public Date getArbeidsTimer() {
        return arbeidsTimer;
    }

    public void setArbeidsTimer(Date arbeidsTimer) {
        this.arbeidsTimer = arbeidsTimer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.timeId);
        hash = 59 * hash + Objects.hashCode(this.brukernavn);
        hash = 59 * hash + Objects.hashCode(this.arbeidsTimer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Timer other = (Timer) obj;
        if (!Objects.equals(this.timeId, other.timeId)) {
            return false;
        }
        if (!Objects.equals(this.brukernavn, other.brukernavn)) {
            return false;
        }
        if (!Objects.equals(this.arbeidsTimer, other.arbeidsTimer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Timer{" + "timeId=" + timeId + ", brukernavn=" + brukernavn + ", arbeidsTimer=" + arbeidsTimer + '}';
    }
    
}