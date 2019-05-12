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
import com.davido.entities.DbpostCodePK;
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
@Named(value = "selectComparison_MB")
@ViewScoped
public class SelectComparison_MB implements Serializable {

    @EJB
    private DbpostCodeFacadeLocal dbpostCodeFacade;
    @EJB
    private DbViewFacadeLocal dbViewFacade;

    // Language variables
    private String currentLang;
    private List<DbView> listViewEn;
    private List<DbView> listViewTr;

    // Business variables
    private boolean activePreference1;
    private boolean activePreference2;
    private boolean activePreference3;
    private List<DbpostCode> listOfPostCode1;
    private List<DbpostCode> listOfPostCode2;
    private List<DbpostCode> listOfPostCode3;
    private String postCode1;
    private String postCode2;
    private String postCode3;
    private boolean errorPreference1;
    private boolean errorPreference2;
    private boolean errorPreference3;

    public boolean isActivePreference1() {
        return activePreference1;
    }

    public boolean isActivePreference2() {
        return activePreference2;
    }

    public boolean isActivePreference3() {
        return activePreference3;
    }

    public List<DbpostCode> getListOfPostCode1() {
        return listOfPostCode1;
    }

    public List<DbpostCode> getListOfPostCode2() {
        return listOfPostCode2;
    }

    public List<DbpostCode> getListOfPostCode3() {
        return listOfPostCode3;
    }

    public String getPostCode1() {
        return postCode1;
    }

    public void setPostCode1(String postCode1) {
        this.postCode1 = postCode1;
    }

    public String getPostCode2() {
        return postCode2;
    }

    public void setPostCode2(String postCode2) {
        this.postCode2 = postCode2;
    }

    public String getPostCode3() {
        return postCode3;
    }

    public void setPostCode3(String postCode3) {
        this.postCode3 = postCode3;
    }

    public boolean isErrorPreference1() {
        return errorPreference1;
    }

    public boolean isErrorPreference2() {
        return errorPreference2;
    }

    public boolean isErrorPreference3() {
        return errorPreference3;
    }

    /**
     * Creates a new instance of selectComparison_MB
     */
    public SelectComparison_MB() {
    }

    /**
     * @ClassMethods
     */
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
        listViewEn = dbViewFacade.findByName("viewPage", "selectPostCode");
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

    // This method get the postCodes for the fisrt list
    private void getPostCodes() {
        postCode1 = null;
        postCode2 = null;
        postCode3 = null;
        activePreference1 = false;
        activePreference2 = true;
        activePreference3 = true;
        listOfPostCode1 = dbpostCodeFacade.findAllRegional();
        listOfPostCode2 = new ArrayList<>();
        listOfPostCode3 = new ArrayList<>();
    }

    // This method filters the preferences in the intial page render
    public void processPreferences(String prefId) {
        switch (prefId) {
            case "one":
                String[] postArr1 = postCode1.split(",");
                DbpostCode postObj1 = new DbpostCode(new DbpostCodePK(Integer.parseInt(postArr1[0]), Integer.parseInt(postArr1[1])));
                for (DbpostCode item : listOfPostCode1) {
                    if (!item.equals(postObj1) && !listOfPostCode2.contains(postObj1)) {
                        listOfPostCode2.add(item);
                    }
                }
                activePreference1 = true;
                activePreference2 = false;
                break;
            case "two":
                String[] postArr2 = postCode2.split(",");
                DbpostCode postObj2 = new DbpostCode(new DbpostCodePK(Integer.parseInt(postArr2[0]), Integer.parseInt(postArr2[1])));
                for (DbpostCode item : listOfPostCode2) {
                    if (!item.equals(postObj2) && !listOfPostCode3.contains(postObj2)) {
                        listOfPostCode3.add(item);
                    }
                }
                activePreference2 = true;
                activePreference3 = false;
                break;
            default:
                break;
        }
    }

    // This method resets the preferences
    public void resetPreferences() {
        listOfPostCode2 = new ArrayList<>();
        listOfPostCode3 = new ArrayList<>();
        activePreference1 = false;
        activePreference2 = true;
        activePreference3 = true;
        errorPreference1 = false;
        errorPreference2 = false;
        errorPreference3 = false;
        postCode1 = null;
        postCode2 = null;
        postCode3 = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("comparePostCodes", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("suggestedPostCodes", null);
    }

    // Save preferences and start survey
    public void savePreferences() {
        boolean error = false;
        errorPreference1 = false;
        errorPreference2 = false;
        errorPreference3 = false;

        if (postCode1 == null) {
            errorPreference1 = true;
            error = true;
        }
        if (postCode2 == null) {
            errorPreference2 = true;
            error = true;
        }

        if (!error) {
            List<comparisonItem> listOfComparison = new ArrayList<>();
            String[] postArr1 = postCode1.split(",");
            String postCode1Name = "";
            for (DbpostCode item : listOfPostCode1) {
                if (item.getDbpostCodePK().getPostCodeId() == Integer.parseInt(postArr1[0])
                        && item.getDbpostCodePK().getPostCodeLine() == Integer.parseInt(postArr1[1])) {
                    postCode1Name = item.getPostCodeName();
                    break;
                }
            }
            String[] postArr2 = postCode2.split(",");
            String postCode2Name = "";
            for (DbpostCode item : listOfPostCode1) {
                if (item.getDbpostCodePK().getPostCodeId() == Integer.parseInt(postArr2[0])
                        && item.getDbpostCodePK().getPostCodeLine() == Integer.parseInt(postArr2[1])) {
                    postCode2Name = item.getPostCodeName();
                    break;
                }
            }

            listOfComparison.add(new comparisonItem(postArr1[0], postArr1[1], postCode1Name, "comparison"));
            listOfComparison.add(new comparisonItem(postArr2[0], postArr2[1], postCode2Name, "comparison"));

            if (postCode3 != null) {
                String[] postArr3 = postCode3.split(",");
                String postCode3Name = "";
                for (DbpostCode item : listOfPostCode1) {
                    if (item.getDbpostCodePK().getPostCodeId() == Integer.parseInt(postArr3[0])
                            && item.getDbpostCodePK().getPostCodeLine() == Integer.parseInt(postArr3[1])) {
                        postCode3Name = item.getPostCodeName();
                        break;
                    }
                }
                listOfComparison.add(new comparisonItem(postArr3[0], postArr3[1], postCode3Name, "comparison"));
            }

            try {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("comparePostCodes", listOfComparison);
                FacesContext.getCurrentInstance().getExternalContext().redirect("compare.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Survey_MB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
