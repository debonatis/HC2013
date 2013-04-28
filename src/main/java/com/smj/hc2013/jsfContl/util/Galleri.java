/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

/**
 *
 * @author Martin
 */
import com.smj.hc2013.model.GalleriaDish;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct; 
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author deb
 */
@SessionScoped
@Named("galleria")
public class Galleri {

    private List<GalleriaDish> images;
    private String[] names = {null, "HealtyCatering\n" +"\n" +"Bæst matn i by'n!",
        "Teriyakibiff", 
        "Klubb m/dopp", 
        "Finnbiff", 
        "Red curry beef", "lol", "lol"};
    private String[] description = {null, "HealtyCatering er et cateringselskap som leverer mat i Trondheim og omegn.\n" +
"Hos oss kan du velge blandt fastfood, norske tradisjonelle retter, m.m. \n" +"\n" +
"Du ringer - vi bringer.",
        "Teriyakibiff med teriyakisaus som minner om en søt soyasaus", 
        "namnam", 
        "Dæsken så godt", 
        "Fale godt ja", "lol", "lol"};

    /**
     *
     */
    @PostConstruct
    public void init(){
        images = new ArrayList<GalleriaDish>();
        for(int i = 2; i<= 7; i++){
            images.add(new GalleriaDish(names[i], description[i], "galleria" + i + ".jpg"));
            
        }
    }

    /**
     *
     * @return
     */
    public List<GalleriaDish> getImages() {
        return images;
    }
}
