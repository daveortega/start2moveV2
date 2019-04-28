/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author davidortega
 */
@Entity
@Table(name = "db_municipality")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbMunicipality.findAll", query = "SELECT d FROM DbMunicipality d")
    , @NamedQuery(name = "DbMunicipality.findByMunicipalityId", query = "SELECT d FROM DbMunicipality d WHERE d.municipalityId = :municipalityId")
    , @NamedQuery(name = "DbMunicipality.findByMunicipalityName", query = "SELECT d FROM DbMunicipality d WHERE d.municipalityName = :municipalityName")})
public class DbMunicipality implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "municipalityId")
    private Integer municipalityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 450)
    @Column(name = "municipalityName")
    private String municipalityName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipalityId")
    private List<DbLocality> dbLocalityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipalityId")
    private List<DbcrimeRate> dbcrimeRateList;

    public DbMunicipality() {
    }

    public DbMunicipality(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    public DbMunicipality(Integer municipalityId, String municipalityName) {
        this.municipalityId = municipalityId;
        this.municipalityName = municipalityName;
    }

    public Integer getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    @XmlTransient
    public List<DbLocality> getDbLocalityList() {
        return dbLocalityList;
    }

    public void setDbLocalityList(List<DbLocality> dbLocalityList) {
        this.dbLocalityList = dbLocalityList;
    }

    @XmlTransient
    public List<DbcrimeRate> getDbcrimeRateList() {
        return dbcrimeRateList;
    }

    public void setDbcrimeRateList(List<DbcrimeRate> dbcrimeRateList) {
        this.dbcrimeRateList = dbcrimeRateList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (municipalityId != null ? municipalityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbMunicipality)) {
            return false;
        }
        DbMunicipality other = (DbMunicipality) object;
        if ((this.municipalityId == null && other.municipalityId != null) || (this.municipalityId != null && !this.municipalityId.equals(other.municipalityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbMunicipality[ municipalityId=" + municipalityId + " ]";
    }
    
}
