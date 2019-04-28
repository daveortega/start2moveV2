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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davidortega
 */
@Entity
@Table(name = "db_crimeRate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbcrimeRate.findAll", query = "SELECT d FROM DbcrimeRate d")
    , @NamedQuery(name = "DbcrimeRate.findByCrimeRateId", query = "SELECT d FROM DbcrimeRate d WHERE d.crimeRateId = :crimeRateId")
    , @NamedQuery(name = "DbcrimeRate.findByYear", query = "SELECT d FROM DbcrimeRate d WHERE d.year = :year")
    , @NamedQuery(name = "DbcrimeRate.findByCrimeRate", query = "SELECT d FROM DbcrimeRate d WHERE d.crimeRate = :crimeRate")})
public class DbcrimeRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "crimeRateId")
    private Integer crimeRateId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crimeRate")
    private float crimeRate;
    @JoinColumn(name = "municipalityId", referencedColumnName = "municipalityId")
    @ManyToOne(optional = false)
    private DbMunicipality municipalityId;

    public DbcrimeRate() {
    }

    public DbcrimeRate(Integer crimeRateId) {
        this.crimeRateId = crimeRateId;
    }

    public DbcrimeRate(Integer crimeRateId, int year, float crimeRate) {
        this.crimeRateId = crimeRateId;
        this.year = year;
        this.crimeRate = crimeRate;
    }

    public Integer getCrimeRateId() {
        return crimeRateId;
    }

    public void setCrimeRateId(Integer crimeRateId) {
        this.crimeRateId = crimeRateId;
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

    public DbMunicipality getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(DbMunicipality municipalityId) {
        this.municipalityId = municipalityId;
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
        if (!(object instanceof DbcrimeRate)) {
            return false;
        }
        DbcrimeRate other = (DbcrimeRate) object;
        if ((this.crimeRateId == null && other.crimeRateId != null) || (this.crimeRateId != null && !this.crimeRateId.equals(other.crimeRateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbcrimeRate[ crimeRateId=" + crimeRateId + " ]";
    }
    
}
