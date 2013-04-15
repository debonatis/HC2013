/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust.mobileUi;

import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.model.Ordre;
import com.smj.hc2013.model.Ordretabell;
import com.smj.hc2013.model.Retter;
import com.smj.hc2013.session.SelgereFacade;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 *
 * @author deb
 */
public class OrdreUtkjoring {

    
    private Ordretabell ordreTabell = new Ordretabell();
    private Ordre ordre = new Ordre();
    private Bruker bruker = new Bruker();
    private Retter rett = new Retter();

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
