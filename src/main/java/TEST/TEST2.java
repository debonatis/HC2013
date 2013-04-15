/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TEST;

import com.smj.hc2013.jsfContCust.mobileUi.OrdreUtkjoring;
import com.smj.hc2013.model.Bruker;
import com.smj.hc2013.model.Ordre;
import com.smj.hc2013.model.Ordretabell;
import com.smj.hc2013.model.Retter;
import com.smj.hc2013.session.BrukerFacade;
import com.smj.hc2013.session.OrdreFacade;
import com.smj.hc2013.session.OrdretabellFacade;
import com.smj.hc2013.session.RetterFacade;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;

/**
 *
 * @author deb
 */
public class TEST2 {

    /**
     * @param args the command line arguments
     */
    
    
    private static  List<OrdreUtkjoring> utListe;
    private static List<OrdreUtkjoring> utListeSt;
    
    private static List<Retter> retterL;
    private static List<Ordre> ordreL;
    private static List<Ordretabell> ordreTabellL;
    private static List<Bruker> brukerL;
    private static OrdreUtkjoring selected = new OrdreUtkjoring();
    private static OrdreUtkjoring setter = new OrdreUtkjoring();
    @EJB
    private static RetterFacade retterFacade;
    @EJB
    private static OrdretabellFacade ordretabellFacade;
    @EJB
    private static OrdreFacade ordreFacade;
    @EJB
    private static BrukerFacade brukerFacade;
    private static Ordre ordre = new Ordre();
    private static Ordretabell ordreT = new Ordretabell();
    private static Bruker bruker = new Bruker();
    private static Retter rett = new Retter();


    public static void main(String[] args) {
        String PK = "[13]jdsad.fr.fr.fr.2013";
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(PK);
        for (int n; m.find();) {
            n = Integer.parseInt(m.group());
            System.out.println(n);


        }
        UUID lol = UUID.randomUUID();
        System.out.println(lol.toString());
        System.out.println(lol);
        
        init2();
        
        for(OrdreUtkjoring Ou: utListeSt){
            System.out.println(Ou.getBruker().getFornavn());
        }
            
       
    }
    
     public static  OrdreUtkjoring getSetter() {
        return setter;
    }

    public  void  setSetter(OrdreUtkjoring setter) {
        TEST2.setter = setter;
    }
    
    public static void init2(){
         brukerL = brukerFacade.findAll();
        ordreL = ordreFacade.findAll();
        ordreTabellL = ordretabellFacade.findAll();
        retterL = retterFacade.findAll();
        for (Ordretabell ot : ordreTabellL) {
            ordreT = ot;
            for (Ordre o : ordreL) {
                if (ot.getOrdretabellPK().getSalgsnummer().equalsIgnoreCase(o.getOrdrePK().getSalgsnummer())) {
                    ordre = o;
                }
                for (Bruker b : brukerL) {
                    if (ot.getOrdretabellPK().getKundebrukernavn().equalsIgnoreCase(b.getBrukernavn())) {
                        bruker = b;
                    }
                    for (Retter r : retterL) {
                        if (ot.getRettnummer().equalsIgnoreCase(r.getRettnummer())) {
                            rett = r;
                        }
                    }

                }
            }
            getSetter().setBruker(bruker);
            getSetter().setOrdre(ordre);
            getSetter().setOrdreTabell(ordreT);
            getSetter().setRett(rett);
            boolean add = utListeSt.add(getSetter());
        }

    
    }

    
    public void init() {
        brukerL = brukerFacade.findAll();
        ordreL = ordreFacade.findAll();
        ordreTabellL = ordretabellFacade.findAll();
        retterL = retterFacade.findAll();
        for (Ordretabell ot : ordreTabellL) {
            ordreT = ot;
            for (Ordre o : ordreL) {
                if (ot.getOrdretabellPK().getSalgsnummer().equalsIgnoreCase(o.getOrdrePK().getSalgsnummer())) {
                    ordre = o;
                }
                for (Bruker b : brukerL) {
                    if (ot.getOrdretabellPK().getKundebrukernavn().equalsIgnoreCase(b.getBrukernavn())) {
                        bruker = b;
                    }
                    for (Retter r : retterL) {
                        if (ot.getRettnummer().equalsIgnoreCase(r.getRettnummer())) {
                            rett = r;
                        }
                    }

                }
            }
            getSetter().setBruker(bruker);
            getSetter().setOrdre(ordre);
            getSetter().setOrdreTabell(ordreT);
            getSetter().setRett(rett);
            boolean add = utListeSt.add(getSetter());
        }

    }
}
