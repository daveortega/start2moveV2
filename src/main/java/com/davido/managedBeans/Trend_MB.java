/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.managedBeans;

import com.davido.customObjects.comparisonItem;
import com.davido.customObjects.trendObject;
import com.davido.ejb.DbCrimerateTrendFacadeLocal;
import com.davido.ejb.DbHousebuyingTrendFacadeLocal;
import com.davido.ejb.DbLandpriceTrendFacadeLocal;
import com.davido.ejb.DbViewFacadeLocal;
import com.davido.entities.DbView;
import com.davido.utilities.Translator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.primefaces.PrimeFaces;

/**
 *
 * @author davidortega
 */
@Named(value = "trend_MB")
@ViewScoped
public class Trend_MB implements Serializable {

    @EJB
    private DbViewFacadeLocal dbViewFacade;
    @EJB
    private DbCrimerateTrendFacadeLocal dbCrimerateTrendFacade;
    @EJB
    private DbHousebuyingTrendFacadeLocal dbHousebuyingTrendFacade;
    @EJB
    private DbLandpriceTrendFacadeLocal dbLandpriceTrendFacade;

    // Language variables
    private String currentLang;
    private List<DbView> listViewEn;
    private List<DbView> listViewTr;

    // Business Variables
    private trendObject object;
    private String sourceComparison;
    private String warningMsg;

    public trendObject getObject() {
        return object;
    }

    public String getWarningMsg() {
        return warningMsg;
    }

    /**
     * Creates a new instance of Trend_MB
     */
    public Trend_MB() {
    }

    @PostConstruct
    private void init() {
        setUpLanguage();
        getPageContent();
        getTrends();
    }
    
    // <-------------------- Language control 

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
        listViewEn = dbViewFacade.findByName("viewPage", "trend");
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
        getTrends();
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
    
    // <--------------- Business Logic section --------------------------->

