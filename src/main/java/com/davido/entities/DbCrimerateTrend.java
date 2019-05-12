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
@Table(name = "db_crimerate_trend")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbCrimerateTrend.findAll", query = "SELECT d FROM DbCrimerateTrend d")
    , @NamedQuery(name = "DbCrimerateTrend.findByCrimeRateId", query = "SELECT d FROM DbCrimerateTrend d WHERE d.crimeRateId = :crimeRateId")
    , @NamedQuery(name = "DbCrimerateTrend.findByMunicipalityId", query = "SELECT d FROM DbCrimerateTrend d WHERE d.municipalityId = :municipalityId")
    , @NamedQuery(name = "DbCrimerateTrend.findByYear", query = "SELECT d FROM DbCrimerateTrend d WHERE d.year = :year")
    , @NamedQuery(name = "DbCrimerateTrend.findByCrimeRate", query = "SELECT d FROM DbCrimerateTrend d WHERE d.crimeRate = :crimeRate")
    , @NamedQuery(name = "DbCrimerateTrend.findByDataType", query = "SELECT d FROM DbCrimerateTrend d WHERE d.dataType = :dataType")})
public class DbCrimerateTrend implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "crimeRateId")
    private Integer crimeRateId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "municipalityId")
    private int municipalityId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crimeRate")
    private float crimeRate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dataType")
    private String dataType;

    public DbCrimerateTrend() {
    }

    public DbCrimerateTrend(Integer crimeRateId) {
        this.crimeRateId = crimeRateId;
    }

    public DbCrimerateTrend(Integer crimeRateId, int municipalityId, int year, float crimeRate, String dataType) {
        this.crimeRateId = crimeRateId;
        this.municipalityId = municipalityId;
        this.year = year;
        this.crimeRate = crimeRate;
        this.dataType = dataType;
    }

    public Integer getCrimeRateId() {
        return crimeRateId;
    }

    public void setCrimeRateId(Integer crimeRateId) {
        this.crimeRateId = crimeRateId;
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getCrimeRate() {
        return crimeRate;
    }

    public void setCrimeRate(float crimeRate) {
        this.crimeRate = crimeRate;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crimeRateId != null ? crimeRateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbCrimerateTrend)) {
            return false;
        }
        DbCrimerateTrend other = (DbCrimerateTrend) object;
        if ((this.crimeRateId == null && other.crimeRateId != null) || (this.crimeRateId != null && !this.crimeRateId.equals(other.crimeRateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbCrimerateTrend[ crimeRateId=" + crimeRateId + " ]";
    }
    
}
