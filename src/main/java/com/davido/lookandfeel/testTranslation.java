/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.lookandfeel;

import com.google.api.services.translate.Translate;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author davidortega
 */
@Named(value = "testTranslation")
@RequestScoped
public class testTranslation {

    /**
     * Creates a new instance of testTranslation
     */
    public testTranslation() {
    }
    
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    public String translate(String lang, String text) throws IOException {
//        Translate translate = TranslateOptions.getDefaultInstance().getService();
//        // Translates some text into Russian
//        Translation translation
//                = translate.translate(
//                        text,
//                        TranslateOption.sourceLanguage("en"),
//                        TranslateOption.targetLanguage(lang));
//
//        System.out.printf("Text: %s%n", text);
//        System.out.printf("Translation: %s%n", translation.getTranslatedText());
//        Translate translate = TranslateOptions.getDefaultInstance().getService();
//        Translation translation = translate.translate("¡Hola Mundo!");
//        System.out.printf("Translated Text:\n\t%s\n", translation.getTranslatedText());
//        return translation.getTranslatedText();
//        return null;
//    }

    public void translate1() throws IOException, GeneralSecurityException {
        String[] test = {"Hello World", "How to use Google Translate from Java"};
        Translate t = new Translate.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(), null)
                // Set your application name
                .setApplicationName("Stackoverflow-Example")
                .build();
        Translate.Translations.List list = t.new Translations().list(
                Arrays.asList(
                        // Pass in list of strings to be translated
                        test),
                // Target language
                "ES");

        // TODO: Set your API-Key from https://console.developers.google.com/
        list.setKey("AIzaSyAe4-A0jGMXsIikTdiOxgPrMwNWH_LYxtI");
        TranslationsListResponse response = list.execute();
        for (TranslationsResource translationsResource : response.getTranslations()) {
            System.out.println(translationsResource.getTranslatedText());
        }

    }

    public String translate(String lang, String text) throws IOException, GeneralSecurityException {
        Translate t = new Translate.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(), null)
                // Set your application name
                .setApplicationName("Stackoverflow-Example")
                .build();
        Translate.Translations.List list = t.new Translations().list(
                Arrays.asList(
                        // Pass in list of strings to be translated
                        text),
                // Target language
                lang);

        // TODO: Set your API-Key from https://console.developers.google.com/
        list.setKey("AIzaSyAe4-A0jGMXsIikTdiOxgPrMwNWH_LYxtI");
        TranslationsListResponse response = list.execute();
        for (TranslationsResource translationsResource : response.getTranslations()) {
            return translationsResource.getTranslatedText();
        }
        return null;
    }

}
