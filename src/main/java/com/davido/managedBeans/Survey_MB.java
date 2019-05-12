/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.managedBeans;

import com.davido.customObjects.resultantItem;
import com.davido.customObjects.surveyQuestion;
import com.davido.ejb.DbHospitalFacadeLocal;
import com.davido.ejb.DbPreferencesFacadeLocal;
import com.davido.ejb.DbSchoolFacadeLocal;
import com.davido.ejb.DbViewFacadeLocal;
import com.davido.ejb.DbbusStopFacadeLocal;
import com.davido.ejb.DbcrimeRateFacadeLocal;
import com.davido.ejb.DbhouseBuyingFacadeLocal;
import com.davido.ejb.DbhouseRentFacadeLocal;
import com.davido.ejb.DblandPriceFacadeLocal;
import com.davido.ejb.DbtrainStationFacadeLocal;
import com.davido.entities.DbPreferences;
import com.davido.entities.DbView;
import com.davido.utilities.Translator;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author davidortega
 */
@Named(value = "survey_MB")
@ViewScoped
public class Survey_MB implements Serializable {

    /**
     * Variables declaration
     *
     * @variables
     */
    @EJB
    private DbViewFacadeLocal dbViewFacade;
    @EJB
    private DbPreferencesFacadeLocal dbPreferencesFacade;
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
    List<DbView> listViewEn;
    List<DbView> listViewTr;

    // Business Logic variables for preferences
    private String preference1;
    private String preference2;
    private String preference3;
    private List<DbPreferences> listOfPreferencesEn;
    private List<DbPreferences> listOfPreferencesTr;
    private List<DbPreferences> listOfPreferencesOne;
    private List<DbPreferences> listOfPreferencesTwo;
    private List<DbPreferences> listOfPreferencesThree;
    private boolean activePreference1;
    private boolean activePreference2;
    private boolean activePreference3;
    private boolean errorPreference1;
    private boolean errorPreference2;
    private boolean errorPreference3;
    private boolean enableInstructions;

    // Business Logic variables for busStops
    private boolean enableBusStops;
    private boolean enableBusStopsBack;
    private boolean enableBusStopsRange1;
    private boolean enableBusStopsRange2;
    private boolean enableBusStopsError1;
    private boolean enableBusStopsError2;
    private List<String> listOfBusStop1;
    private List<String> listOfBusStop2;

    // Business Logic variables for Crime Rate
    private boolean enableCrimeRate;
    private boolean enableCrimeRateBack;
    private boolean enableCrimeRateRange1;
    private boolean enableCrimeRateRange2;
    private boolean enableCrimeRateError1;
    private boolean enableCrimeRateError2;
    private List<String> listOfCrimeRate1;
    private List<String> listOfCrimeRate2;

    // Business Logic variables for Hospitals
    private boolean enableHospitals;
    private boolean enableHospitalsBack;
    private boolean enableHospitalsRange1;
    private boolean enableHospitalsRange2;
    private boolean enableHospitalsError1;
    private boolean enableHospitalsError2;
    private List<String> listOfHospitals1;
    private List<String> listOfHospitals2;

    // Business Logic variables for Schools
    private boolean enableSchools;
    private boolean enableSchoolsBack;
    private boolean enableSchoolsRange1;
    private boolean enableSchoolsRange2;
    private boolean enableSchoolsError1;
    private boolean enableSchoolsError2;
    private List<String> listOfSchools1;
    private List<String> listOfSchools2;

    // Business Logic variables for Rent Prices
    private boolean enableRealStateRent;
    private boolean enableRealStateRentBack;
    private boolean enableRealStateRentRange1;
    private boolean enableRealStateRentRange2;
    private boolean enableRealStateRentError1;
    private boolean enableRealStateRentError2;
    private List<String> listOfRealStateRent1;
    private List<String> listOfRealStateRent2;

    // Business Logic variables for Real Estate Prices
    private boolean enableRealStatePrices;
    private boolean enableRealStatePricesBack;
    private boolean enableRealStatePricesRange1;
    private boolean enableRealStatePricesRange2;
    private boolean enableRealStatePricesError1;
    private boolean enableRealStatePricesError2;
    private List<String> listOfRealStatePrices1;
    private List<String> listOfRealStatePrices2;

    // Business Logic variables for Land Prices
    private boolean enableRealStateBuyLand;
    private boolean enableRealStateBuyLandBack;
    private boolean enableRealStateBuyLandRange1;
    private boolean enableRealStateBuyLandRange2;
    private boolean enableRealStateBuyLandError1;
    private boolean enableRealStateBuyLandError2;
    private List<String> listOfRealStateBuyLand1;
    private List<String> listOfRealStateBuyLand2;

    // Business Logic variables for Train Stops
    private boolean enableTrainSations;
    private boolean enableTrainSationsBack;
    private boolean enableTrainSationsRange1;
    private boolean enableTrainSationsRange2;
    private boolean enableTrainSationsError1;
    private boolean enableTrainSationsError2;
    private List<String> listOfTrainSations1;
    private List<String> listOfTrainSations2;

    // Business Logic Variables for survey in general
    private surveyQuestion currentQuestion;
    private List<surveyQuestion> listOfQuestions;

    /**
     * Getters and Setters
     *
     * @return
     */
    public String getPreference1() {
        return preference1;
    }

