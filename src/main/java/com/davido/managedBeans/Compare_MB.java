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
import com.davido.utilities.GeoCoder;
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
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

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

    private MapModel simpleModel;
    private final String MAPKEY = "AIzaSyCiXv5vqdhfhpf1PvkvAH_fl1u9OFT0HUU";

    public comparisonObject getObject1() {
        return object1;
    }

    public comparisonObject getObject2() {
        return object2;
    }

    public comparisonObject getObject3() {
        return object3;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

    public String getMAPKEY() {
        return MAPKEY;
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
        getComparison();
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
                    break;
                case 1:
                    object2 = buildCompItem(listOfComparison.get(i));
                    break;
                case 2:
                    object3 = buildCompItem(listOfComparison.get(i));
                    break;
                default:
                    System.out.println("com.davido.managedBeans.Compare_MB.getComparison()");
                    break;
            }
        }

        addMapMarkers();

        // Creating the first comparison bar chart
        if (object1.isPieChart() || object2.isPieChart() || object3.isPieChart()) {
            String[] Labels = {translateString("Schools"), translateString("Hospitals"), translateString("Bus Stops"),
                translateString("Train Stations")};

            int[] dataArrayOne = {Integer.parseInt(object1.getSchoolsNo()), Integer.parseInt(object1.getHospitalsNo()),
                Integer.parseInt(object1.getBusStopsNo()), Integer.parseInt(object1.getTrainStationsNo())};
            datasetBarObject dataObj1 = new datasetBarObject(object1.getPostCodeName(), dataArrayOne, "rgba(255, 99, 132, 0.2)",
                    "rgba(255, 99, 132, 1)", 1);
            Gson gsonBuilder = new GsonBuilder().create();
            String firstDataSet = gsonBuilder.toJson(dataObj1);

            int[] dataArrayTwo = {Integer.parseInt(object2.getSchoolsNo()), Integer.parseInt(object2.getHospitalsNo()),
                Integer.parseInt(object2.getBusStopsNo()), Integer.parseInt(object2.getTrainStationsNo())};
            datasetBarObject dataObj2 = new datasetBarObject(object2.getPostCodeName(), dataArrayTwo, "rgba(0, 189, 184, 0.2)",
                    "rgba(0, 189, 184, 1)", 1);
            String secondDataSet = gsonBuilder.toJson(dataObj2);

            int yAxisMax = 3;
            for (int i = 0; i < dataArrayOne.length; i++) {
                if (yAxisMax < dataArrayOne[i]) {
                    yAxisMax = dataArrayOne[i];
                }
            }

            for (int i = 0; i < dataArrayTwo.length; i++) {
                if (yAxisMax < dataArrayTwo[i]) {
                    yAxisMax = dataArrayTwo[i];
                }
            }

            String thirdDataSet = "";
            if (object3 != null && object3.isNonEmptyObj()) {
                int[] dataArrayThree = {Integer.parseInt(object3.getSchoolsNo()), Integer.parseInt(object3.getHospitalsNo()),
                    Integer.parseInt(object3.getBusStopsNo()), Integer.parseInt(object3.getTrainStationsNo())};
                datasetBarObject dataObj3 = new datasetBarObject(object3.getPostCodeName(), dataArrayThree, "rgba(54, 162, 235, 0.2)",
                        "rgba(54, 162, 235, 1)", 1);
                thirdDataSet = gsonBuilder.toJson(dataObj3);

                for (int i = 0; i < dataArrayThree.length; i++) {
                    if (yAxisMax < dataArrayThree[i]) {
                        yAxisMax = dataArrayThree[i];
                    }
                }
            }
            yAxisMax += 10;
            int step = yAxisMax / 10;
            callJavaScript3Bar("generalCompBar", Labels, firstDataSet, secondDataSet, object3 != null, thirdDataSet, yAxisMax, step);
        }

        if (object3 == null) {
            object3 = new comparisonObject();
        }

        // build the crime rate chart
        if ((object1.isCrimeChart() && object2.isCrimeChart()) || (object1.isCrimeChart() && object3.isCrimeChart())
                || (object2.isCrimeChart() && object3.isCrimeChart())) {
            Gson gsonBuilder = new GsonBuilder().create();

            String[] Labels = object1.getCrimeChartLabels();
            Double[] dataArrayOne = new Double[object1.getCrimeChartLabels().length];
            int yAxisMax = 3;
            for (int i = 0; i < object1.getCrimeChartLabels().length; i++) {
                dataArrayOne[i] = Double.parseDouble(object1.getCrimeChartValues()[i]);
                if ((int) Math.round(dataArrayOne[i]) > yAxisMax) {
                    yAxisMax = (int) Math.round(dataArrayOne[i]);
                }
            }

            Double[] dataArrayTwo = new Double[object2.getCrimeChartLabels().length];
            for (int i = 0; i < object2.getCrimeChartLabels().length; i++) {
                dataArrayTwo[i] = Double.parseDouble(object2.getCrimeChartValues()[i]);
                if ((int) Math.round(dataArrayTwo[i]) > yAxisMax) {
                    yAxisMax = (int) Math.round(dataArrayTwo[i]);
                }
            }

            datasetLineObject dataObj1 = new datasetLineObject(object1.getPostCodeName(), dataArrayOne, false, "rgba(255, 99, 132, 1)", 0.1);
            String firstDataSet = gsonBuilder.toJson(dataObj1);
            datasetLineObject dataObj2 = new datasetLineObject(object2.getPostCodeName(), dataArrayTwo, false, "rgba(0, 189, 184, 1)", 0.1);
            String secondDataSet = gsonBuilder.toJson(dataObj2);

            Double[] dataArrayThree;
            String thirdDataSet = "";
            if (object3.isCrimeChart()) {
                dataArrayThree = new Double[object3.getCrimeChartLabels().length];
                for (int i = 0; i < object3.getCrimeChartLabels().length; i++) {
                    dataArrayThree[i] = Double.parseDouble(object3.getCrimeChartValues()[i]);
                    if ((int) Math.round(dataArrayThree[i]) > yAxisMax) {
                        yAxisMax = (int) Math.round(dataArrayThree[i]);
                    }
                }
                datasetLineObject dataObj3 = new datasetLineObject(object3.getPostCodeName(), dataArrayThree, false, "rgba(54, 162, 235, 1)", 0.1);
                thirdDataSet = gsonBuilder.toJson(dataObj3);
            }
            yAxisMax += 2;
            callJavaScript3Line("crimeCompLine", Labels, firstDataSet, secondDataSet, object3.isCrimeChart(), thirdDataSet, yAxisMax, 1.0);

        }

        // build the rent chart
        if ((object1.isRentChart() && object2.isRentChart()) || (object1.isRentChart() && object3.isRentChart())
                || (object2.isRentChart() && object3.isRentChart())) {
            Gson gsonBuilder = new GsonBuilder().create();
            int yAxisMax = 3;
            String[] Labels = null;

            int[] dataArrayOne;
            String firstDataSet = "";
            if (object1.isRentChart()) {
                if (Labels == null) {
                    Labels = object1.getRentChartLabels();
                }
                dataArrayOne = new int[object1.getRentChartLabels().length];
                for (int i = 0; i < object1.getRentChartLabels().length; i++) {
                    dataArrayOne[i] = Integer.parseInt(object1.getRentChartValues()[i]);
                    if (dataArrayOne[i] > yAxisMax) {
                        yAxisMax = dataArrayOne[i];
                    }
                }
                datasetBarObject dataObj1 = new datasetBarObject(object1.getPostCodeName(), dataArrayOne, "rgba(255, 99, 132, 0.2)",
                        "rgba(255, 99, 132, 1)", 1);
                firstDataSet = gsonBuilder.toJson(dataObj1);
            }

            int[] dataArrayTwo;
            String secondDataSet = "";
            if (object2.isRentChart()) {
                if (Labels == null) {
                    Labels = object2.getRentChartLabels();
                }
                dataArrayTwo = new int[object2.getRentChartLabels().length];
                for (int i = 0; i < object2.getRentChartLabels().length; i++) {
                    dataArrayTwo[i] = Integer.parseInt(object2.getRentChartValues()[i]);
                    if (dataArrayTwo[i] > yAxisMax) {
                        yAxisMax = dataArrayTwo[i];
                    }
                }

                datasetBarObject dataObj2 = new datasetBarObject(object2.getPostCodeName(), dataArrayTwo, "rgba(0, 189, 184, 0.2)",
                        "rgba(0, 189, 184, 1)", 1);
                secondDataSet = gsonBuilder.toJson(dataObj2);
            }

            int[] dataArrayThree;
            String thirdDataSet = "";
            if (object3.isRentChart()) {
                if (Labels == null) {
                    Labels = object3.getRentChartLabels();
                }
                dataArrayThree = new int[object3.getRentChartLabels().length];
                for (int i = 0; i < object3.getRentChartLabels().length; i++) {
                    dataArrayThree[i] = Integer.parseInt(object3.getRentChartValues()[i]);
                    if (dataArrayThree[i] > yAxisMax) {
                        yAxisMax = dataArrayThree[i];
                    }
                }
                datasetBarObject dataObj3 = new datasetBarObject(object3.getPostCodeName(), dataArrayThree, "rgba(54, 162, 235, 0.2)",
                        "rgba(54, 162, 235, 1)", 1);
                thirdDataSet = gsonBuilder.toJson(dataObj3);
            }
            yAxisMax += 100;
            callJavaScript3BarCheck("rentBarChart", Labels, object1.isRentChart(), firstDataSet, object2.isRentChart(), secondDataSet,
                    object3.isRentChart(), thirdDataSet, yAxisMax, 100);
        }

        // build the house chart
        if ((object1.isHouseChart() && object2.isHouseChart()) || (object1.isHouseChart() && object3.isHouseChart())
                || (object2.isHouseChart() && object3.isHouseChart())) {
            Gson gsonBuilder = new GsonBuilder().create();
            int yAxisMax = 3;
            String[] Labels = null;

            int[] dataArrayOne;
            String firstDataSet = "";
            if (object1.isHouseChart()) {
                if (Labels == null) {
                    Labels = object1.getHouseChartLabels();
                }
                dataArrayOne = new int[object1.getHouseChartLabels().length];
                for (int i = 0; i < object1.getHouseChartLabels().length; i++) {
                    dataArrayOne[i] = Integer.parseInt(object1.getHouseChartValues()[i]);
                    if (dataArrayOne[i] > yAxisMax) {
                        yAxisMax = dataArrayOne[i];
                    }
                }
                datasetBarObject dataObj1 = new datasetBarObject(object1.getPostCodeName(), dataArrayOne, "rgba(255, 99, 132, 0.2)",
                        "rgba(255, 99, 132, 1)", 1);
                firstDataSet = gsonBuilder.toJson(dataObj1);
            }

            int[] dataArrayTwo;
            String secondDataSet = "";
            if (object2.isHouseChart()) {
                if (Labels == null) {
                    Labels = object2.getHouseChartLabels();
                }
                dataArrayTwo = new int[object2.getHouseChartLabels().length];
                for (int i = 0; i < object2.getHouseChartLabels().length; i++) {
                    dataArrayTwo[i] = Integer.parseInt(object2.getHouseChartValues()[i]);
                    if (dataArrayTwo[i] > yAxisMax) {
                        yAxisMax = dataArrayTwo[i];
                    }
                }
                datasetBarObject dataObj2 = new datasetBarObject(object2.getPostCodeName(), dataArrayTwo, "rgba(0, 189, 184, 0.2)",
                        "rgba(0, 189, 184, 1)", 1);
                secondDataSet = gsonBuilder.toJson(dataObj2);
            }

            int[] dataArrayThree;
            String thirdDataSet = "";
            if (object3.isHouseChart()) {
                if (Labels == null) {
                    Labels = object3.getHouseChartLabels();
                }
                dataArrayThree = new int[object3.getHouseChartLabels().length];
                for (int i = 0; i < object3.getHouseChartLabels().length; i++) {
                    dataArrayThree[i] = Integer.parseInt(object3.getHouseChartValues()[i]);
                    if (dataArrayThree[i] > yAxisMax) {
                        yAxisMax = dataArrayThree[i];
                    }
                }
                datasetBarObject dataObj3 = new datasetBarObject(object3.getPostCodeName(), dataArrayThree, "rgba(54, 162, 235, 0.2)",
                        "rgba(54, 162, 235, 1)", 1);
                thirdDataSet = gsonBuilder.toJson(dataObj3);
            }
            yAxisMax += 50000;
            callJavaScript3BarCheck("HouseBarChart", Labels, object1.isHouseChart(), firstDataSet, object2.isHouseChart(), secondDataSet,
                    object3.isHouseChart(), thirdDataSet, yAxisMax, 100000);
        }

        // build the land chart
        if ((object1.isLandChart() && object2.isLandChart()) || (object1.isLandChart() && object3.isLandChart())
                || (object2.isLandChart() && object3.isLandChart())) {
            Gson gsonBuilder = new GsonBuilder().create();
            int yAxisMax = 3;
            String[] Labels = null;

            int[] dataArrayOne;
            String firstDataSet = "";
            if (object1.isLandChart()) {
                if (Labels == null) {
                    Labels = object1.getLandChartLabels();
                }
                dataArrayOne = new int[object1.getLandChartLabels().length];
                for (int i = 0; i < object1.getLandChartLabels().length; i++) {
                    dataArrayOne[i] = Integer.parseInt(object1.getLandChartValues()[i]);
                    if (dataArrayOne[i] > yAxisMax) {
                        yAxisMax = dataArrayOne[i];
                    }
                }
                datasetBarObject dataObj1 = new datasetBarObject(object1.getPostCodeName(), dataArrayOne, "rgba(255, 99, 132, 0.2)",
                        "rgba(255, 99, 132, 1)", 1);
                firstDataSet = gsonBuilder.toJson(dataObj1);
            }

            int[] dataArrayTwo;
            String secondDataSet = "";
            if (object2.isLandChart()) {
                if (Labels == null) {
                    Labels = object2.getLandChartLabels();
                }
                dataArrayTwo = new int[object2.getLandChartLabels().length];
                for (int i = 0; i < object2.getLandChartLabels().length; i++) {
                    dataArrayTwo[i] = Integer.parseInt(object2.getLandChartValues()[i]);
                    if (dataArrayTwo[i] > yAxisMax) {
                        yAxisMax = dataArrayTwo[i];
                    }
                }
                datasetBarObject dataObj2 = new datasetBarObject(object2.getPostCodeName(), dataArrayTwo, "rgba(0, 189, 184, 0.2)",
                        "rgba(0, 189, 184, 1)", 1);
                secondDataSet = gsonBuilder.toJson(dataObj2);
            }

            int[] dataArrayThree;
            String thirdDataSet = "";
            if (object3.isLandChart()) {
                if (Labels == null) {
                    Labels = object3.getLandChartLabels();
                }
                dataArrayThree = new int[object3.getLandChartLabels().length];
                for (int i = 0; i < object3.getLandChartLabels().length; i++) {
                    dataArrayThree[i] = Integer.parseInt(object3.getLandChartValues()[i]);
                    if (dataArrayThree[i] > yAxisMax) {
                        yAxisMax = dataArrayThree[i];
                    }
                }
                datasetBarObject dataObj3 = new datasetBarObject(object3.getPostCodeName(), dataArrayThree, "rgba(54, 162, 235, 0.2)",
                        "rgba(54, 162, 235, 1)", 1);
                thirdDataSet = gsonBuilder.toJson(dataObj3);
            }
            yAxisMax += 100000;
            callJavaScript3BarCheck("LandBarChart", Labels, object1.isLandChart(), firstDataSet, object2.isLandChart(),
                    secondDataSet, object3.isLandChart(), thirdDataSet, yAxisMax, 100000);
        }
    }

    // This method creates the object to be presented in the comparison
    private comparisonObject buildCompItem(comparisonItem item) {
        comparisonObject tmpCompObj = new comparisonObject();

        tmpCompObj.setNonEmptyObj(true);
        tmpCompObj.setHeader(item.getPostCode() + " (" + item.getPostCodeName() + ")");
        tmpCompObj.setPostCode(item.getPostCode());
        tmpCompObj.setPostCodeName(item.getPostCodeName());
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
            tmpCompObj.setCrimeChart(true);
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
            tmpCompObj.setHouseChart(true);
            tmpCompObj.setHouseChartLabel(translateString("Real Estate"));
            tmpCompObj.setHouseChartLabels(listOfRealEstate.stream().map(h -> h[2].toString()).toArray(String[]::new));
            tmpCompObj.setHouseChartValues(listOfRealEstate.stream().map(h -> h[3].toString()).toArray(String[]::new));
        }

        List<Object[]> listOfLandPrices = new ArrayList<>();
        listOfLandPrices = dblandPriceFacade.findByPostcodeAndLine(item.getPostCode(), item.getPostCodeLine());
        if (!listOfLandPrices.isEmpty()) {
            tmpCompObj.setLandChart(true);
            tmpCompObj.setLandChartLabel(translateString("Land Price"));
            tmpCompObj.setLandChartLabels(listOfRealEstate.stream().map(h -> h[2].toString()).toArray(String[]::new));
            tmpCompObj.setLandChartValues(listOfLandPrices.stream().map(h -> h[3].toString()).toArray(String[]::new));
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

    private void callJavaScript3Bar(String JSObject, String[] Labels, String datasetOne, String datasetTwo, boolean dataSet3, String datasetThree,
            int yAxisMax, int stepSize) {
        PrimeFaces.current().executeScript("Process3BarChart('" + JSObject + "','" + String.join(",", Labels) + "','" + datasetOne + "','" + datasetTwo
                + "','" + dataSet3 + "','" + datasetThree + "','" + yAxisMax + "','" + stepSize + "');");
    }

    private void callJavaScript3Line(String JSObject, String[] Labels, String datasetOne, String datasetTwo, boolean dataSet3, String datasetThree,
            int yAxisMax, double stepSize) {
        PrimeFaces.current().executeScript("Process3LineChart('" + JSObject + "','" + String.join(",", Labels) + "','" + datasetOne + "','" + datasetTwo
                + "','" + dataSet3 + "','" + datasetThree + "','" + yAxisMax + "','" + stepSize + "');");
    }

    private void callJavaScript3hoBar(String JSObject, String[] Labels, String datasetOne, String datasetTwo, boolean dataSet3, String datasetThree,
            int yAxisMax, double stepSize) {
        PrimeFaces.current().executeScript("Process3BarHoChart('" + JSObject + "','" + String.join(",", Labels) + "','" + datasetOne + "','" + datasetTwo
                + "','" + dataSet3 + "','" + datasetThree + "','" + yAxisMax + "','" + stepSize + "');");
    }

    private void callJavaScript3BarCheck(String JSObject, String[] Labels, boolean dataSet1, String datasetOne, boolean dataSet2, String datasetTwo,
            boolean dataSet3, String datasetThree,
            int yAxisMax, double stepSize) {
        PrimeFaces.current().executeScript("Process3BarChartCheck('" + JSObject + "','" + String.join(",", Labels) + "','" + dataSet1 + "','"
                + datasetOne + "','" + dataSet2 + "','" + datasetTwo + "','" + dataSet3 + "','" + datasetThree + "','" + yAxisMax + "','"
                + stepSize + "');");
    }

    // This method returns to the original page
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

    // This method draws all the markers in the map
    private void addMapMarkers() {

        //Get reverse geocode for postCodes
        simpleModel = new DefaultMapModel();
        GeoCoder revGeoCode = new GeoCoder();

        if (object1 != null && object1.isNonEmptyObj()) {
            Double[] coordsObj = revGeoCode.GeoCoder(object1.getPostCodeName() + "+Victoria+Australia", MAPKEY);
            if (coordsObj != null) {
                LatLng coord = new LatLng(coordsObj[0], coordsObj[1]);
                simpleModel.addOverlay(new Marker(coord, object1.getHeader(), "Option1",
                        "http://maps.google.com/mapfiles/ms/icons/red-dot.png"));
            }
        }

        if (object2 != null && object2.isNonEmptyObj()) {
            Double[] coordsObj = revGeoCode.GeoCoder(object2.getPostCodeName() + "+Victoria+Australia", MAPKEY);
            if (coordsObj != null) {
                LatLng coord = new LatLng(coordsObj[0], coordsObj[1]);
                simpleModel.addOverlay(new Marker(coord, object2.getHeader(), "Option2",
                        "http://maps.google.com/mapfiles/ms/icons/green-dot.png"));
            }
        }

        if (object3 != null && object3.isNonEmptyObj()) {
            Double[] coordsObj = revGeoCode.GeoCoder(object3.getPostCodeName() + "+Victoria+Australia", MAPKEY);
            if (coordsObj != null) {
                LatLng coord = new LatLng(coordsObj[0], coordsObj[1]);
                simpleModel.addOverlay(new Marker(coord, object3.getHeader(), "Option3",
                        "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"));
            }
        }

    }

    private void send() {
    }

    class datasetBarObject {

        public String label;
        public int[] data;
        public String backgroundColor;
        public String borderColor;
        public int borderWidth;

        public datasetBarObject() {
        }

        public datasetBarObject(String label, int[] data, String backgroundColor, String borderColor, int borderWidth) {
            this.label = label;
            this.data = data;
            this.backgroundColor = backgroundColor;
            this.borderColor = borderColor;
            this.borderWidth = borderWidth;
        }

    }

    class datasetLineObject {

        public String label;
        public Double[] data;
        public boolean fill;
        public String borderColor;
        public Double lineTension;

        public datasetLineObject() {
        }

        public datasetLineObject(String label, Double[] data, boolean fill, String borderColor, Double lineTension) {
            this.label = label;
            this.data = data;
            this.fill = fill;
            this.borderColor = borderColor;
            this.lineTension = lineTension;
        }

    }

}
