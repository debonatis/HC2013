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
@Table(name = "bruker", catalog = "waplj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bruker.findAll", query = "SELECT b FROM Bruker b"),
    @NamedQuery(name = "Bruker.findByBrukernavn", query = "SELECT b FROM Bruker b WHERE b.brukernavn = :brukernavn"),
    @NamedQuery(name = "Bruker.findByPassord", query = "SELECT b FROM Bruker b WHERE b.passord = :passord"),
    @NamedQuery(name = "Bruker.findByFornavn", query = "SELECT b FROM Bruker b WHERE b.fornavn = :fornavn"),
    @NamedQuery(name = "Bruker.findByEtternavn", query = "SELECT b FROM Bruker b WHERE b.etternavn = :etternavn"),
    @NamedQuery(name = "Bruker.findByAdresse", query = "SELECT b FROM Bruker b WHERE b.adresse = :adresse"),
    @NamedQuery(name = "Bruker.findByEmail", query = "SELECT b FROM Bruker b WHERE b.email = :email"),
    @NamedQuery(name = "Bruker.findByTelefon", query = "SELECT b FROM Bruker b WHERE b.telefon = :telefon"),
    @NamedQuery(name = "Bruker.findByPostnummer", query = "SELECT b FROM Bruker b WHERE b.postnummer = :postnummer")})
public class Bruker implements Serializable {
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
    @Column(name = "passord")
    private String passord;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fornavn")
    private String fornavn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "etternavn")
    private String etternavn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "adresse")
    private String adresse;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefon")
    private int telefon;
    @Column(name = "postnummer")
    private Integer postnummer;

    /**
     *
     */
    public Bruker() {
    }

    /**
     *
     * @param brukernavn
     */
    public Bruker(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    /**
     *
     * @param brukernavn
     * @param passord
     * @param fornavn
     * @param etternavn
     * @param adresse
     * @param email
     * @param telefon
     */
    public Bruker(String brukernavn, String passord, String fornavn, String etternavn, String adresse, String email, int telefon) {
        this.brukernavn = brukernavn;
        this.passord = passord;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.email = email;
        this.telefon = telefon;
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
    public String getPassord() {
        return passord;
    }

    /**
     *
     * @param passord
     */
    public void setPassord(String passord) {
        this.passord = passord;
    }

    /**
     *
     * @return
     */
    public String getFornavn() {
        return fornavn;
    }

    /**
     *
     * @param fornavn
     */
    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    /**
     *
     * @return
     */
    public String getEtternavn() {
        return etternavn;
    }

    /**
     *
     * @param etternavn
     */
    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
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
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public int getTelefon() {
        return telefon;
    }

    /**
     *
     * @param telefon
     */
    public void setTelefon(int telefon) {
        this.telefon = telefon;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brukernavn != null ? brukernavn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bruker)) {
            return false;
        }
        Bruker other = (Bruker) object;
        if ((this.brukernavn == null && other.brukernavn != null) || (this.brukernavn != null && !this.brukernavn.equals(other.brukernavn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smj.hc2013.model.Bruker[ brukernavn=" + brukernavn + " ]";
    }
    
}
