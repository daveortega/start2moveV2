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
@Table(name = "db_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbRegion.findAll", query = "SELECT d FROM DbRegion d")
    , @NamedQuery(name = "DbRegion.findByRegionId", query = "SELECT d FROM DbRegion d WHERE d.regionId = :regionId")
    , @NamedQuery(name = "DbRegion.findByRegionName", query = "SELECT d FROM DbRegion d WHERE d.regionName = :regionName")})
public class DbRegion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "regionId")
    private Integer regionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "regionName")
    private String regionName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId")
    private List<DbLocality> dbLocalityList;

    public DbRegion() {
    }

    public DbRegion(Integer regionId) {
        this.regionId = regionId;
    }

    public DbRegion(Integer regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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
        hash += (regionId != null ? regionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbRegion)) {
            return false;
        }
        DbRegion other = (DbRegion) object;
        if ((this.regionId == null && other.regionId != null) || (this.regionId != null && !this.regionId.equals(other.regionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbRegion[ regionId=" + regionId + " ]";
    }
    
}
