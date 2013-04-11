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
@Table(name = "retter", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retter.findAll", query = "SELECT r FROM Retter r"),
    @NamedQuery(name = "Retter.findByRettnummer", query = "SELECT r FROM Retter r WHERE r.rettnummer = :rettnummer"),
    @NamedQuery(name = "Retter.findByNavn", query = "SELECT r FROM Retter r WHERE r.navn = :navn"),
    @NamedQuery(name = "Retter.findByBeskrivelse", query = "SELECT r FROM Retter r WHERE r.beskrivelse = :beskrivelse"),
    @NamedQuery(name = "Retter.findByIngredienser", query = "SELECT r FROM Retter r WHERE r.ingredienser = :ingredienser"),
    @NamedQuery(name = "Retter.findByKalorier", query = "SELECT r FROM Retter r WHERE r.kalorier = :kalorier"),
    @NamedQuery(name = "Retter.findByVekt", query = "SELECT r FROM Retter r WHERE r.vekt = :vekt"),
    @NamedQuery(name = "Retter.findByPris", query = "SELECT r FROM Retter r WHERE r.pris = :pris"),
    @NamedQuery(name = "Retter.findByFil", query = "SELECT r FROM Retter r WHERE r.fil = :fil")})
public class Retter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "rettnummer")
    private String rettnummer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "navn")
    private String navn;
    @Size(max = 30)
    @Column(name = "beskrivelse")
    private String beskrivelse;
    @Size(max = 30)
    @Column(name = "ingredienser")
    private String ingredienser;
    @Column(name = "kalorier")
    private Integer kalorier;
    @Column(name = "vekt")
    private Integer vekt;
    @Column(name = "pris")
    private Integer pris;
    @Size(max = 30)
    @Column(name = "fil")
    private String fil;

    public Retter() {
    }

    public Retter(String rettnummer) {
        this.rettnummer = rettnummer;
    }

    public Retter(String rettnummer, String navn) {
        this.rettnummer = rettnummer;
        this.navn = navn;
    }

    public String getFil() {
        return fil;
    }

    public void setFil(String fil) {
        this.fil = fil;
    }

    public String getRettnummer() {
        return rettnummer;
    }

    public void setRettnummer(String rettnummer) {
        this.rettnummer = rettnummer;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getIngredienser() {
        return ingredienser;
    }

    public void setIngredienser(String ingredienser) {
        this.ingredienser = ingredienser;
    }

    public Integer getKalorier() {
        return kalorier;
    }

    public void setKalorier(Integer kalorier) {
        this.kalorier = kalorier;
    }

    public Integer getVekt() {
        return vekt;
    }

    public void setVekt(Integer vekt) {
        this.vekt = vekt;
    }

    public Integer getPris() {
        return pris;
    }

    public void setPris(Integer pris) {
        this.pris = pris;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rettnummer != null ? rettnummer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retter)) {
            return false;
        }
        Retter other = (Retter) object;
        if ((this.rettnummer == null && other.rettnummer != null) || (this.rettnummer != null && !this.rettnummer.equals(other.rettnummer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Retter[ rettnummer=" + rettnummer + " ]";
    }
}
