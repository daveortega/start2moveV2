/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davidortega
 */
@Entity
@Table(name = "db_preferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbPreferences.findAll", query = "SELECT d FROM DbPreferences d")
    , @NamedQuery(name = "DbPreferences.findByPrefId", query = "SELECT d FROM DbPreferences d WHERE d.prefId = :prefId")
    , @NamedQuery(name = "DbPreferences.findByPrefName", query = "SELECT d FROM DbPreferences d WHERE d.prefName = :prefName")
    , @NamedQuery(name = "DbPreferences.findByPrefNoQuestion", query = "SELECT d FROM DbPreferences d WHERE d.prefNoQuestion = :prefNoQuestion")})
public class DbPreferences implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prefId")
    private Integer prefId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "prefName")
    private String prefName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prefNoQuestion")
    private int prefNoQuestion;

    public DbPreferences() {
    }

    public DbPreferences(Integer prefId) {
        this.prefId = prefId;
    }

    public DbPreferences(Integer prefId, String prefName, int prefNoQuestion) {
        this.prefId = prefId;
        this.prefName = prefName;
        this.prefNoQuestion = prefNoQuestion;
    }

    public Integer getPrefId() {
        return prefId;
    }

    public void setPrefId(Integer prefId) {
        this.prefId = prefId;
    }

    public String getPrefName() {
        return prefName;
    }

    public void setPrefName(String prefName) {
        this.prefName = prefName;
    }

    public int getPrefNoQuestion() {
        return prefNoQuestion;
    }

    public void setPrefNoQuestion(int prefNoQuestion) {
        this.prefNoQuestion = prefNoQuestion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prefId != null ? prefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbPreferences)) {
            return false;
        }
        DbPreferences other = (DbPreferences) object;
        if ((this.prefId == null && other.prefId != null) || (this.prefId != null && !this.prefId.equals(other.prefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbPreferences[ prefId=" + prefId + " ]";
    }
    
}
