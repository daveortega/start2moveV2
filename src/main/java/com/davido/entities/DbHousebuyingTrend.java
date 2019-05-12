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
@Table(name = "db_housebuying_trend")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbHousebuyingTrend.findAll", query = "SELECT d FROM DbHousebuyingTrend d")
    , @NamedQuery(name = "DbHousebuyingTrend.findByHouseBuyingId", query = "SELECT d FROM DbHousebuyingTrend d WHERE d.houseBuyingId = :houseBuyingId")
    , @NamedQuery(name = "DbHousebuyingTrend.findByPostCode", query = "SELECT d FROM DbHousebuyingTrend d WHERE d.postCode = :postCode")
    , @NamedQuery(name = "DbHousebuyingTrend.findByPostLine", query = "SELECT d FROM DbHousebuyingTrend d WHERE d.postLine = :postLine")
    , @NamedQuery(name = "DbHousebuyingTrend.findByYear", query = "SELECT d FROM DbHousebuyingTrend d WHERE d.year = :year")
    , @NamedQuery(name = "DbHousebuyingTrend.findByBuyingPrice", query = "SELECT d FROM DbHousebuyingTrend d WHERE d.buyingPrice = :buyingPrice")
    , @NamedQuery(name = "DbHousebuyingTrend.findByDataType", query = "SELECT d FROM DbHousebuyingTrend d WHERE d.dataType = :dataType")})
public class DbHousebuyingTrend implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "houseBuyingId")
    private Integer houseBuyingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "postCode")
    private int postCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "postLine")
    private int postLine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "buyingPrice")
    private float buyingPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dataType")
    private String dataType;

    public DbHousebuyingTrend() {
    }

    public DbHousebuyingTrend(Integer houseBuyingId) {
        this.houseBuyingId = houseBuyingId;
    }

    public DbHousebuyingTrend(Integer houseBuyingId, int postCode, int postLine, int year, float buyingPrice, String dataType) {
        this.houseBuyingId = houseBuyingId;
        this.postCode = postCode;
        this.postLine = postLine;
        this.year = year;
        this.buyingPrice = buyingPrice;
        this.dataType = dataType;
    }

    public Integer getHouseBuyingId() {
        return houseBuyingId;
    }

    public void setHouseBuyingId(Integer houseBuyingId) {
        this.houseBuyingId = houseBuyingId;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public int getPostLine() {
        return postLine;
    }

    public void setPostLine(int postLine) {
        this.postLine = postLine;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(float buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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
        if (!(object instanceof DbHousebuyingTrend)) {
            return false;
        }
        DbHousebuyingTrend other = (DbHousebuyingTrend) object;
        if ((this.houseBuyingId == null && other.houseBuyingId != null) || (this.houseBuyingId != null && !this.houseBuyingId.equals(other.houseBuyingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbHousebuyingTrend[ houseBuyingId=" + houseBuyingId + " ]";
    }
    
}