    public void setPreference1(String preference1) {
        this.preference1 = preference1;
    }

    public String getPreference2() {
        return preference2;
    }

    public void setPreference2(String preference2) {
        this.preference2 = preference2;
    }

    public String getPreference3() {
        return preference3;
    }

    public void setPreference3(String preference3) {
        this.preference3 = preference3;
    }

    public List<DbPreferences> getListOfPreferencesOne() {
        return listOfPreferencesOne;
    }

    public void setListOfPreferencesOne(List<DbPreferences> listOfPreferencesOne) {
        this.listOfPreferencesOne = listOfPreferencesOne;
    }

    public List<DbPreferences> getListOfPreferencesTwo() {
        return listOfPreferencesTwo;
    }

    public void setListOfPreferencesTwo(List<DbPreferences> listOfPreferencesTwo) {
        this.listOfPreferencesTwo = listOfPreferencesTwo;
    }

    public List<DbPreferences> getListOfPreferencesThree() {
        return listOfPreferencesThree;
    }

    public void setListOfPreferencesThree(List<DbPreferences> listOfPreferencesThree) {
        this.listOfPreferencesThree = listOfPreferencesThree;
    }

    public boolean isActivePreference1() {
        return activePreference1;
    }

    public boolean isActivePreference2() {
        return activePreference2;
    }

