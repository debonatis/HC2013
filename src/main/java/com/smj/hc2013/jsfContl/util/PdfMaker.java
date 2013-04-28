/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContl.util;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.smj.hc2013.model.OrdreBestilling;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 *Getters and setters are not described
 * @author deb
 */
public class PdfMaker {
    
    /**
     * Generates a pdf file for download
     * @param d
     * @param filnavn
     * @param path
     */
    public static void makePdf(List<OrdreBestilling> d, String filnavn, String path){
    
    Document document = new Document();

        try {
                     
            PdfWriter.getInstance(document, new FileOutputStream(new File(path, filnavn)));
    
            document.open();

            PdfPTable table = new PdfPTable(6);
            
             PdfPCell cell7 = new PdfPCell(new Paragraph("Name on Dish: "));
            PdfPCell cell8 = new PdfPCell(new Paragraph("Price on Dish: "));
            PdfPCell cell9 = new PdfPCell(new Paragraph("Id on Dish: "));
            PdfPCell cell10 = new PdfPCell(new Paragraph("Quantity: "));
            PdfPCell cell11 = new PdfPCell(new Paragraph("Delvidery Address"));
           PdfPCell cell12 = new PdfPCell(new Paragraph("Brønnøy Regnr: "));
            

            table.addCell(cell7);
            table.addCell(cell8);
            table.addCell(cell9);
            table.addCell(cell10);
            table.addCell(cell11);
            table.addCell(cell12);
            
            for(OrdreBestilling k : d){

            PdfPCell cell1 = new PdfPCell(new Paragraph(k.getRett().getNavn()));
            PdfPCell cell2 = new PdfPCell(new Paragraph(k.getRett().getPris().toString()));
            PdfPCell cell3 = new PdfPCell(new Paragraph(k.getRett().getRettnummer()));
            PdfPCell cell4 = new PdfPCell(new Paragraph(((Integer)k.getAntall()).toString()));
            PdfPCell cell5 = new PdfPCell(new Paragraph(k.getLeveringsAdresse()));
           PdfPCell cell6 = new PdfPCell(new Paragraph("BRID: "+k.getSelskap()));
            

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            }
           

            document.add(table);

            document.close();
        } catch(Exception e){

        }
    }
    
}