    // This method returns to the original page
    public void returnToOrigin() {
        try {
            if (sourceComparison.equals("suggestion")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("suggestion.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("selecTrend.xhtml");
            }
        } catch (IOException ex) {
            Logger.getLogger(Compare_MB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // This method calculate the trends for the selected suburb
    private void getTrends() {
        comparisonItem trendItem = (comparisonItem) FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("trendPostCode");
        sourceComparison = trendItem.getOrigin();
        object = buildObject(trendItem);
        Gson gsonBuilder = new GsonBuilder().create();

        if (object.isCrimeChart()) {
            int yAxisMax = 3;
            String[] Labels = object.getCrimeChartLabels();
            for (int i = 0; i < object.getCrimeChartLabels().length; i++) {
                if (!object.getCrimeChartValues()[i].equals("null")
                        && (int) Math.round(Double.parseDouble(object.getCrimeChartValues()[i])) > yAxisMax) {
                    yAxisMax = (int) Math.round(Double.parseDouble(object.getCrimeChartValues()[i]));
                }
                if (!object.getCrimeChartTrends()[i].equals("null")
                        && (int) Math.round(Double.parseDouble(object.getCrimeChartTrends()[i])) > yAxisMax) {
                    yAxisMax = (int) Math.round(Double.parseDouble(object.getCrimeChartTrends()[i]));
                }
            }

            for (int i = 0; i < object.getCrimeChartLabels().length; i++) {
                if (!object.getCrimeChartTrends()[i].equals("null")) {
                    object.getCrimeChartTrends()[i - 1] = object.getCrimeChartValues()[i - 1];
                    break;
                }
            }

            datasetLineObject dataObj1 = new datasetLineObject("Current", object.getCrimeChartValues(), false,
                    "rgb(75, 192, 192)", 0.1);
            String firstDataSet = gsonBuilder.toJson(dataObj1);
            datasetLineObject dataObj2 = new datasetLineObject("Trend", object.getCrimeChartTrends(), false,
                    "rgb(255, 99, 132)", 0.1);
            String secondDataSet = gsonBuilder.toJson(dataObj2);

            yAxisMax += 2;
            callJavaScript3Line("crimeTrend", Labels, firstDataSet, secondDataSet, false, "", yAxisMax, 1.0);

        }

        if (object.isHouseChart()) {
            int yAxisMax = 3;
            String[] Labels = object.getHouseChartLabels();

            for (int i = 0; i < object.getHouseChartLabels().length; i++) {
                if (!object.getHouseChartValues()[i].equals("null")
                        && (int) Math.round(Double.parseDouble(object.getHouseChartValues()[i])) > yAxisMax) {
                    yAxisMax = (int) Math.round(Double.parseDouble(object.getHouseChartValues()[i]));
                }
                if (!object.getHouseChartTrends()[i].equals("null")
                        && (int) Math.round(Double.parseDouble(object.getHouseChartTrends()[i])) > yAxisMax) {
                    yAxisMax = (int) Math.round(Double.parseDouble(object.getHouseChartTrends()[i]));
                }
            }

            for (int i = 0; i < object.getHouseChartLabels().length; i++) {
                if (!object.getHouseChartTrends()[i].equals("null")) {
                    object.getHouseChartTrends()[i - 1] = object.getHouseChartValues()[i - 1];
                    break;
                }
            }

            datasetLineObject dataObj1 = new datasetLineObject("Current", object.getHouseChartValues(), false,
                    "rgb(75, 192, 192)", 0.1);
            String firstDataSet = gsonBuilder.toJson(dataObj1);
            datasetLineObject dataObj2 = new datasetLineObject("Trend", object.getHouseChartTrends(), false,
                    "rgb(255, 99, 132)", 0.1);
            String secondDataSet = gsonBuilder.toJson(dataObj2);

            yAxisMax += 100000;
            callJavaScript3Line("houseTrend", Labels, firstDataSet, secondDataSet, false, "", 
                    (int) Math.ceil((double) (yAxisMax / 100000)) * 100000, 100000);
        }

        if (object.isLandChart()) {
            int yAxisMax = 3;
            String[] Labels = object.getLandChartLabels();

            for (int i = 0; i < object.getLandChartLabels().length; i++) {
                if (!object.getLandChartValues()[i].equals("null")
                        && (int) Math.round(Double.parseDouble(object.getLandChartValues()[i])) > yAxisMax) {
                    yAxisMax = (int) Math.round(Double.parseDouble(object.getLandChartValues()[i]));
                }
                if (!object.getLandChartTrends()[i].equals("null")
                        && (int) Math.round(Double.parseDouble(object.getLandChartTrends()[i])) > yAxisMax) {
                    yAxisMax = (int) Math.round(Double.parseDouble(object.getLandChartTrends()[i]));
                }
            }

            for (int i = 0; i < object.getLandChartLabels().length; i++) {
                if (!object.getLandChartTrends()[i].equals("null")) {
                    object.getLandChartTrends()[i - 1] = object.getLandChartValues()[i - 1];
                    break;
                }
            }

            datasetLineObject dataObj1 = new datasetLineObject("Current", object.getLandChartValues(), false,
                    "rgb(75, 192, 192)", 0.1);
            String firstDataSet = gsonBuilder.toJson(dataObj1);
            datasetLineObject dataObj2 = new datasetLineObject("Trend", object.getLandChartTrends(), false,
                    "rgb(255, 99, 132)", 0.1);
            String secondDataSet = gsonBuilder.toJson(dataObj2);

            yAxisMax += 100000;
            callJavaScript3Line("landTrend", Labels, firstDataSet, secondDataSet, false, "", 
                    (int) Math.ceil((double) (yAxisMax / 100000)) * 100000, 100000);
        }

        createWarnningMsg();
    }

    // This method builds the comparison objext
    private trendObject buildObject(comparisonItem item) {
        trendObject tmpTrdObj = new trendObject();
        tmpTrdObj.setNonEmptyObj(true);
        tmpTrdObj.setPostCode(item.getPostCode());
        tmpTrdObj.setPostCodeName(item.getPostCodeName());

        List<Object[]> listOfCrime = new ArrayList<>();
        listOfCrime = dbCrimerateTrendFacade.findTrends(item.getPostCode(), item.getPostCodeLine());
        if (!listOfCrime.isEmpty()) {
            tmpTrdObj.setCrimeChart(true);
            tmpTrdObj.setCrimeChartLabel(translateString("Crime Trends"));
            tmpTrdObj.setCrimeChartLabels(listOfCrime.stream().map(h -> h[2].toString()).toArray(String[]::new));
            tmpTrdObj.setCrimeChartValues(listOfCrime.stream().map(h -> h[3].toString()).toArray(String[]::new));
            tmpTrdObj.setCrimeChartTrends(listOfCrime.stream().map(h -> h[4].toString()).toArray(String[]::new));
        }

        List<Object[]> listOfRealEstate = new ArrayList<>();
        listOfRealEstate = dbHousebuyingTrendFacade.findTrends(item.getPostCode(), item.getPostCodeLine());
        if (!listOfRealEstate.isEmpty()) {
            tmpTrdObj.setHouseChart(true);
            tmpTrdObj.setHouseChartLabel(translateString("Real Estate Trend"));
            tmpTrdObj.setHouseChartLabels(listOfRealEstate.stream().map(h -> h[2].toString()).toArray(String[]::new));
            tmpTrdObj.setHouseChartValues(listOfRealEstate.stream().map(h -> h[3].toString()).toArray(String[]::new));
            tmpTrdObj.setHouseChartTrends(listOfRealEstate.stream().map(h -> h[4].toString()).toArray(String[]::new));
        }

        List<Object[]> listOfLandPrices = new ArrayList<>();
        listOfLandPrices = dbLandpriceTrendFacade.findTrends(item.getPostCode(), item.getPostCodeLine());
        if (!listOfLandPrices.isEmpty()) {
            tmpTrdObj.setLandChart(true);
            tmpTrdObj.setLandChartLabel(translateString("Land Price Trend"));
            tmpTrdObj.setLandChartLabels(listOfLandPrices.stream().map(h -> h[2].toString()).toArray(String[]::new));
            tmpTrdObj.setLandChartValues(listOfLandPrices.stream().map(h -> h[3].toString()).toArray(String[]::new));
            tmpTrdObj.setLandChartTrends(listOfLandPrices.stream().map(h -> h[4].toString()).toArray(String[]::new));
        }

        return tmpTrdObj;
    }

    // Call the JavaScript function for building the chart
    private void callJavaScript3Line(String JSObject, String[] Labels, String datasetOne, String datasetTwo, boolean dataSet3, String datasetThree,
            int yAxisMax, double stepSize) {
        PrimeFaces.current().executeScript("Process3LineChart('" + JSObject + "','" + String.join(",", Labels) + "','" + datasetOne + "','" + datasetTwo
                + "','" + dataSet3 + "','" + datasetThree + "','" + yAxisMax + "','" + stepSize + "');");
    }

    // Creates the warnning when required
    private void createWarnningMsg() {
        warningMsg = "";
        if (!object.isCrimeChart() && !object.isHouseChart() && !object.isLandChart()) {
            warningMsg = translateString("Start2move does not have trend information for the selected post code.");
        } else if (!object.isCrimeChart() && !object.isHouseChart()) {
            warningMsg = translateString("Start2move does not have crime and house prices trend information for the selected post code.");
        } else if (!object.isHouseChart() && !object.isLandChart()) {
            warningMsg = translateString("Start2move does not have house and land prices trend information for the selected post code.");
        } else if (!object.isCrimeChart() && !object.isLandChart()) {
            warningMsg = translateString("Start2move does not have crime and land prices trend information for the selected post code.");
        } else if (!object.isCrimeChart()) {
            warningMsg = translateString("Start2move does not have crime trend information for the selected post code.");
        } else if (!object.isHouseChart()) {
            warningMsg = translateString("Start2move does not have house prices trend information for the selected post code.");
        } else if (!object.isLandChart()) {
            warningMsg = translateString("Start2move does not have land prices trend information for the selected post code.");
        }
    }

    // This is a nested class to containt a temporal object.
    class datasetLineObject {

        public String label;
        public String[] data;
        public boolean fill;
        public String borderColor;
        public Double lineTension;

        public datasetLineObject() {
        }

        public datasetLineObject(String label, String[] data, boolean fill, String borderColor, Double lineTension) {
            this.label = label;
            this.data = data;
            this.fill = fill;
            this.borderColor = borderColor;
            this.lineTension = lineTension;
        }

    }

}
