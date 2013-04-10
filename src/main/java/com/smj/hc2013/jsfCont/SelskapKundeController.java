package com.smj.hc2013.jsfCont;

import com.smj.hc2013.model.SelskapKunde;
import com.smj.hc2013.jsfContl.util.JsfUtil;
import com.smj.hc2013.jsfContl.util.PaginationHelper;
import com.smj.hc2013.session.SelskapKundeFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "selskapKundeController")
@SessionScoped
public class SelskapKundeController implements Serializable {

    private SelskapKunde current;
    private DataModel items = null;
    @EJB
    private com.smj.hc2013.session.SelskapKundeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public SelskapKundeController() {
    }

    public SelskapKunde getSelected() {
        if (current == null) {
            current = new SelskapKunde();
            current.setSelskapKundePK(new com.smj.hc2013.model.SelskapKundePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private SelskapKundeFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (SelskapKunde) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new SelskapKunde();
        current.setSelskapKundePK(new com.smj.hc2013.model.SelskapKundePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SelskapKundeCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (SelskapKunde) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SelskapKundeUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (SelskapKunde) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SelskapKundeDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = SelskapKunde.class)
    public static class SelskapKundeControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SelskapKundeController controller = (SelskapKundeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "selskapKundeController");
            return controller.ejbFacade.find(getKey(value));
        }

        com.smj.hc2013.model.SelskapKundePK getKey(String value) {
            com.smj.hc2013.model.SelskapKundePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.smj.hc2013.model.SelskapKundePK();
            key.setBrukernavn(values[0]);
            key.setSelskapnr(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.smj.hc2013.model.SelskapKundePK value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value.getBrukernavn());
            sb.append(SEPARATOR);
            sb.append(value.getSelskapnr());
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SelskapKunde) {
                SelskapKunde o = (SelskapKunde) object;
                return getStringKey(o.getSelskapKundePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + SelskapKunde.class.getName());
            }
        }
    }
}
