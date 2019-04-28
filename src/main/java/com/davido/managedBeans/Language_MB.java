/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.managedBeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author davidortega
 */
@Named(value = "language_MB")
@SessionScoped
public class Language_MB implements Serializable {

    private String language;

    /**
     * Creates a new instance of language_MB
     */
    @PostConstruct
    private void init() {
        language = "EN";
    }

    public Language_MB() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void update() {
    }

}
