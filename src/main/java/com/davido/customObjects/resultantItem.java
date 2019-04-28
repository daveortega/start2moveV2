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
public class resultantItem {
    
    private String postCode;
    private String postCodeLine;
    private String postCodeName;
    private String questionOneName;
    private String questionOneAnswer;
    private String questionTwoName;
    private String questionTwoAnswer;
    private String questionThreeName;
    private String questionThreeAnswer;
    private boolean checked;

    public resultantItem() {
    }

    public resultantItem(String postCode, String postCodeLine, String postCodeName, String questionOneName, String questionOneAnswer, String questionTwoName, String questionTwoAnswer, String questionThreeName, String questionThreeAnswer, boolean checked) {
        this.postCode = postCode;
        this.postCodeLine = postCodeLine;
        this.postCodeName = postCodeName;
        this.questionOneName = questionOneName;
        this.questionOneAnswer = questionOneAnswer;
        this.questionTwoName = questionTwoName;
        this.questionTwoAnswer = questionTwoAnswer;
        this.questionThreeName = questionThreeName;
        this.questionThreeAnswer = questionThreeAnswer;
        this.checked = checked;
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

    public String getQuestionOneName() {
        return questionOneName;
    }

    public void setQuestionOneName(String questionOneName) {
        this.questionOneName = questionOneName;
    }

    public String getQuestionOneAnswer() {
        return questionOneAnswer;
    }

    public void setQuestionOneAnswer(String questionOneAnswer) {
        this.questionOneAnswer = questionOneAnswer;
    }

    public String getQuestionTwoName() {
        return questionTwoName;
    }

    public void setQuestionTwoName(String questionTwoName) {
        this.questionTwoName = questionTwoName;
    }

    public String getQuestionTwoAnswer() {
        return questionTwoAnswer;
    }

    public void setQuestionTwoAnswer(String questionTwoAnswer) {
        this.questionTwoAnswer = questionTwoAnswer;
    }

    public String getQuestionThreeName() {
        return questionThreeName;
    }

    public void setQuestionThreeName(String questionThreeName) {
        this.questionThreeName = questionThreeName;
    }

    public String getQuestionThreeAnswer() {
        return questionThreeAnswer;
    }

    public void setQuestionThreeAnswer(String questionThreeAnswer) {
        this.questionThreeAnswer = questionThreeAnswer;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "resultantItem{" + "postCode=" + postCode + ", postCodeLine=" + postCodeLine + ", postCodeName=" + postCodeName + ", questionOneName=" + questionOneName + ", questionOneAnswer=" + questionOneAnswer + ", questionTwoName=" + questionTwoName + ", questionTwoAnswer=" + questionTwoAnswer + ", questionThreeName=" + questionThreeName + ", questionThreeAnswer=" + questionThreeAnswer + ", checked=" + checked + '}';
    }
    
}