    public boolean isActivePreference3() {
        return activePreference3;
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

    public boolean isEnableInstructions() {
        return enableInstructions;
    }

    public boolean isEnableBusStops() {
        return enableBusStops;
    }

    public boolean isEnableHospitals() {
        return enableHospitals;
    }

    public boolean isEnableCrimeRate() {
        return enableCrimeRate;
    }

    public boolean isEnableRealStateRent() {
        return enableRealStateRent;
    }

    public boolean isEnableRealStateBuyLand() {
        return enableRealStateBuyLand;
    }

    public boolean isEnableSchools() {
        return enableSchools;
    }

    public boolean isEnableBusStopsBack() {
        return enableBusStopsBack;
    }

    public boolean isEnableHospitalsBack() {
        return enableHospitalsBack;
    }

    public boolean isEnableCrimeRateBack() {
        return enableCrimeRateBack;
    }

    public boolean isEnableSchoolsBack() {
        return enableSchoolsBack;
    }

    public boolean isEnableRealStateRentBack() {
        return enableRealStateRentBack;
    }

    public boolean isEnableRealStateBuyLandBack() {
        return enableRealStateBuyLandBack;
    }

    public surveyQuestion getCurrentQuestion() {
        return currentQuestion;
    }

    public List<surveyQuestion> getListOfQuestions() {
        return listOfQuestions;
    }

    public boolean isEnableBusStopsRange1() {
        return enableBusStopsRange1;
    }

    public boolean isEnableBusStopsRange2() {
        return enableBusStopsRange2;
    }

    public boolean isEnableBusStopsError1() {
        return enableBusStopsError1;
    }

    public boolean isEnableBusStopsError2() {
        return enableBusStopsError2;
    }

    public List<String> getListOfBusStop1() {
        return listOfBusStop1;
    }

    public List<String> getListOfBusStop2() {
        return listOfBusStop2;
    }

    public boolean isEnableRealStatePrices() {
        return enableRealStatePrices;
    }

    public boolean isEnableRealStatePricesBack() {
        return enableRealStatePricesBack;
    }

    public boolean isEnableTrainSations() {
        return enableTrainSations;
    }

    public boolean isEnableTrainSationsBack() {
        return enableTrainSationsBack;
    }

    public boolean isEnableCrimeRateRange1() {
        return enableCrimeRateRange1;
    }

    public boolean isEnableCrimeRateRange2() {
        return enableCrimeRateRange2;
    }

    public boolean isEnableCrimeRateError1() {
        return enableCrimeRateError1;
    }

    public boolean isEnableCrimeRateError2() {
        return enableCrimeRateError2;
    }

    public List<String> getListOfCrimeRate1() {
        return listOfCrimeRate1;
    }

    public List<String> getListOfCrimeRate2() {
        return listOfCrimeRate2;
    }

    public boolean isEnableHospitalsRange1() {
        return enableHospitalsRange1;
    }

    public boolean isEnableHospitalsRange2() {
        return enableHospitalsRange2;
    }

    public boolean isEnableHospitalsError1() {
        return enableHospitalsError1;
    }

    public boolean isEnableHospitalsError2() {
        return enableHospitalsError2;
    }

    public List<String> getListOfHospitals1() {
        return listOfHospitals1;
    }

    public List<String> getListOfHospitals2() {
        return listOfHospitals2;
    }

    public boolean isEnableSchoolsRange1() {
        return enableSchoolsRange1;
    }

    public boolean isEnableSchoolsRange2() {
        return enableSchoolsRange2;
    }

    public boolean isEnableSchoolsError1() {
        return enableSchoolsError1;
    }

    public boolean isEnableSchoolsError2() {
        return enableSchoolsError2;
    }

    public List<String> getListOfSchools1() {
        return listOfSchools1;
    }

    public List<String> getListOfSchools2() {
        return listOfSchools2;
    }

    public boolean isEnableRealStateRentRange1() {
        return enableRealStateRentRange1;
    }

    public boolean isEnableRealStateRentRange2() {
        return enableRealStateRentRange2;
    }

    public boolean isEnableRealStateRentError1() {
        return enableRealStateRentError1;
    }

    public boolean isEnableRealStateRentError2() {
        return enableRealStateRentError2;
    }

    public List<String> getListOfRealStateRent1() {
        return listOfRealStateRent1;
    }

    public List<String> getListOfRealStateRent2() {
        return listOfRealStateRent2;
    }

    public boolean isEnableRealStatePricesRange1() {
        return enableRealStatePricesRange1;
    }

    public boolean isEnableRealStatePricesRange2() {
        return enableRealStatePricesRange2;
    }

    public boolean isEnableRealStatePricesError1() {
        return enableRealStatePricesError1;
    }

    public boolean isEnableRealStatePricesError2() {
        return enableRealStatePricesError2;
    }

    public List<String> getListOfRealStatePrices1() {
        return listOfRealStatePrices1;
    }

    public List<String> getListOfRealStatePrices2() {
        return listOfRealStatePrices2;
    }

    public boolean isEnableRealStateBuyLandRange1() {
        return enableRealStateBuyLandRange1;
    }

    public boolean isEnableRealStateBuyLandRange2() {
        return enableRealStateBuyLandRange2;
    }

    public boolean isEnableRealStateBuyLandError1() {
        return enableRealStateBuyLandError1;
    }

    public boolean isEnableRealStateBuyLandError2() {
        return enableRealStateBuyLandError2;
    }

    public List<String> getListOfRealStateBuyLand1() {
        return listOfRealStateBuyLand1;
    }

    public List<String> getListOfRealStateBuyLand2() {
        return listOfRealStateBuyLand2;
    }

    public boolean isEnableTrainSationsRange1() {
        return enableTrainSationsRange1;
    }

    public boolean isEnableTrainSationsRange2() {
        return enableTrainSationsRange2;
    }

    public boolean isEnableTrainSationsError1() {
        return enableTrainSationsError1;
    }

    public boolean isEnableTrainSationsError2() {
        return enableTrainSationsError2;
    }

    public List<String> getListOfTrainSations1() {
        return listOfTrainSations1;
    }

    public List<String> getListOfTrainSations2() {
        return listOfTrainSations2;
    }

    /**
     * @Constructors
     */
    public Survey_MB() {
    }

    /**
     * @ClassMethods
     */
    @PostConstruct
    private void init() {
        setUpLanguage();
        getPageContent();
        resetSurvey();
        getPreferences();
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
        listViewEn = dbViewFacade.findByName("viewPage", "survey");
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

    // This method translates the current English object
    public void translate(String lang) {
        if (lang.equals("EN")) {
            listViewTr = new ArrayList<>();
            currentLang = lang;
            listViewTr = listViewEn;
            getPreferences();
        } else if (!currentLang.equals(lang)) {
            listViewTr = new ArrayList<>();
            currentLang = lang;
            listViewTr = translateList(listViewEn, currentLang);
            getPreferencesAfterPageTranslation(currentLang);
        }
    }

    // This method returns the field in the obj
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

    // This method resets the survey to first step
    private void resetSurvey() {
        enableInstructions = true;
        enableBusStops = false;
        enableHospitals = false;
        enableCrimeRate = false;
        enableRealStateRent = false;
        enableRealStatePrices = false;
        enableRealStateBuyLand = false;
        enableSchools = false;
        enableTrainSations = false;
        enableBusStopsBack = false;
        enableHospitalsBack = false;
        enableCrimeRateBack = false;
        enableRealStateRentBack = false;
        enableRealStatePricesBack = false;
        enableRealStateBuyLandBack = false;
        enableSchoolsBack = false;
        enableTrainSationsBack = false;
        resetPreferences();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("comparePostCodes", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("suggestedPostCodes",  null);
    }

    /**
     * This section performs all the survey actions-----------------------------
     *
     * @Survey
     */
    /**
     * @SurveyInstructions
     */
// This method brings the preferences from the database
    public void getPreferences() {
        listOfPreferencesEn = dbPreferencesFacade.findAll();
        listOfPreferencesTr = translatePreferences(listOfPreferencesEn, currentLang);
        listOfPreferencesOne = listOfPreferencesTr;
        listOfPreferencesTwo = new ArrayList<>();
        listOfPreferencesThree = new ArrayList<>();
        activePreference1 = false;
        activePreference2 = true;
        activePreference3 = true;
        errorPreference1 = false;
        errorPreference2 = false;
        errorPreference3 = false;
    }

    // This method brings the preferences from the database after the page is translated
    public void getPreferencesAfterPageTranslation(String lang) {
        listOfPreferencesEn = dbPreferencesFacade.findAll();
        listOfPreferencesTr = translatePreferences(listOfPreferencesEn, lang);
        listOfPreferencesOne = listOfPreferencesTr;
        listOfPreferencesTwo = new ArrayList<>();
        listOfPreferencesThree = new ArrayList<>();
        activePreference1 = false;
        activePreference2 = true;
        activePreference3 = true;
        errorPreference1 = false;
        errorPreference2 = false;
        errorPreference3 = false;
    }

    // This method translate the preferences in the intial page render
    private List<DbPreferences> translatePreferences(List<DbPreferences> listOfPreferencesEnTmp, String lang) {
        if (lang.equals("EN")) {
            return listOfPreferencesEnTmp;
        } else {
            List<DbPreferences> listOfPreferencesTrTmp = new ArrayList<>();
            for (DbPreferences item : listOfPreferencesEnTmp) {
                Translator translator = new Translator();
                try {
                    listOfPreferencesTrTmp.add(new DbPreferences(item.getPrefId(), translator.translate(lang, item.getPrefName()),
                            item.getPrefNoQuestion()));

                } catch (IOException | GeneralSecurityException ex) {
                    Logger.getLogger(Index_MB.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            return listOfPreferencesTrTmp;
        }
    }

    // This method resets the preferences
    public void resetPreferences() {
        listOfPreferencesOne = listOfPreferencesTr;
        listOfPreferencesTwo = new ArrayList<>();
        listOfPreferencesThree = new ArrayList<>();
        activePreference1 = false;
        activePreference2 = true;
        activePreference3 = true;
        errorPreference1 = false;
        errorPreference2 = false;
        errorPreference3 = false;
        preference1 = null;
        preference2 = null;
        preference3 = null;
    }

    // This method filters the preferences in the intial page render
    public void processPreferences(String prefId) {
        switch (prefId) {
            case "one":
                for (DbPreferences item : listOfPreferencesOne) {
                    if (!item.getPrefName().equals(preference1) && !listOfPreferencesTwo.contains(item)) {
                        listOfPreferencesTwo.add(item);
                    }
                }
                activePreference1 = true;
                activePreference2 = false;
                break;
            case "two":
                for (DbPreferences item : listOfPreferencesTwo) {
                    if (!item.getPrefName().equals(preference2) && !listOfPreferencesThree.contains(item)) {
                        listOfPreferencesThree.add(item);
                    }
                }
                activePreference2 = true;
                activePreference3 = false;
                break;
            default:
                break;
        }
    }

    // Save preferences and start survey
    public void savePreferences() {
        boolean error = false;
        errorPreference1 = false;
        errorPreference2 = false;
        errorPreference3 = false;

        if (preference1 == null) {
            errorPreference1 = true;
            error = true;
        }
        if (preference2 == null) {
            errorPreference2 = true;
            error = true;
        }
        if (preference3 == null) {
            errorPreference3 = true;
            error = true;
        }

        if (!error) {
            surveyController("build", null);
        }
    }

    // Build the survey and process the answers per each question
    public void surveyController(String action, String questionName) {
        switch (action) {
            case "build":
                listOfQuestions = new ArrayList<>();
                listOfQuestions.add(questionStructure(listOfPreferencesEn.get(getPreferenceEN(preference1)).getPrefName()));
                listOfQuestions.add(questionStructure(listOfPreferencesEn.get(getPreferenceEN(preference2)).getPrefName()));
                listOfQuestions.add(questionStructure(listOfPreferencesEn.get(getPreferenceEN(preference3)).getPrefName()));

                for (int i = 0; i < listOfQuestions.size(); i++) {
                    if (i == 0) {
                        listOfQuestions.get(i).setNumberOfQuestion(String.valueOf(i + 1));
                        listOfQuestions.get(i).setInitialOrFinal("Initial");
                    } else if (i == listOfQuestions.size() - 1) {
                        listOfQuestions.get(i).setNumberOfQuestion(String.valueOf(i + 1));
                        listOfQuestions.get(i).setInitialOrFinal("Final");
                    } else {
                        listOfQuestions.get(i).setNumberOfQuestion(String.valueOf(i + 1));
                    }
                }

                enableInstructions = false;
                currentQuestion = listOfQuestions.get(0);
                surveyController("nextQuestion", listOfQuestions.get(0).getQuestionName());
                break;
            case "nextQuestion":
                currentQuestion.setQuestionAnswer1(null);
                currentQuestion.setQuestionAnswer2(null);
                switch (questionName) {
                    case "Bus Stops":
                        enableBusStops = true;
                        enableBusStopsBack = false;
                        enableBusStopsRange1 = false;
                        enableBusStopsRange2 = true;
                        listOfBusStop1 = new ArrayList<>();
                        dbbusStopFacade.findStopsPostCode().forEach((Object tmp) -> {
                            listOfBusStop1.add(tmp.toString());
                        });
                        break;
                    case "Crime Rate":
                        enableCrimeRate = true;
                        enableCrimeRateBack = false;
                        enableCrimeRateRange1 = false;
                        enableCrimeRateRange2 = true;
                        listOfCrimeRate1 = new ArrayList<>();
                        dbcrimeRateFacade.findCrimePostCode().forEach((Object tmp) -> {
                            listOfCrimeRate1.add(tmp.toString());
                        });
                        break;
                    case "Hospitals":
                        enableHospitals = true;
                        enableHospitalsBack = false;
                        enableHospitalsRange1 = false;
                        enableHospitalsRange2 = true;
                        listOfHospitals1 = new ArrayList<>();
                        dbHospitalFacade.findHospitalsPostCode().forEach((Object tmp) -> {
                            listOfHospitals1.add(tmp.toString());
                        });
                        break;
                    case "Schools":
                        enableSchools = true;
                        enableSchoolsBack = false;
                        enableSchoolsRange1 = false;
                        enableSchoolsRange2 = true;
                        listOfSchools1 = new ArrayList<>();
                        DbSchoolFacade.findSchoolTypePostCode().forEach((Object tmp) -> {
                            listOfSchools1.add(tmp.toString());
                        });
                        break;
                    case "Rent Prices":
                        enableRealStateRent = true;
                        enableRealStateRentBack = false;
                        enableRealStateRentRange1 = false;
                        enableRealStateRentRange2 = true;
                        listOfRealStateRent1 = new ArrayList<>();
                        List<Object[]> tmpList = dbhouseRentFacade.findHouseRentPostCode();
                        listOfRealStateRent1 = createNormalizedList( Integer.parseInt(((Object) tmpList.get(0)).toString()), 
                                Integer.parseInt(((Object) tmpList.get(tmpList.size() - 1)).toString()), 10, 10);
                        break;
                    case "Real Estate Prices":
                        enableRealStatePrices = true;
                        enableRealStatePricesBack = false;
                        enableRealStatePricesRange1 = false;
                        enableRealStatePricesRange2 = true;
                        listOfRealStatePrices1 = new ArrayList<>();
                        List<Object[]> tmpList1 = dbhouseBuyingFacade.findHouseBuyPostCode();
                        listOfRealStatePrices1 = createNormalizedList( Integer.parseInt(((Object) tmpList1.get(0)).toString()), 
                                Integer.parseInt(((Object) tmpList1.get(tmpList1.size() - 1)).toString()), 10000, 20000);
                        break;
                    case "Land Prices":
                        enableRealStateBuyLand = true;
                        enableRealStateBuyLandBack = false;
                        enableRealStateBuyLandRange1 = false;
                        enableRealStateBuyLandRange2 = true;
                        listOfRealStateBuyLand1 = new ArrayList<>();
                        List<Object[]> tmpList2 = dblandPriceFacade.findBuyLandPostCode();
                        listOfRealStateBuyLand1 = createNormalizedList( Integer.parseInt(((Object) tmpList2.get(0)).toString()), 
                                Integer.parseInt(((Object) tmpList2.get(tmpList2.size() - 1)).toString()), 10000, 20000);
                        break;
                    case "Train Stations":
                        enableTrainSations = true;
                        enableTrainSationsBack = false;
                        enableTrainSationsRange1 = false;
                        enableTrainSationsRange2 = true;
                        listOfTrainSations1 = new ArrayList<>();
                        dbtrainStationFacade.findTrainStationsPostCode().forEach((Object tmp) -> {
                            listOfTrainSations1.add(tmp.toString());
                        });
                        break;
                    default:
                        System.err.println("com.davido.managedBeans.Survey_MB.surveyController()");
                        break;
                }
                break;
            case "process":
                if (surveyValidator(questionName)) {
                    listOfQuestions.forEach(tmp -> {
                        if (tmp.getQuestionName().equals(currentQuestion.getQuestionName())) {
                            tmp.setQuestionAnswer1(currentQuestion.getQuestionAnswer1());
                            tmp.setQuestionAnswer2(currentQuestion.getQuestionAnswer2());
                        }
                    });

                    if (currentQuestion.getInitialOrFinal() != null && currentQuestion.getInitialOrFinal().equals("Final")) {
                        processSurveyResults();
                    } else {
                        currentQuestion = listOfQuestions.get(Integer.parseInt(currentQuestion.getNumberOfQuestion()));
                        surveyController("nextQuestion", currentQuestion.getQuestionName());
                    }

                    switch (questionName) {
                        case "Bus Stops":
                            enableBusStops = false;
                            break;
                        case "Crime Rate":
                            enableCrimeRate = false;
                            break;
                        case "Hospitals":
                            enableHospitals = false;
                            break;
                        case "Schools":
                            enableSchools = false;
                            break;
                        case "Rent Prices":
                            enableRealStateRent = false;
                            break;
                        case "Real Estate Prices":
                            enableRealStatePrices = false;
                            break;
                        case "Land Prices":
                            enableRealStateBuyLand = false;
                            break;
                        case "Train Stations":
                            enableTrainSations = false;
                            break;
                        default:
                            System.err.println("com.davido.managedBeans.Survey_MB.surveyController()");
                            break;
                    }
                }
            default:
                break;
        }
    }

    // This method validates the coorect answers per question
    private boolean surveyValidator(String questionName) {
        switch (questionName) {
            case "Bus Stops":
                if (currentQuestion.getQuestionAnswer1() == null) {
                    enableBusStopsError1 = true;
                    return false;
                }
                if (currentQuestion.getQuestionAnswer2() == null) {
                    enableBusStopsError2 = true;
                    return false;
                }
                return true;
            case "Crime Rate":
                if (currentQuestion.getQuestionAnswer1() == null) {
                    enableCrimeRateError1 = true;
                    return false;
                }
                if (currentQuestion.getQuestionAnswer2() == null) {
                    enableCrimeRateError2 = true;
                    return false;
                }
                return true;
            case "Hospitals":
                if (currentQuestion.getQuestionAnswer1() == null) {
                    enableHospitalsError1 = true;
                    return false;
                }
                if (currentQuestion.getQuestionAnswer2() == null) {
                    enableHospitalsError2 = true;
                    return false;
                }
                return true;
            case "Schools":
                if (currentQuestion.getQuestionAnswer1() == null) {
                    enableSchoolsError1 = true;
                    return false;
                }
                return true;
            case "Rent Prices":
                if (currentQuestion.getQuestionAnswer1() == null) {
                    enableRealStateRentError1 = true;
                    return false;
                }
                if (currentQuestion.getQuestionAnswer2() == null) {
                    enableRealStateRentError2 = true;
                    return false;
                }
                return true;
            case "Real Estate Prices":
                if (currentQuestion.getQuestionAnswer1() == null) {
                    enableRealStatePricesError1 = true;
                    return false;
                }
                if (currentQuestion.getQuestionAnswer2() == null) {
                    enableRealStatePricesError2 = true;
                    return false;
                }
                return true;
            case "Land Prices":
                if (currentQuestion.getQuestionAnswer1() == null) {
                    enableRealStateBuyLandError1 = true;
                    return false;
                }
                if (currentQuestion.getQuestionAnswer2() == null) {
                    enableRealStateBuyLandError2 = true;
                    return false;
                }
                return true;
            case "Train Stations":
                if (currentQuestion.getQuestionAnswer1() == null) {
                    enableTrainSationsError1 = true;
                    return false;
                }
                if (currentQuestion.getQuestionAnswer2() == null) {
                    enableTrainSationsError2 = true;
                    return false;
                }
                return true;
            default:
                System.err.println("com.davido.managedBeans.Survey_MB.surveyValidator()");
                return false;
        }
    }

    // This method filters the lists of objects structure per each question type
    public void processQuestions(String questionName, String action) {
        switch (questionName) {
            case "Bus Stops":
                if (action.equals("BusListOne")) {
                    listOfBusStop2 = new ArrayList<>();
                    listOfBusStop1.forEach(tmp -> {
                        if (Integer.parseInt(tmp) >= Integer.parseInt(currentQuestion.getQuestionAnswer1())) {
                            listOfBusStop2.add(tmp);
                        }
                    });
                    enableBusStopsRange1 = true;
                    enableBusStopsRange2 = false;
                } else if (action.equals("Clean")) {
                    surveyController("nextQuestion", questionName);
                }
                break;
            case "Crime Rate":
                if (action.equals("CrimeListOne")) {
                    listOfCrimeRate2 = new ArrayList<>();
                    listOfCrimeRate1.forEach(tmp -> {
                        if (Double.parseDouble(tmp) >= Double.parseDouble(currentQuestion.getQuestionAnswer1())) {
                            listOfCrimeRate2.add(tmp);
                        }
                    });
                    enableCrimeRateRange1 = true;
                    enableCrimeRateRange2 = false;
                } else if (action.equals("Clean")) {
                    surveyController("nextQuestion", questionName);
                }
                break;
            case "Hospitals":
                if (action.equals("HospitalsListOne")) {
                    listOfHospitals2 = new ArrayList<>();
                    listOfHospitals1.forEach(tmp -> {
                        if (Integer.parseInt(tmp) >= Integer.parseInt(currentQuestion.getQuestionAnswer1())) {
                            listOfHospitals2.add(tmp);
                        }
                    });
                    enableHospitalsRange1 = true;
                    enableHospitalsRange2 = false;
                } else if (action.equals("Clean")) {
                    surveyController("nextQuestion", questionName);
                }
                break;
            case "Schools":
                // Not required
                break;
            case "Rent Prices":
                if (action.equals("RentPricesListOne")) {
                    listOfRealStateRent2 = new ArrayList<>();
                    listOfRealStateRent1.forEach(tmp -> {
                        if (Integer.parseInt(tmp) >= Integer.parseInt(currentQuestion.getQuestionAnswer1())) {
                            listOfRealStateRent2.add(tmp);
                        }
                    });
                    enableRealStateRentRange1 = true;
                    enableRealStateRentRange2 = false;
                } else if (action.equals("Clean")) {
                    surveyController("nextQuestion", questionName);
                }
                break;
            case "Real Estate Prices":
                if (action.equals("BuyHouseListOne")) {
                    listOfRealStatePrices2 = new ArrayList<>();
                    listOfRealStatePrices1.forEach(tmp -> {
                        if (Integer.parseInt(tmp) >= Integer.parseInt(currentQuestion.getQuestionAnswer1())) {
                            listOfRealStatePrices2.add(tmp);
                        }
                    });
                    enableRealStatePricesRange1 = true;
                    enableRealStatePricesRange2 = false;
                } else if (action.equals("Clean")) {
                    surveyController("nextQuestion", questionName);
                }
                break;
            case "Land Prices":
                if (action.equals("BuyLandListOne")) {
                    listOfRealStateBuyLand2 = new ArrayList<>();
                    listOfRealStateBuyLand1.forEach(tmp -> {
                        if (Integer.parseInt(tmp) >= Integer.parseInt(currentQuestion.getQuestionAnswer1())) {
                            listOfRealStateBuyLand2.add(tmp);
                        }
                    });
                    enableRealStateBuyLandRange1 = true;
                    enableRealStateBuyLandRange2 = false;
                } else if (action.equals("Clean")) {
                    surveyController("nextQuestion", questionName);
                }
                break;
            case "Train Stations":
                if (action.equals("TrainStopsListOne")) {
                    listOfTrainSations2 = new ArrayList<>();
                    listOfTrainSations1.forEach(tmp -> {
                        if (Integer.parseInt(tmp) >= Integer.parseInt(currentQuestion.getQuestionAnswer1())) {
                            listOfTrainSations2.add(tmp);
                        }
                    });
                    enableTrainSationsRange1 = true;
                    enableTrainSationsRange2 = false;
                } else if (action.equals("Clean")) {
                    surveyController("nextQuestion", questionName);
                }
                break;
            default:
                System.err.println("com.davido.managedBeans.Survey_MB.processQuestions()");
                break;
        }
    }

    // This method returns the object structure per each question type
    private surveyQuestion questionStructure(String questionName) {
        switch (questionName) {
            case "Bus Stops":
                return new surveyQuestion("Bus Stops", null, "range", null, null, "Yes", "2", "2", null);
            case "Crime Rate":
                return new surveyQuestion("Crime Rate", null, "range", null, null, "Yes", "0.5", "0.5", null);
            case "Hospitals":
                return new surveyQuestion("Hospitals", null, "value", null, null, "Yes", "1", "1", null);
            case "Schools":
                return new surveyQuestion("Schools", null, "fixed", null, null, "No", null, null, null);
            case "Rent Prices":
                return new surveyQuestion("Rent Prices", null, "range", null, null, "Yes", "25", "25", null);
            case "Real Estate Prices":
                return new surveyQuestion("Real Estate Prices", null, "range", null, null, "Yes", "20000", "20000", null);
            case "Land Prices":
                return new surveyQuestion("Land Prices", null, "range", null, null, "Yes", "10000", "10000", null);
            case "Train Stations":
                return new surveyQuestion("Train Stations", null, "range", null, null, "Yes", "1", "1", null);
            default:
                return null;
        }
    }

    // Get the selected preference on English
    private int getPreferenceEN(String preference) {
        for (int i = 0; i < listOfPreferencesTr.size(); i++) {
            if (listOfPreferencesTr.get(i).getPrefName().equals(preference)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * This section process the user answers and perform the
     * results-----------------------------
     *
     * /
     *
     **
     * @ProcessResults
     */
    private void processSurveyResults() {
        boolean firstCycle = true;
        List<surveyQuestion> listForSearching = new ArrayList<>();
        listOfQuestions.forEach(item -> {
            listForSearching.add(item);
        });

        List<resultantItem> resultantList = new ArrayList<>();
        do {
            if (!firstCycle) {
                // calculate work out
                listForSearching.forEach(item -> {
                    surveyQuestion newItem = questionReWork(item);
                    if (newItem != null) {
                        item = newItem;
                    }
                });
                resultantList.removeAll(resultantList);

            }

            // Get results for the first question
            List<Object[]> questionOneResults = getRangeResults(listForSearching.get(0));
            questionOneResults.forEach((Object[] item) -> {
                resultantList.add(new resultantItem(item[0].toString(), item[1].toString(), item[2].toString(),
                        listForSearching.get(0).getQuestionName(), item[3].toString(), null, null, null, null, false));
            });

            // Get results for the second question
            List<Object[]> questionTwoResults = getRangeResults(listForSearching.get(1));
            questionTwoResults.forEach((Object[] question2) -> {
                resultantList.forEach(current -> {
                    if (current.getPostCode().equals(question2[0].toString()) && current.getPostCodeLine().equals(question2[1].toString())) {
                        current.setQuestionTwoName(listForSearching.get(1).getQuestionName());
                        current.setQuestionTwoAnswer(question2[3].toString());
                    }
                });
            });

            // Get results for the third question
            List<Object[]> questionThreeResults = getRangeResults(listForSearching.get(2));
            questionThreeResults.forEach((Object[] question3) -> {
                resultantList.forEach(current -> {
                    if (current.getPostCode().equals(question3[0].toString()) && current.getPostCodeLine().equals(question3[1].toString())) {
                        current.setQuestionThreeName(listForSearching.get(2).getQuestionName());
                        current.setQuestionThreeAnswer(question3[3].toString());
                    }
                });
            });

            // cleaning incomplete results
            List<resultantItem> listForDeleting = new ArrayList<>();
            resultantList.forEach(item -> {
                if (item.getQuestionOneAnswer() == null || item.getQuestionTwoAnswer() == null || item.getQuestionThreeName() == null) {
                    listForDeleting.add(item);
                }
            });
            resultantList.removeAll(listForDeleting);
            firstCycle = false;
        } while (resultantList.size() <= 3);

        List<resultantItem> listForResultPage = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.println(resultantList.get(i).toString());
            listForResultPage.add(resultantList.get(i));
        }

        // Create the list session variable
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("suggestedPostCodes", listForResultPage);
            FacesContext.getCurrentInstance().getExternalContext().redirect("suggestion.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Survey_MB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // This method returns the list of objects from the database
    private List<Object[]> getRangeResults(surveyQuestion searchedItem) {
        switch (searchedItem.getQuestionName()) {
            case "Bus Stops":
                return dbbusStopFacade.findPostCodeByRange(searchedItem.getQuestionAnswer1(), searchedItem.getQuestionAnswer2());
            case "Crime Rate":
                return dbcrimeRateFacade.findPostCodeByRange(searchedItem.getQuestionAnswer1(), searchedItem.getQuestionAnswer2());
            case "Hospitals":
                return dbHospitalFacade.findPostCodeByRange(searchedItem.getQuestionAnswer1(), searchedItem.getQuestionAnswer2());
            case "Schools":
                List<String> result = Arrays.stream(searchedItem.getQuestionAnswer1().split("\\s*,\\s*")).collect(Collectors.toList());
                return DbSchoolFacade.findPostCodeByRange(result);
            case "Rent Prices":
                return dbhouseRentFacade.findPostCodeByRange(searchedItem.getQuestionAnswer1(), searchedItem.getQuestionAnswer2());
            case "Real Estate Prices":
                return dbhouseBuyingFacade.findPostCodeByRange(searchedItem.getQuestionAnswer1(), searchedItem.getQuestionAnswer2());
            case "Land Prices":
                return dblandPriceFacade.findPostCodeByRange(searchedItem.getQuestionAnswer1(), searchedItem.getQuestionAnswer2());
            case "Train Stations":
                return dbtrainStationFacade.findPostCodeByRange(searchedItem.getQuestionAnswer1(), searchedItem.getQuestionAnswer2());
            default:
                return null;
        }
    }

    // This method returns the question after the rework
    private surveyQuestion questionReWork(surveyQuestion item) {
        List<String> listForComparison = new ArrayList<>();
        String action = "";
        switch (item.getQuestionName()) {
            case "Schools":
                action = "text";
                break;
            case "Crime Rate":
                action = "double";
                dbcrimeRateFacade.findCrimePostCode().forEach((Object tmp) -> {
                    listForComparison.add(tmp.toString());
                });
                break;
            case "Bus Stops":
                action = "integer";
                dbbusStopFacade.findStopsPostCode().forEach((Object tmp) -> {
                    listForComparison.add(tmp.toString());
                });
                break;
            case "Hospitals":
                action = "integer";
                dbHospitalFacade.findHospitalsPostCode().forEach((Object tmp) -> {
                    listForComparison.add(tmp.toString());
                });
                break;
            case "Rent Prices":
                action = "integer";
                dbhouseRentFacade.findHouseRentPostCode().forEach((Object tmp) -> {
                    listForComparison.add(tmp.toString());
                });
                break;
            case "Real Estate Prices":
                action = "integer";
                dbhouseBuyingFacade.findHouseBuyPostCode().forEach((Object tmp) -> {
                    listForComparison.add(tmp.toString());
                });
                break;
            case "Land Prices":
                action = "integer";
                dblandPriceFacade.findBuyLandPostCode().forEach((Object tmp) -> {
                    listForComparison.add(tmp.toString());
                });
                break;
            case "Train Stations":
                action = "integer";
                dbtrainStationFacade.findTrainStationsPostCode().forEach((Object tmp) -> {
                    listForComparison.add(tmp.toString());
                });
                break;
            default:
                break;
        }

        switch (action) {
            case "text":
                item.setQuestionAnswer1(schoolReWork(item.getQuestionAnswer1()));
                return item;
            case "double":
                Double newMin = Double.parseDouble(item.getQuestionAnswer1()) - Double.parseDouble(item.getReworkDown());
                Double newMax = Double.parseDouble(item.getQuestionAnswer2()) + Double.parseDouble(item.getReworkUp());
                if (Double.parseDouble(listForComparison.get(0)) > newMin) {
                    newMin = Double.parseDouble(listForComparison.get(0));
                }
                if (Double.parseDouble(listForComparison.get(listForComparison.size() - 1)) < newMax) {
                    newMin = Double.parseDouble(listForComparison.get(listForComparison.size() - 1));
                }
                item.setQuestionAnswer1(Double.toString(newMin));
                item.setQuestionAnswer2(Double.toString(newMax));
                return item;
            case "integer":
                int newMin1 = Integer.parseInt(item.getQuestionAnswer1()) - Integer.parseInt(item.getReworkDown());
                int newMax1 = Integer.parseInt(item.getQuestionAnswer2()) + Integer.parseInt(item.getReworkUp());
                if (Integer.parseInt(listForComparison.get(0)) > newMin1) {
                    newMin1 = Integer.parseInt(listForComparison.get(0));
                }
                if (Integer.parseInt(listForComparison.get(listForComparison.size() - 1)) < newMax1) {
                    newMax1 = Integer.parseInt(listForComparison.get(listForComparison.size() - 1));
                }
                item.setQuestionAnswer1(Integer.toString(newMin1));
                item.setQuestionAnswer2(Integer.toString(newMax1));
                return item;
            default:
                return null;
        }
    }

    // This method increase the number of options per iteration on the school option
    private String schoolReWork(String listOfShools) {
        List<String> currentList = Arrays.stream(listOfShools.split("\\s*,\\s*")).collect(Collectors.toList());
        List<String> databaseList = new ArrayList<>();
        DbSchoolFacade.findSchoolTypePostCode().forEach((Object tmp) -> {
            databaseList.add(tmp.toString());
        });
        if (currentList.size() < databaseList.size()) {
            databaseList.removeAll(currentList);
            currentList.add(databaseList.get(0));
        }
        return String.join(",", currentList);
    }
    
    private List<String> createNormalizedList(int begin, int end, int base, int step){
        List<String> resultantList = new ArrayList<>();
        int secondNumber = (int) Math.ceil((double) (begin / base)) * base;
        int seconLastNumber = (int) Math.floor((double) (end / base)) * base;
        resultantList.add(Integer.toString(begin));
        for (int i = secondNumber; i < seconLastNumber; i += step) {
            resultantList.add(Integer.toString(i));
        }
        resultantList.add(Integer.toString(end));
        return resultantList;
    }

}
