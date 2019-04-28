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
import javax.persistence.JoinColumns;
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
@Table(name = "db_locality")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbLocality.findAll", query = "SELECT d FROM DbLocality d")
    , @NamedQuery(name = "DbLocality.findById", query = "SELECT d FROM DbLocality d WHERE d.id = :id")
    , @NamedQuery(name = "DbLocality.findByPropertyCount", query = "SELECT d FROM DbLocality d WHERE d.propertyCount = :propertyCount")})
public class DbLocality implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "propertyCount")
    private int propertyCount;
    @JoinColumn(name = "districtId", referencedColumnName = "districtId")
    @ManyToOne(optional = false)
    private DbDistrict districtId;
    @JoinColumn(name = "municipalityId", referencedColumnName = "municipalityId")
    @ManyToOne(optional = false)
    private DbMunicipality municipalityId;
    @JoinColumns({
        @JoinColumn(name = "postCodeId", referencedColumnName = "postCodeId")
        , @JoinColumn(name = "postCodeLine", referencedColumnName = "postCodeLine")})
    @ManyToOne(optional = false)
    private DbpostCode dbpostCode;
    @JoinColumn(name = "regionId", referencedColumnName = "regionId")
    @ManyToOne(optional = false)
    private DbRegion regionId;
    @JoinColumn(name = "warId", referencedColumnName = "wardId")
    @ManyToOne(optional = false)
    private DbWard warId;

    public DbLocality() {
    }

    public DbLocality(Integer id) {
        this.id = id;
    }

    public DbLocality(Integer id, int propertyCount) {
        this.id = id;
        this.propertyCount = propertyCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPropertyCount() {
        return propertyCount;
    }

    public void setPropertyCount(int propertyCount) {
        this.propertyCount = propertyCount;
    }

    public DbDistrict getDistrictId() {
        return districtId;
    }

    public void setDistrictId(DbDistrict districtId) {
        this.districtId = districtId;
    }

    public DbMunicipality getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(DbMunicipality municipalityId) {
        this.municipalityId = municipalityId;
    }

    public DbpostCode getDbpostCode() {
        return dbpostCode;
    }

    public void setDbpostCode(DbpostCode dbpostCode) {
        this.dbpostCode = dbpostCode;
    }

    public DbRegion getRegionId() {
        return regionId;
    }

    public void setRegionId(DbRegion regionId) {
        this.regionId = regionId;
    }

    public DbWard getWarId() {
        return warId;
    }

    public void setWarId(DbWard warId) {
        this.warId = warId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbLocality)) {
            return false;
        }
        DbLocality other = (DbLocality) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbLocality[ id=" + id + " ]";
    }
    
}
