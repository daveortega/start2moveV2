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
@Table(name = "db_school")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbSchool.findAll", query = "SELECT d FROM DbSchool d")
    , @NamedQuery(name = "DbSchool.findBySchoolId", query = "SELECT d FROM DbSchool d WHERE d.schoolId = :schoolId")
    , @NamedQuery(name = "DbSchool.findBySchoolName", query = "SELECT d FROM DbSchool d WHERE d.schoolName = :schoolName")
    , @NamedQuery(name = "DbSchool.findBySchoolType", query = "SELECT d FROM DbSchool d WHERE d.schoolType = :schoolType")
    , @NamedQuery(name = "DbSchool.findByAddress", query = "SELECT d FROM DbSchool d WHERE d.address = :address")
    , @NamedQuery(name = "DbSchool.findByX", query = "SELECT d FROM DbSchool d WHERE d.x = :x")
    , @NamedQuery(name = "DbSchool.findByY", query = "SELECT d FROM DbSchool d WHERE d.y = :y")})
public class DbSchool implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "schoolId")
    private Integer schoolId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "schoolName")
    private String schoolName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "schoolType")
    private String schoolType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Address")
    private String address;
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

    public DbSchool() {
    }

    public DbSchool(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public DbSchool(Integer schoolId, String schoolName, String schoolType, String address, BigDecimal x, BigDecimal y) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolType = schoolType;
        this.address = address;
        this.x = x;
        this.y = y;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        hash += (schoolId != null ? schoolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbSchool)) {
            return false;
        }
        DbSchool other = (DbSchool) object;
        if ((this.schoolId == null && other.schoolId != null) || (this.schoolId != null && !this.schoolId.equals(other.schoolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbSchool[ schoolId=" + schoolId + " ]";
    }
    
}
