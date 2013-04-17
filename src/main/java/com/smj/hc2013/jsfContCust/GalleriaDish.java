/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

/**
 *
 * @author Martin
 */
public class GalleriaDish {
    public String name;
    public String description;
    public String path;

    public GalleriaDish(String names, String descriptions, String paths){
        this.name=names;
        this.description=descriptions;
        this.path=paths;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }
}