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
@Table(name = "db_houseRent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbhouseRent.findAll", query = "SELECT d FROM DbhouseRent d")
    , @NamedQuery(name = "DbhouseRent.findByHouseRentId", query = "SELECT d FROM DbhouseRent d WHERE d.houseRentId = :houseRentId")
    , @NamedQuery(name = "DbhouseRent.findByYear", query = "SELECT d FROM DbhouseRent d WHERE d.year = :year")
    , @NamedQuery(name = "DbhouseRent.findByHouseRentPrice", query = "SELECT d FROM DbhouseRent d WHERE d.houseRentPrice = :houseRentPrice")})
public class DbhouseRent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "houseRentId")
    private Integer houseRentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "houseRentPrice")
    private int houseRentPrice;
    @JoinColumns({
        @JoinColumn(name = "postCode", referencedColumnName = "postCodeId")
        , @JoinColumn(name = "postLine", referencedColumnName = "postCodeLine")})
    @ManyToOne(optional = false)
    private DbpostCode dbpostCode;

    public DbhouseRent() {
    }

    public DbhouseRent(Integer houseRentId) {
        this.houseRentId = houseRentId;
    }

    public DbhouseRent(Integer houseRentId, int year, int houseRentPrice) {
        this.houseRentId = houseRentId;
        this.year = year;
        this.houseRentPrice = houseRentPrice;
    }

    public Integer getHouseRentId() {
        return houseRentId;
    }

    public void setHouseRentId(Integer houseRentId) {
        this.houseRentId = houseRentId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHouseRentPrice() {
        return houseRentPrice;
    }

    public void setHouseRentPrice(int houseRentPrice) {
        this.houseRentPrice = houseRentPrice;
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
        hash += (houseRentId != null ? houseRentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbhouseRent)) {
            return false;
        }
        DbhouseRent other = (DbhouseRent) object;
        if ((this.houseRentId == null && other.houseRentId != null) || (this.houseRentId != null && !this.houseRentId.equals(other.houseRentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbhouseRent[ houseRentId=" + houseRentId + " ]";
    }
    
}
