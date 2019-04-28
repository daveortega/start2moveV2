/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.customObjects;

import java.util.Objects;

/**
 *
 * @author davidortega
 */
public class surveyQuestion {
    
    private String questionName;
    private String numberOfQuestion;
    private String questionAnswerType;
    private String questionAnswer1;
    private String questionAnswer2;
    private String reworkRequired;
    private String reworkDown;
    private String reworkUp;
    private String initialOrFinal;

    public surveyQuestion() {
    }

    public surveyQuestion(String questionName, String numberOfQuestion, String questionAnswerType, String questionAnswer1, String questionAnswer2, String reworkRequired, String reworkDown, String reworkUp, String initialOrFinal) {
        this.questionName = questionName;
        this.numberOfQuestion = numberOfQuestion;
        this.questionAnswerType = questionAnswerType;
        this.questionAnswer1 = questionAnswer1;
        this.questionAnswer2 = questionAnswer2;
        this.reworkRequired = reworkRequired;
        this.reworkDown = reworkDown;
        this.reworkUp = reworkUp;
        this.initialOrFinal = initialOrFinal;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(String numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public String getQuestionAnswerType() {
        return questionAnswerType;
    }

    public void setQuestionAnswerType(String questionAnswerType) {
        this.questionAnswerType = questionAnswerType;
    }

    public String getQuestionAnswer1() {
        return questionAnswer1;
    }

    public void setQuestionAnswer1(String questionAnswer1) {
        this.questionAnswer1 = questionAnswer1;
    }

    public String getQuestionAnswer2() {
        return questionAnswer2;
    }

    public void setQuestionAnswer2(String questionAnswer2) {
        this.questionAnswer2 = questionAnswer2;
    }

    public String getReworkRequired() {
        return reworkRequired;
    }

    public void setReworkRequired(String reworkRequired) {
        this.reworkRequired = reworkRequired;
    }

    public String getReworkDown() {
        return reworkDown;
    }

    public void setReworkDown(String reworkDown) {
        this.reworkDown = reworkDown;
    }

    public String getReworkUp() {
        return reworkUp;
    }

    public void setReworkUp(String reworkUp) {
        this.reworkUp = reworkUp;
    }

    public String getInitialOrFinal() {
        return initialOrFinal;
    }

    public void setInitialOrFinal(String initialOrFinal) {
        this.initialOrFinal = initialOrFinal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final surveyQuestion other = (surveyQuestion) obj;
        if (!Objects.equals(this.questionName, other.questionName)) {
            return false;
        }
        if (!Objects.equals(this.numberOfQuestion, other.numberOfQuestion)) {
            return false;
        }
        if (!Objects.equals(this.questionAnswerType, other.questionAnswerType)) {
            return false;
        }
        if (!Objects.equals(this.questionAnswer1, other.questionAnswer1)) {
            return false;
        }
        if (!Objects.equals(this.questionAnswer2, other.questionAnswer2)) {
            return false;
        }
        if (!Objects.equals(this.reworkRequired, other.reworkRequired)) {
            return false;
        }
        if (!Objects.equals(this.reworkDown, other.reworkDown)) {
            return false;
        }
        if (!Objects.equals(this.reworkUp, other.reworkUp)) {
            return false;
        }
        if (!Objects.equals(this.initialOrFinal, other.initialOrFinal)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "surveyQuestion{" + "questionName=" + questionName + ", numberOfQuestion=" + numberOfQuestion + ", questionAnswerType=" + questionAnswerType + ", questionAnswer1=" + questionAnswer1 + ", questionAnswer2=" + questionAnswer2 + ", reworkRequired=" + reworkRequired + ", reworkDown=" + reworkDown + ", reworkUp=" + reworkUp + ", initialOrFinal=" + initialOrFinal + '}';
    }
     
}
