/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author deb
 */
@ManagedBean
@ViewScoped
public class Kart {

    private MapModel kart;

    /**
     *
     */
    public Kart() {
        kart = new DefaultMapModel();
        LatLng coord1 = new LatLng(63.430515, 10.395053);
        kart.addOverlay(new Marker(coord1, "Healthy Catering"));

    }

    /**
     *
     * @return
     */
    public MapModel getKart() {
        return kart;
    }
}
