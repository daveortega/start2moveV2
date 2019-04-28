/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davidortega
 */
@Entity
@Table(name = "db_hospital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbHospital.findAll", query = "SELECT d FROM DbHospital d")
    , @NamedQuery(name = "DbHospital.findByHospitalId", query = "SELECT d FROM DbHospital d WHERE d.hospitalId = :hospitalId")
    , @NamedQuery(name = "DbHospital.findByLabelName", query = "SELECT d FROM DbHospital d WHERE d.labelName = :labelName")
    , @NamedQuery(name = "DbHospital.findByOpsName", query = "SELECT d FROM DbHospital d WHERE d.opsName = :opsName")
    , @NamedQuery(name = "DbHospital.findByType", query = "SELECT d FROM DbHospital d WHERE d.type = :type")
    , @NamedQuery(name = "DbHospital.findByStreet", query = "SELECT d FROM DbHospital d WHERE d.street = :street")
    , @NamedQuery(name = "DbHospital.findByRoadName", query = "SELECT d FROM DbHospital d WHERE d.roadName = :roadName")
    , @NamedQuery(name = "DbHospital.findByCampusCode", query = "SELECT d FROM DbHospital d WHERE d.campusCode = :campusCode")
    , @NamedQuery(name = "DbHospital.findByVicgovRegi", query = "SELECT d FROM DbHospital d WHERE d.vicgovRegi = :vicgovRegi")
    , @NamedQuery(name = "DbHospital.findByX", query = "SELECT d FROM DbHospital d WHERE d.x = :x")
    , @NamedQuery(name = "DbHospital.findByY", query = "SELECT d FROM DbHospital d WHERE d.y = :y")})
public class DbHospital implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hospitalId")
    private Integer hospitalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "labelName")
    private String labelName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "opsName")
    private String opsName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "street")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "roadName")
    private String roadName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "campusCode")
    private String campusCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "vicgovRegi")
    private String vicgovRegi;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "X")
    private BigDecimal x;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Y")
    private BigDecimal y;
    @JoinColumns({
        @JoinColumn(name = "postCode", referencedColumnName = "postCodeId")
        , @JoinColumn(name = "postLine", referencedColumnName = "postCodeLine")})
    @ManyToOne(optional = false)
    private DbpostCode dbpostCode;

    public DbHospital() {
    }

    public DbHospital(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public DbHospital(Integer hospitalId, String labelName, String opsName, String type, String street, String roadName, String campusCode, String vicgovRegi, BigDecimal x, BigDecimal y) {
        this.hospitalId = hospitalId;
        this.labelName = labelName;
        this.opsName = opsName;
        this.type = type;
        this.street = street;
        this.roadName = roadName;
        this.campusCode = campusCode;
        this.vicgovRegi = vicgovRegi;
        this.x = x;
        this.y = y;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getOpsName() {
        return opsName;
    }

    public void setOpsName(String opsName) {
        this.opsName = opsName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getCampusCode() {
        return campusCode;
    }

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    public String getVicgovRegi() {
        return vicgovRegi;
    }

    public void setVicgovRegi(String vicgovRegi) {
        this.vicgovRegi = vicgovRegi;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public DbpostCode getDbpostCode() {
        return dbpostCode;
    }

    public void setDbpostCode(DbpostCode dbpostCode) {
        this.dbpostCode = dbpostCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hospitalId != null ? hospitalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbHospital)) {
            return false;
        }
        DbHospital other = (DbHospital) object;
        if ((this.hospitalId == null && other.hospitalId != null) || (this.hospitalId != null && !this.hospitalId.equals(other.hospitalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbHospital[ hospitalId=" + hospitalId + " ]";
    }
    
}
