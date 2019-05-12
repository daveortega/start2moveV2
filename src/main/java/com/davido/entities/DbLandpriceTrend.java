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
@Table(name = "db_landprice_trend")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbLandpriceTrend.findAll", query = "SELECT d FROM DbLandpriceTrend d")
    , @NamedQuery(name = "DbLandpriceTrend.findByLandPriceId", query = "SELECT d FROM DbLandpriceTrend d WHERE d.landPriceId = :landPriceId")
    , @NamedQuery(name = "DbLandpriceTrend.findByPostCode", query = "SELECT d FROM DbLandpriceTrend d WHERE d.postCode = :postCode")
    , @NamedQuery(name = "DbLandpriceTrend.findByPostLine", query = "SELECT d FROM DbLandpriceTrend d WHERE d.postLine = :postLine")
    , @NamedQuery(name = "DbLandpriceTrend.findByYear", query = "SELECT d FROM DbLandpriceTrend d WHERE d.year = :year")
    , @NamedQuery(name = "DbLandpriceTrend.findByPrice", query = "SELECT d FROM DbLandpriceTrend d WHERE d.price = :price")
    , @NamedQuery(name = "DbLandpriceTrend.findByDataType", query = "SELECT d FROM DbLandpriceTrend d WHERE d.dataType = :dataType")})
public class DbLandpriceTrend implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "landPriceId")
    private Integer landPriceId;
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
    @Column(name = "price")
    private float price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dataType")
    private String dataType;

    public DbLandpriceTrend() {
    }

    public DbLandpriceTrend(Integer landPriceId) {
        this.landPriceId = landPriceId;
    }

    public DbLandpriceTrend(Integer landPriceId, int postCode, int postLine, int year, float price, String dataType) {
        this.landPriceId = landPriceId;
        this.postCode = postCode;
        this.postLine = postLine;
        this.year = year;
        this.price = price;
        this.dataType = dataType;
    }

    public Integer getLandPriceId() {
        return landPriceId;
    }

    public void setLandPriceId(Integer landPriceId) {
        this.landPriceId = landPriceId;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
        hash += (landPriceId != null ? landPriceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbLandpriceTrend)) {
            return false;
        }
        DbLandpriceTrend other = (DbLandpriceTrend) object;
        if ((this.landPriceId == null && other.landPriceId != null) || (this.landPriceId != null && !this.landPriceId.equals(other.landPriceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbLandpriceTrend[ landPriceId=" + landPriceId + " ]";
    }
    
}
