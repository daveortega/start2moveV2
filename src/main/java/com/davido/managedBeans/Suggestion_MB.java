/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.managedBeans;

import com.davido.customObjects.comparisonItem;
import com.davido.customObjects.resultantItem;
import com.davido.ejb.DbHospitalFacadeLocal;
import com.davido.ejb.DbSchoolFacadeLocal;
import com.davido.ejb.DbViewFacadeLocal;
import com.davido.ejb.DbbusStopFacadeLocal;
import com.davido.ejb.DbcrimeRateFacadeLocal;
import com.davido.ejb.DbhouseBuyingFacadeLocal;
import com.davido.ejb.DbhouseRentFacadeLocal;
import com.davido.ejb.DblandPriceFacadeLocal;
import com.davido.ejb.DbtrainStationFacadeLocal;
import com.davido.entities.DbHospital;
import com.davido.entities.DbSchool;
import com.davido.entities.DbView;
import com.davido.entities.DbbusStop;
import com.davido.entities.DbtrainStation;
import com.davido.utilities.Translator;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author davidortega
 */
@Named(value = "suggestion_MB")
@ViewScoped
public class Suggestion_MB implements Serializable {

    @EJB
    private DbViewFacadeLocal dbViewFacade;
    @EJB
    private DbbusStopFacadeLocal dbbusStopFacade;
    @EJB
    private DbcrimeRateFacadeLocal dbcrimeRateFacade;
    @EJB
    private DbHospitalFacadeLocal dbHospitalFacade;
    @EJB
    private DbhouseRentFacadeLocal dbhouseRentFacade;
    @EJB
    private DbhouseBuyingFacadeLocal dbhouseBuyingFacade;
    @EJB
    private DblandPriceFacadeLocal dblandPriceFacade;
    @EJB
    private DbtrainStationFacadeLocal dbtrainStationFacade;
    @EJB
    private DbSchoolFacadeLocal DbSchoolFacade;

    // Language variables
    private String currentLang;
    private List<DbView> listViewEn;
    private List<DbView> listViewTr;

    // Logic variables
    private List<resultantItem> listOfSuggestions;
    private String header1;
    private String header2;
    private String header3;
    private resultantItem currentItem;

    //Business logic variables
    private boolean showMainTable;
    private boolean showResults;
    private List<DbSchool> listOfSchools;
    private boolean crimeChart;
    private String crimeAvg;
    private List<DbtrainStation> listOfTrains;
    private List<DbbusStop> listOfBus;
    private List<DbHospital> listOfHospitals;
    private boolean rentChart;
    private boolean realChart;
    private boolean landChart;
    private boolean enableComparison;

    public List<resultantItem> getListOfSuggestions() {
        return listOfSuggestions;
    }

    public void setListOfSuggestions(List<resultantItem> listOfSuggestions) {
        this.listOfSuggestions = listOfSuggestions;
    }

    public String getHeader1() {
        return header1;
    }

    public String getHeader2() {
        return header2;
    }

    public String getHeader3() {
        return header3;
    }

    public boolean isShowMainTable() {
        return showMainTable;
    }

    public boolean isShowResults() {
        return showResults;
    }

    public resultantItem getCurrenItem() {
        return currentItem;
    }

    public List<DbSchool> getListOfSchools() {
        return listOfSchools;
    }

    public boolean isCrimeChart() {
        return crimeChart;
    }

    public List<DbtrainStation> getListOfTrains() {
        return listOfTrains;
    }

    public List<DbbusStop> getListOfBus() {
        return listOfBus;
    }

    public List<DbHospital> getListOfHospitals() {
        return listOfHospitals;
    }

    public boolean isRentChart() {
        return rentChart;
    }

    public boolean isRealChart() {
        return realChart;
    }

    public boolean isLandChart() {
        return landChart;
    }

    public String getCrimeAvg() {
        return crimeAvg;
    }

    public boolean isEnableComparison() {
        return enableComparison;
    }

    /**
     * @Constructors
     */
    public Suggestion_MB() {
    }

