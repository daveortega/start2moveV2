/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.managedBeans;

import com.davido.customObjects.comparisonItem;
import com.davido.customObjects.comparisonObject;
import com.davido.ejb.DbHospitalFacadeLocal;
import com.davido.ejb.DbSchoolFacadeLocal;
import com.davido.ejb.DbViewFacadeLocal;
import com.davido.ejb.DbbusStopFacadeLocal;
import com.davido.ejb.DbcrimeRateFacadeLocal;
import com.davido.ejb.DbhouseBuyingFacadeLocal;
import com.davido.ejb.DbhouseRentFacadeLocal;
import com.davido.ejb.DblandPriceFacadeLocal;
import com.davido.ejb.DbtrainStationFacadeLocal;
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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author davidortega
 */
@Named(value = "compare_MB")
@ViewScoped
public class Compare_MB implements Serializable {

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

    // Business Logic Vairables
    private comparisonObject object1;
    private comparisonObject object2;
    private comparisonObject object3;
    private String sourceComparison;

    public comparisonObject getObject1() {
        return object1;
    }

    public comparisonObject getObject2() {
        return object2;
    }

    public comparisonObject getObject3() {
        return object3;
    }

    /**
     * Creates a new instance of Compare_MB
     */
    public Compare_MB() {
    }

