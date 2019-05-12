/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.managedBeans;

import com.davido.customObjects.comparisonItem;
import com.davido.ejb.DbViewFacadeLocal;
import com.davido.ejb.DbpostCodeFacadeLocal;
import com.davido.entities.DbView;
import com.davido.entities.DbpostCode;
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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author davidortega
 */
@Named(value = "selectTrend_MB")
@ViewScoped
public class SelectTrend_MB implements Serializable {

    @EJB
    private DbpostCodeFacadeLocal dbpostCodeFacade;
    @EJB
    private DbViewFacadeLocal dbViewFacade;

    // Language variables
    private String currentLang;
    private List<DbView> listViewEn;
    private List<DbView> listViewTr;

    // Business variables
    private String postCode1;
    private List<DbpostCode> listOfPostCode1;
    private boolean errorPreference1;

    public String getPostCode1() {
        return postCode1;
    }

    public void setPostCode1(String postCode1) {
        this.postCode1 = postCode1;
    }

    public List<DbpostCode> getListOfPostCode1() {
        return listOfPostCode1;
    }

    public boolean isErrorPreference1() {
        return errorPreference1;
    }

    /**
     * Creates a new instance of SelectTrend_MB
     */
    public SelectTrend_MB() {
    }

    @PostConstruct
    private void init() {
        setUpLanguage();
        getPageContent();
        getPostCodes();
    }

    // Create the global variable for the language for view scope beans
    private void setUpLanguage() {
        Language_MB languageBean = FacesContext.getCurrentInstance().getApplication().
                evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{language_MB}", Language_MB.class);
        if (currentLang == null) {
            currentLang = languageBean.getLanguage();
        }
    }

    // This method prepares the information to be rendered on the view.
    private void getPageContent() {
        listViewEn = dbViewFacade.findByName("viewPage", "selectTrend");
        if (currentLang.equals("EN")) {
            listViewTr = new ArrayList<>();
            listViewTr = listViewEn;
        } else {
            listViewTr = new ArrayList<>();
            listViewTr = translateList(listViewEn, currentLang);
        }
    }

    // Translate a list
    private List<DbView> translateList(List<DbView> listViewEnTmp, String targetLang) {
        List<DbView> listViewTrTmp = new ArrayList<>();
        Translator translator = new Translator();
        for (DbView item : listViewEnTmp) {
            try {
                listViewTrTmp.add(new DbView(item.getViewId(), item.getViewPage(), item.getViewPageSection(),
                        item.getViewFieldName(), translator.translate(targetLang, item.getViewFieldContent())));
            } catch (IOException | GeneralSecurityException ex) {
                Logger.getLogger(Index_MB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listViewTrTmp;
    }

    //This method translates the current English object
    public void translate(String lang) {
        if (lang.equals("EN")) {
            listViewTr = new ArrayList<>();
            currentLang = lang;
            listViewTr = listViewEn;
        } else if (!currentLang.equals(lang)) {
            listViewTr = new ArrayList<>();
            currentLang = lang;
            listViewTr = translateList(listViewEn, currentLang);
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

    // This method translate a particular string
    public String translateString(String text) {
        Language_MB languageBeanTmp = FacesContext.getCurrentInstance().getApplication().
                evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{language_MB}", Language_MB.class);
        if (!languageBeanTmp.getLanguage().equals("EN")) {
            Translator translator = new Translator();
            try {
                return translator.translate(languageBeanTmp.getLanguage(), text);
            } catch (IOException | GeneralSecurityException ex) {
                Logger.getLogger(Survey_MB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return text;
    }

    private void getPostCodes() {
        postCode1 = null;
        listOfPostCode1 = dbpostCodeFacade.findAllRegional();
    }
    
    // This method filters the preferences in the intial page render
    public void processPreferences(String prefId) {
        
    }

    // Save preferences and start survey
    public void savePreferences() {
        errorPreference1 = false;
        if (postCode1 == null) {
            errorPreference1 = true;
        }

        if (!errorPreference1) {
            String[] postArr1 = postCode1.split(",");
            String postCode1Name = "";
            for (DbpostCode item : listOfPostCode1) {
                if (item.getDbpostCodePK().getPostCodeId() == Integer.parseInt(postArr1[0])
                        && item.getDbpostCodePK().getPostCodeLine() == Integer.parseInt(postArr1[1])) {
                    postCode1Name = item.getPostCodeName();
                    break;
                }
            }

            comparisonItem itemToTrend = new comparisonItem(postArr1[0], postArr1[1], postCode1Name, "selectTrend");
            // Create the list session variable and redirect
            try {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("trendPostCode", itemToTrend);
                FacesContext.getCurrentInstance().getExternalContext().redirect("trend.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Survey_MB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