    /**
     * @ClassMethods
     */
    @PostConstruct
    private void init() {
        setUpLanguage();
        getPageContent();
        getSuggestions();
        enableComparison();
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
        listViewEn = dbViewFacade.findByName("viewPage", "suggestion");
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
        getSuggestions();
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

    /*
    * @Business Methods  
     */
    // This method gets the suggestions
    public void getSuggestions() {
        List<resultantItem> listOfSuggesTmp = (List<resultantItem>) FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("suggestedPostCodes");
        if (listOfSuggesTmp != null) {
            listOfSuggestions = listOfSuggesTmp;
            header1 = translateString(listOfSuggestions.get(0).getQuestionOneName());
            header2 = translateString(listOfSuggestions.get(0).getQuestionTwoName());
            header3 = translateString(listOfSuggestions.get(0).getQuestionThreeName());
        }
        showMainTable = true;
        showResults = false;
    }

    // This method gets the result information
    public void processItem(resultantItem item) {
        currentItem = item;
        showMainTable = false;
        showResults = true;

        listOfSchools = new ArrayList<>();
        listOfSchools = DbSchoolFacade.findByPostcodeAndLine(Integer.parseInt(item.getPostCode()), Integer.parseInt(item.getPostCodeLine()));

        List<Object[]> listOfCrime = new ArrayList<>();
        listOfCrime = dbcrimeRateFacade.findByPostcodeAndLine(item.getPostCode(), item.getPostCodeLine());
        if (!listOfCrime.isEmpty()) {
            crimeChart = true;
            String Label = translateString("Crime Rate");
            String[] Labels = listOfCrime.stream().map(h -> h[2].toString()).toArray(String[]::new);
            String[] Values = listOfCrime.stream().map(h -> h[3].toString()).toArray(String[]::new);
            Double avg = 0.0;
            for (Object[] item1 : listOfCrime) {
                avg = avg + Double.parseDouble(item1[3].toString());
            }
            avg = avg / listOfCrime.size();
            DecimalFormat df2 = new DecimalFormat(".#");
            crimeAvg = df2.format(avg);
            callJavaScript("crimeChart", Label, Labels, Values);
        }

        listOfHospitals = new ArrayList<>();
        listOfHospitals = dbHospitalFacade.findByPostcodeAndLine(Integer.parseInt(item.getPostCode()), Integer.parseInt(item.getPostCodeLine()));
        listOfBus = new ArrayList<>();
        listOfBus = dbbusStopFacade.findByPostcodeAndLine(Integer.parseInt(item.getPostCode()), Integer.parseInt(item.getPostCodeLine()));
        listOfTrains = new ArrayList<>();
        listOfTrains = dbtrainStationFacade.findByPostcodeAndLine(Integer.parseInt(item.getPostCode()), Integer.parseInt(item.getPostCodeLine()));

        List<Object[]> listOfRent = new ArrayList<>();
        listOfRent = dbhouseRentFacade.findByPostcodeAndLine(item.getPostCode(), item.getPostCodeLine());
        if (!listOfRent.isEmpty()) {
            rentChart = true;
            String Label = translateString("Rent Prices/week");
            String[] Labels = listOfRent.stream().map(h -> h[2].toString()).toArray(String[]::new);
            String[] Values = listOfRent.stream().map(h -> h[3].toString()).toArray(String[]::new);
            callJavaScript("rentChart", Label, Labels, Values);
        }

        List<Object[]> listOfRealEstate = new ArrayList<>();
        listOfRealEstate = dbhouseBuyingFacade.findByPostcodeAndLine(item.getPostCode(), item.getPostCodeLine());
        if (!listOfRealEstate.isEmpty()) {
            realChart = true;
            String Label = translateString("Avg. Real Estate Prices");
            String[] Labels = listOfRealEstate.stream().map(h -> h[2].toString()).toArray(String[]::new);
            String[] Values = listOfRealEstate.stream().map(h -> h[3].toString()).toArray(String[]::new);
            callJavaScript("realEstateChart", Label, Labels, Values);
        }

        List<Object[]> listOfLandPrices = new ArrayList<>();
        listOfLandPrices = dblandPriceFacade.findByPostcodeAndLine(item.getPostCode(), item.getPostCodeLine());
        if (!listOfLandPrices.isEmpty()) {
            landChart = true;
            String Label = translateString("Avg. Land Prices");
            String[] Labels = listOfLandPrices.stream().map(h -> h[2].toString()).toArray(String[]::new);
            String[] Values = listOfLandPrices.stream().map(h -> h[3].toString()).toArray(String[]::new);
            callJavaScript("LandChart", Label, Labels, Values);
        }
    }

    private void callJavaScript(String JSObject, String Label, String[] Labels, String[] Values) {
        PrimeFaces.current().executeScript("ProcessBarChart('" + JSObject + "','" + Label + "','" + String.join(",", Labels)
                + "','" + String.join(",", Values) + "');");
    }
    
    public void backToList(){
        currentItem = null;
        showMainTable = true;
        showResults = false;
    }

    public void enableComparison() {
        enableComparison = true;
        if (listOfSuggestions.stream().filter(x -> x.isChecked()).count() > 1) {
            enableComparison = false;
        }
    }

    public void comparePostCodes() {
        List<comparisonItem> listOfComparison = listOfSuggestions
                                                .stream()
                                                .filter(x -> x.isChecked())
                                                .map(p -> new comparisonItem(p.getPostCode(), p.getPostCodeLine(), 
                                                        p.getPostCodeName(), "suggestion"))
                                                .collect(Collectors.toList());

        // Create the list session variable and redirect
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("comparePostCodes", listOfComparison);
            FacesContext.getCurrentInstance().getExternalContext().redirect("compare.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Survey_MB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
