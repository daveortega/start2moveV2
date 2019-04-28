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
@Table(name = "db_houseBuying")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbhouseBuying.findAll", query = "SELECT d FROM DbhouseBuying d")
    , @NamedQuery(name = "DbhouseBuying.findByHouseBuyingId", query = "SELECT d FROM DbhouseBuying d WHERE d.houseBuyingId = :houseBuyingId")
    , @NamedQuery(name = "DbhouseBuying.findByYear", query = "SELECT d FROM DbhouseBuying d WHERE d.year = :year")
    , @NamedQuery(name = "DbhouseBuying.findByBuyingPrice", query = "SELECT d FROM DbhouseBuying d WHERE d.buyingPrice = :buyingPrice")})
public class DbhouseBuying implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "houseBuyingId")
    private Integer houseBuyingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "buyingPrice")
    private int buyingPrice;
    @JoinColumns({
        @JoinColumn(name = "postCodeId", referencedColumnName = "postCodeId")
        , @JoinColumn(name = "postCodeLine", referencedColumnName = "postCodeLine")})
    @ManyToOne(optional = false)
    private DbpostCode dbpostCode;

    public DbhouseBuying() {
    }

    public DbhouseBuying(Integer houseBuyingId) {
        this.houseBuyingId = houseBuyingId;
    }

    public DbhouseBuying(Integer houseBuyingId, int year, int buyingPrice) {
        this.houseBuyingId = houseBuyingId;
        this.year = year;
        this.buyingPrice = buyingPrice;
    }

    public Integer getHouseBuyingId() {
        return houseBuyingId;
    }

    public void setHouseBuyingId(Integer houseBuyingId) {
        this.houseBuyingId = houseBuyingId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
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
        hash += (houseBuyingId != null ? houseBuyingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbhouseBuying)) {
            return false;
        }
        DbhouseBuying other = (DbhouseBuying) object;
        if ((this.houseBuyingId == null && other.houseBuyingId != null) || (this.houseBuyingId != null && !this.houseBuyingId.equals(other.houseBuyingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbhouseBuying[ houseBuyingId=" + houseBuyingId + " ]";
    }
    
}
