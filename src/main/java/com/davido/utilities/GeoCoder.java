/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author davidortega
 */
public class GeoCoder {


    public GeoCoder() {
    }

    public Double[] GeoCoder(String placeToFind, String APIKEY) {
        try {
            URL url = new URL(
                    "https://maps.googleapis.com/maps/api/geocode/json?address="
                    + placeToFind.replaceAll(" ", "+")
                    + "&key=" + APIKEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output = "", full = "";
            while ((output = br.readLine()) != null) {
                // System.out.println(output);
                full += output;
            }

            //JSON parser object to parse read file
            JSONParser jsonParser = new JSONParser();
            //Read JSON Obj
            JSONObject jsonObject = (JSONObject) jsonParser.parse(full);

            JSONArray result = (JSONArray) jsonObject.get("results");
            JSONObject result1 = (JSONObject) result.get(0);
            JSONObject geometry = (JSONObject) result1.get("geometry");
            JSONObject locat = (JSONObject) geometry.get("location");

            Double[] resultantObj = new Double[2];
            String lat = locat.get("lat").toString();
            String lon = locat.get("lng").toString();
            
            //"iterate onto level of location";
            resultantObj[0] = Double.parseDouble(lat);
            resultantObj[1] = Double.parseDouble(lon);

            conn.disconnect();
            return resultantObj;
        } catch (MalformedURLException e) {
            Logger.getLogger(GeoCoder.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException | ParseException e) {
            Logger.getLogger(GeoCoder.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
