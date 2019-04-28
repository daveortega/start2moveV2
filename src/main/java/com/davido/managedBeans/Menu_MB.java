/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.managedBeans;

import com.davido.ejb.DbViewFacadeLocal;
import com.davido.entities.DbView;
import com.davido.utilities.Translator;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author davidortega
 */
@Named(value = "menu_MB")
@SessionScoped
public class Menu_MB implements Serializable {

    @EJB
    private DbViewFacadeLocal dbViewFacade;

    // Language variables
    private String currentLang;
    List<DbView> listViewEn;
    List<DbView> listViewTr;

    /**
     * Creates a new instance of Menu_MB
     */
    public Menu_MB() {
    }

    @PostConstruct
    private void init() {
        Language_MB languageBean = FacesContext.getCurrentInstance().getApplication().
                evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{language_MB}", Language_MB.class);
        currentLang = languageBean.getLanguage();
        getPageContent();
    }

    //This method translates the current English object
    public void translate(String lang) {
        if (lang.equals("EN")) {
            listViewTr = new ArrayList<>();
            currentLang = lang;
            listViewTr = listViewEn;
        } else if (!currentLang.equals(lang)) {
            currentLang = lang;
            listViewTr = new ArrayList<>();
            Translator translator = new Translator();
            for (DbView item : listViewEn) {
                try {
                    listViewTr.add(new DbView(item.getViewId(), item.getViewPage(), item.getViewPageSection(),
                            item.getViewFieldName(), translator.translate(lang, item.getViewFieldContent())));
                } catch (IOException | GeneralSecurityException ex) {
                    Logger.getLogger(Index_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    //This method prepares the information to be rendered on the view.
    private void getPageContent() {
        listViewEn = dbViewFacade.findByName("viewPage", "menu");
        if (currentLang.equals("EN")) {
            listViewTr = listViewEn;
        } else {
            translate(currentLang);
        }
    }

    //This method returns the field in the obj
    public String getFieldContent(String section, String fieldName) {
        for (DbView row : listViewTr) {
            if (row.getViewPageSection().equals(section) && row.getViewFieldName().equals(fieldName)) {
                return row.getViewFieldContent();
            }
        }
        return "";
    }

}
