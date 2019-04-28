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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "db_postCode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbpostCode.findAll", query = "SELECT d FROM DbpostCode d")
    , @NamedQuery(name = "DbpostCode.findByPostCodeId", query = "SELECT d FROM DbpostCode d WHERE d.dbpostCodePK.postCodeId = :postCodeId")
    , @NamedQuery(name = "DbpostCode.findByPostCodeLine", query = "SELECT d FROM DbpostCode d WHERE d.dbpostCodePK.postCodeLine = :postCodeLine")
    , @NamedQuery(name = "DbpostCode.findByPostCodeName", query = "SELECT d FROM DbpostCode d WHERE d.postCodeName = :postCodeName")})
public class DbpostCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DbpostCodePK dbpostCodePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "postCodeName")
    private String postCodeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dbpostCode")
    private List<DbLocality> dbLocalityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dbpostCode")
    private List<DbHospital> dbHospitalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dbpostCode")
    private List<DbSchool> dbSchoolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dbpostCode")
    private List<DblandPrice> dblandPriceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dbpostCode")
    private List<DbtrainStation> dbtrainStationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dbpostCode")
    private List<DbhouseRent> dbhouseRentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dbpostCode")
    private List<DbbusStop> dbbusStopList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dbpostCode")
    private List<DbhouseBuying> dbhouseBuyingList;

    public DbpostCode() {
    }

    public DbpostCode(DbpostCodePK dbpostCodePK) {
        this.dbpostCodePK = dbpostCodePK;
    }

    public DbpostCode(DbpostCodePK dbpostCodePK, String postCodeName) {
        this.dbpostCodePK = dbpostCodePK;
        this.postCodeName = postCodeName;
    }

    public DbpostCode(int postCodeId, int postCodeLine) {
        this.dbpostCodePK = new DbpostCodePK(postCodeId, postCodeLine);
    }

    public DbpostCodePK getDbpostCodePK() {
        return dbpostCodePK;
    }

    public void setDbpostCodePK(DbpostCodePK dbpostCodePK) {
        this.dbpostCodePK = dbpostCodePK;
    }

    public String getPostCodeName() {
        return postCodeName;
    }

    public void setPostCodeName(String postCodeName) {
        this.postCodeName = postCodeName;
    }

    @XmlTransient
    public List<DbLocality> getDbLocalityList() {
        return dbLocalityList;
    }

    public void setDbLocalityList(List<DbLocality> dbLocalityList) {
        this.dbLocalityList = dbLocalityList;
    }

    @XmlTransient
    public List<DbHospital> getDbHospitalList() {
        return dbHospitalList;
    }

    public void setDbHospitalList(List<DbHospital> dbHospitalList) {
        this.dbHospitalList = dbHospitalList;
    }

    @XmlTransient
    public List<DbSchool> getDbSchoolList() {
        return dbSchoolList;
    }

    public void setDbSchoolList(List<DbSchool> dbSchoolList) {
        this.dbSchoolList = dbSchoolList;
    }

    @XmlTransient
    public List<DblandPrice> getDblandPriceList() {
        return dblandPriceList;
    }

    public void setDblandPriceList(List<DblandPrice> dblandPriceList) {
        this.dblandPriceList = dblandPriceList;
    }

    @XmlTransient
    public List<DbtrainStation> getDbtrainStationList() {
        return dbtrainStationList;
    }

    public void setDbtrainStationList(List<DbtrainStation> dbtrainStationList) {
        this.dbtrainStationList = dbtrainStationList;
    }

    @XmlTransient
    public List<DbhouseRent> getDbhouseRentList() {
        return dbhouseRentList;
    }

    public void setDbhouseRentList(List<DbhouseRent> dbhouseRentList) {
        this.dbhouseRentList = dbhouseRentList;
    }

    @XmlTransient
    public List<DbbusStop> getDbbusStopList() {
        return dbbusStopList;
    }

    public void setDbbusStopList(List<DbbusStop> dbbusStopList) {
        this.dbbusStopList = dbbusStopList;
    }

    @XmlTransient
    public List<DbhouseBuying> getDbhouseBuyingList() {
        return dbhouseBuyingList;
    }

    public void setDbhouseBuyingList(List<DbhouseBuying> dbhouseBuyingList) {
        this.dbhouseBuyingList = dbhouseBuyingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dbpostCodePK != null ? dbpostCodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbpostCode)) {
            return false;
        }
        DbpostCode other = (DbpostCode) object;
        if ((this.dbpostCodePK == null && other.dbpostCodePK != null) || (this.dbpostCodePK != null && !this.dbpostCodePK.equals(other.dbpostCodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dbpostCodePK.getPostCodeId() + " (" + postCodeName + ")";
    }
    
}
