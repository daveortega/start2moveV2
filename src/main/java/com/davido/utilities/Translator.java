/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.utilities;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 *
 * @author davidortega
 */
public class Translator {

    public Translator() {
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
