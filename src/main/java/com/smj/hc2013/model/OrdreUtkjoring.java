/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

import java.util.Objects;

/**
 *
 * @author deb
 */
public class OrdreUtkjoring {

    private Ordretabell ordreTabell = new Ordretabell();
    private Ordre ordre = new Ordre();
    private Bruker bruker = new Bruker();
    private Retter rett = new Retter();
    private Utkjoring utkojring = new Utkjoring();

    public Utkjoring getUtkojring() {
        return utkojring;
    }

    public void setUtkojring(Utkjoring utkojring) {
        this.utkojring = utkojring;
    }

    @Override
    public String toString() {
        return "OrdreUtkjoring{" + "ordreTabell=" + ordreTabell + ", ordre=" + ordre + ", bruker=" + bruker + ", rett=" + rett + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.ordreTabell);
        hash = 43 * hash + Objects.hashCode(this.ordre);
        hash = 43 * hash + Objects.hashCode(this.bruker);
        hash = 43 * hash + Objects.hashCode(this.rett);
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
        final OrdreUtkjoring other = (OrdreUtkjoring) obj;
        if (!Objects.equals(this.ordreTabell, other.ordreTabell)) {
            return false;
        }
        if (!Objects.equals(this.ordre, other.ordre)) {
            return false;
        }
        if (!Objects.equals(this.bruker, other.bruker)) {
            return false;
        }
        if (!Objects.equals(this.rett, other.rett)) {
            return false;
        }
        return true;
    }

    public Ordretabell getOrdreTabell() {
        return ordreTabell;
    }

    public void setOrdreTabell(Ordretabell ordreTabell) {
        this.ordreTabell = ordreTabell;
    }

    public Ordre getOrdre() {
        return ordre;
    }

    public void setOrdre(Ordre ordre) {
        this.ordre = ordre;
    }

    public Bruker getBruker() {
        return bruker;
    }

    public void setBruker(Bruker bruker) {
        this.bruker = bruker;
    }

    public Retter getRett() {
        return rett;
    }

    public void setRett(Retter rett) {
        this.rett = rett;
    }
}
