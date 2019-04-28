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
@Table(name = "db_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbDistrict.findAll", query = "SELECT d FROM DbDistrict d")
    , @NamedQuery(name = "DbDistrict.findByDistrictId", query = "SELECT d FROM DbDistrict d WHERE d.districtId = :districtId")
    , @NamedQuery(name = "DbDistrict.findByDistrictName", query = "SELECT d FROM DbDistrict d WHERE d.districtName = :districtName")})
public class DbDistrict implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "districtId")
    private Integer districtId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "districtName")
    private String districtName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "districtId")
    private List<DbLocality> dbLocalityList;

    public DbDistrict() {
    }

    public DbDistrict(Integer districtId) {
        this.districtId = districtId;
    }

    public DbDistrict(Integer districtId, String districtName) {
        this.districtId = districtId;
        this.districtName = districtName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @XmlTransient
    public List<DbLocality> getDbLocalityList() {
        return dbLocalityList;
    }

    public void setDbLocalityList(List<DbLocality> dbLocalityList) {
        this.dbLocalityList = dbLocalityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (districtId != null ? districtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbDistrict)) {
            return false;
        }
        DbDistrict other = (DbDistrict) object;
        if ((this.districtId == null && other.districtId != null) || (this.districtId != null && !this.districtId.equals(other.districtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbDistrict[ districtId=" + districtId + " ]";
    }
    
}
