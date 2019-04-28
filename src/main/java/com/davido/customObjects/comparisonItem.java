/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.customObjects;

/**
 *
 * @author davidortega
 */
public class comparisonItem {
    private String postCode;
    private String postCodeLine;
    private String postCodeName;
    private String origin;

    public comparisonItem() {
    }

    public comparisonItem(String postCode, String postCodeLine, String postCodeName, String origin) {
        this.postCode = postCode;
        this.postCodeLine = postCodeLine;
        this.postCodeName = postCodeName;
        this.origin = origin;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCodeLine() {
        return postCodeLine;
    }

    public void setPostCodeLine(String postCodeLine) {
        this.postCodeLine = postCodeLine;
    }

    public String getPostCodeName() {
        return postCodeName;
    }

    public void setPostCodeName(String postCodeName) {
        this.postCodeName = postCodeName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "comparisonItem{" + "postCode=" + postCode + ", postCodeLine=" + postCodeLine + ", postCodeName=" + postCodeName + ", origin=" + origin + '}';
    }
    
}