    /**
     * @ClassMethods
     */
    @PostConstruct
    private void init() {
        setUpLanguage();
        getPageContent();
        getComparison();
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
        listViewEn = dbViewFacade.findByName("viewPage", "comparison");
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

    private void getComparison() {
        List<comparisonItem> listOfComparison = (List<comparisonItem>) FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("comparePostCodes");
        sourceComparison = listOfComparison.get(0).getOrigin();
        listOfComparison.forEach(tmp -> {
            System.out.println(tmp.toString());
        });

        for (int i = 0; i < listOfComparison.size(); i++) {
            switch (i) {
                case 0:
                    object1 = buildCompItem(listOfComparison.get(i));
                    if (object1.getPieChartLabel() != null) {
                        callJavaScriptPie("item1PieChart", object1.getPieChartLabel(), object1.getPieChartLabels(),
                                object1.getPieChartValues());
                    }
                    if (object1.getCrimeChartLabel() != null) {
                        callJavaScriptBar("item1crimeChart", object1.getCrimeChartLabel(), object1.getCrimeChartLabels(),
                                object1.getCrimeChartValues());
                    }
                    if (object1.getRentChartLabel() != null) {
                        callJavaScriptBar("item1RentChart", object1.getRentChartLabel(), object1.getRentChartLabels(),
                                object1.getRentChartValues());
                    }
                    if (object1.getHousAndLandChartLabel1() != null && object1.getHousAndLandChartLabel2() != null) {
                        object1.setHousAndLandChart(true);
                        callJavaScript2Bar("item1RealChart", object1.getHousAndLandChartLabel1(), object1.getHousAndLandChartLabel2(),
                                object1.getHousAndLandChartBar1Labels(), object1.getHousAndLandChartBar1Values(), object1.getHousAndLandChartBar2Values());
                    }
                    break;
                case 1:
                    object2 = buildCompItem(listOfComparison.get(i));
                    if (object2.getPieChartLabel() != null) {
                        callJavaScriptPie("item2PieChart", object2.getPieChartLabel(), object2.getPieChartLabels(),
                                object2.getPieChartValues());
                    }
                    if (object2.getCrimeChartLabel() != null) {
                        callJavaScriptBar("item2crimeChart", object2.getCrimeChartLabel(), object2.getCrimeChartLabels(),
                                object2.getCrimeChartValues());
                    }
                    if (object2.getRentChartLabel() != null) {
                        callJavaScriptBar("item2RentChart", object2.getRentChartLabel(), object2.getRentChartLabels(),
                                object2.getRentChartValues());
                    }
                    if (object2.getHousAndLandChartLabel1() != null && object2.getHousAndLandChartLabel2() != null) {
                        object2.setHousAndLandChart(true);
                        callJavaScript2Bar("item2RealChart", object2.getHousAndLandChartLabel1(), object2.getHousAndLandChartLabel2(),
                                object2.getHousAndLandChartBar1Labels(), object2.getHousAndLandChartBar1Values(), object2.getHousAndLandChartBar2Values());
                    }
                    break;
                case 2:
                    object3 = buildCompItem(listOfComparison.get(i));
                    if (object3.getPieChartLabel() != null) {
                        callJavaScriptPie("item3PieChart", object3.getPieChartLabel(), object3.getPieChartLabels(),
                                object3.getPieChartValues());
                    }
                    if (object3.getCrimeChartLabel() != null) {
                        callJavaScriptBar("item3crimeChart", object3.getCrimeChartLabel(), object3.getCrimeChartLabels(),
                                object3.getCrimeChartValues());
                    }
                    if (object3.getRentChartLabel() != null) {
                        callJavaScriptBar("item3RentChart", object3.getRentChartLabel(), object3.getRentChartLabels(),
                                object3.getRentChartValues());
                    }
                    if (object3.getHousAndLandChartLabel1() != null && object3.getHousAndLandChartLabel2() != null) {
                        object3.setHousAndLandChart(true);
                        callJavaScript2Bar("item3RealChart", object3.getHousAndLandChartLabel1(), object3.getHousAndLandChartLabel2(),
                                object3.getHousAndLandChartBar1Labels(), object3.getHousAndLandChartBar1Values(), object3.getHousAndLandChartBar2Values());
                    }
                    break;
                default:
                    System.out.println("com.davido.managedBeans.Compare_MB.getComparison()");
                    break;
            }
        }
    }

    // This method creates the object to be presented in the comparison
    private comparisonObject buildCompItem(comparisonItem item) {
        comparisonObject tmpCompObj = new comparisonObject();

        tmpCompObj.setNonEmptyObj(true);
        tmpCompObj.setHeader(item.getPostCode() + " (" + item.getPostCodeName() + ")");
        tmpCompObj.setSchoolsNo(Integer.toString(DbSchoolFacade.findByPostcodeAndLine(Integer.parseInt(item.getPostCode()),
                Integer.parseInt(item.getPostCodeLine())).size()));
        tmpCompObj.setHospitalsNo(Integer.toString(dbHospitalFacade.findByPostcodeAndLine(Integer.parseInt(item.getPostCode()),
                Integer.parseInt(item.getPostCodeLine())).size()));
        tmpCompObj.setBusStopsNo(Integer.toString(dbbusStopFacade.findByPostcodeAndLine(Integer.parseInt(item.getPostCode()),
                Integer.parseInt(item.getPostCodeLine())).size()));
        tmpCompObj.setTrainStationsNo(Integer.toString(dbtrainStationFacade.findByPostcodeAndLine(Integer.parseInt(item.getPostCode()),
                Integer.parseInt(item.getPostCodeLine())).size()));

        List<String> labels = new ArrayList<>();
        List<String> colorsBg = new ArrayList<>();
        List<String> values = new ArrayList<>();

        if (!tmpCompObj.getSchoolsNo().equals("0")) {
            tmpCompObj.setPieChart(true);
            labels.add(translateString("Schools"));
            colorsBg.add("rgb(255, 99, 132)");
            values.add(tmpCompObj.getSchoolsNo());
        }
        if (!tmpCompObj.getHospitalsNo().equals("0")) {
            tmpCompObj.setPieChart(true);
            labels.add(translateString("Hospitals"));
            colorsBg.add("rgb(54, 162, 235)");
            values.add(tmpCompObj.getHospitalsNo());
        }
        if (!tmpCompObj.getBusStopsNo().equals("0")) {
            tmpCompObj.setPieChart(true);
            labels.add(translateString("Bus Stops"));
            colorsBg.add("rgb(255, 205, 86)");
            values.add(tmpCompObj.getBusStopsNo());
        }
        if (!tmpCompObj.getTrainStationsNo().equals("0")) {
            tmpCompObj.setPieChart(true);
            labels.add(translateString("Train Stops"));
            colorsBg.add("rgb(0,189,184)");
            values.add(tmpCompObj.getTrainStationsNo());
        }

        if (!labels.isEmpty() || !colorsBg.isEmpty() || !values.isEmpty()) {
            tmpCompObj.setPieChartLabel(translateString("Pie Chart"));
            tmpCompObj.setPieChartLabels(labels.stream().map(h -> h).toArray(String[]::new));
            tmpCompObj.setPieChartBgColors(colorsBg.stream().map(h -> h).toArray(String[]::new));
            tmpCompObj.setPieChartValues(values.stream().map(h -> h).toArray(String[]::new));
        }

        List<Object[]> listOfCrime = new ArrayList<>();
        listOfCrime = dbcrimeRateFacade.findByPostcodeAndLine(item.getPostCode(), item.getPostCodeLine());
        if (!listOfCrime.isEmpty()) {
            tmpCompObj.setCrimeChartLabel(translateString("Crime Rate"));
            tmpCompObj.setCrimeChartLabels(listOfCrime.stream().map(h -> h[2].toString()).toArray(String[]::new));
            tmpCompObj.setCrimeChartValues(listOfCrime.stream().map(h -> h[3].toString()).toArray(String[]::new));
        }

        List<Object[]> listOfRent = new ArrayList<>();
        listOfRent = dbhouseRentFacade.findByPostcodeAndLine(item.getPostCode(), item.getPostCodeLine());
        if (!listOfRent.isEmpty()) {
            tmpCompObj.setRentChart(true);
            tmpCompObj.setRentChartLabel(translateString("Rent Prices"));
            tmpCompObj.setRentChartLabels(listOfRent.stream().map(h -> h[2].toString()).toArray(String[]::new));
            tmpCompObj.setRentChartValues(listOfRent.stream().map(h -> h[3].toString()).toArray(String[]::new));
        }

        List<Object[]> listOfRealEstate = new ArrayList<>();
        listOfRealEstate = dbhouseBuyingFacade.findByPostcodeAndLine(item.getPostCode(), item.getPostCodeLine());
        if (!listOfRealEstate.isEmpty()) {
            tmpCompObj.setHousAndLandChartLabel1(translateString("Real Estate"));
            tmpCompObj.setHousAndLandChartBar1Labels(listOfRealEstate.stream().map(h -> h[2].toString()).toArray(String[]::new));
            tmpCompObj.setHousAndLandChartBar1Values(listOfRealEstate.stream().map(h -> h[3].toString()).toArray(String[]::new));
        }

        List<Object[]> listOfLandPrices = new ArrayList<>();
        listOfLandPrices = dblandPriceFacade.findByPostcodeAndLine(item.getPostCode(), item.getPostCodeLine());
        if (!listOfLandPrices.isEmpty()) {
            tmpCompObj.setHousAndLandChartLabel2(translateString("Land Prices"));
            tmpCompObj.setHousAndLandChartBar2Values(listOfLandPrices.stream().map(h -> h[3].toString()).toArray(String[]::new));
        }
        return tmpCompObj;
    }

    private void callJavaScriptBar(String JSObject, String Label, String[] Labels, String[] Values) {
        PrimeFaces.current().executeScript("ProcessBarChart('" + JSObject + "','" + Label + "','" + String.join(",", Labels)
                + "','" + String.join(",", Values) + "');");
    }

    private void callJavaScriptPie(String JSObject, String Label, String[] Labels, String[] Values) {
        PrimeFaces.current().executeScript("ProcessPieChart('" + JSObject + "','" + Label + "','" + String.join(",", Labels)
                + "','" + String.join(",", Values) + "');");
    }

    private void callJavaScript2Bar(String JSObject, String Label1, String Label2, String[] Labels, String[] Values1, String[] Values2) {
        PrimeFaces.current().executeScript("Process2BarChart('" + JSObject + "','" + Label1 + "','" + Label2 + "','" + String.join(",", Labels)
                + "','" + String.join(",", Values1) + "','" + String.join(",", Values2) + "');");
    }

    public void returnToOrigin() {
        try {
            if (sourceComparison.equals("suggestion")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("suggestion.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("selectComp.xhtml");
            }
        } catch (IOException ex) {
            Logger.getLogger(Compare_MB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
