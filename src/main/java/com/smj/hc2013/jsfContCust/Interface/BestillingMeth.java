/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust.Interface;

import com.smj.hc2013.model.Ordre;
import com.smj.hc2013.model.OrdreBestilling;
import com.smj.hc2013.model.Retter;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author deb
 */
public interface BestillingMeth extends Serializable {

    String[] getBridList();

    StreamedContent getFile();

    List<Retter> getMaal();

    Ordre getOrdre();

    List<Retter> getRetter();

    DualListModel<Retter> getRetterPick();

    List<OrdreBestilling> getSettAntallList();

    UUID getUUID();

    @PostConstruct
    void init();

    boolean isMailVe();

    boolean isSkip();

    String onFlowProcess(FlowEvent event);

    void onTransfer(TransferEvent event);

    void prepareCreate();

    void removeFromsetAntalle(OrdreBestilling item);

    void savePick() throws Exception;

    void setMaal(List<Retter> maal);

    void setMailVe(boolean MailVe);

    void setOrdre(Ordre ordre);

    void setRetter(List<Retter> retter);

    void setRetterPick(DualListModel<Retter> retterPick);

    void setSettAntallList(List<OrdreBestilling> settAntallList);

    void setSkip(boolean skip);
    
}
