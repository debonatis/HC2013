/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.model;

/**
 *
 * @author Martin
 */
public class GalleriaDish {
    /**
     *
     */
    public String name;
    /**
     *
     */
    public String description;
    /**
     *
     */
    public String path;

    /**
     *
     * @param names
     * @param descriptions
     * @param paths
     */
    public GalleriaDish(String names, String descriptions, String paths){
        this.name=names;
        this.description=descriptions;
        this.path=paths;
    }
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param desc
     */
    public void setDescription(String desc) {
        this.description = desc;
    }
}