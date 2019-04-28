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
@Table(name = "db_landPrice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DblandPrice.findAll", query = "SELECT d FROM DblandPrice d")
    , @NamedQuery(name = "DblandPrice.findByLandPriceId", query = "SELECT d FROM DblandPrice d WHERE d.landPriceId = :landPriceId")
    , @NamedQuery(name = "DblandPrice.findByYear", query = "SELECT d FROM DblandPrice d WHERE d.year = :year")
    , @NamedQuery(name = "DblandPrice.findByPrice", query = "SELECT d FROM DblandPrice d WHERE d.price = :price")})
public class DblandPrice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "landPriceId")
    private Integer landPriceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @JoinColumns({
        @JoinColumn(name = "postCode", referencedColumnName = "postCodeId")
        , @JoinColumn(name = "postLine", referencedColumnName = "postCodeLine")})
    @ManyToOne(optional = false)
    private DbpostCode dbpostCode;

    public DblandPrice() {
    }

    public DblandPrice(Integer landPriceId) {
        this.landPriceId = landPriceId;
    }

    public DblandPrice(Integer landPriceId, int year, int price) {
        this.landPriceId = landPriceId;
        this.year = year;
        this.price = price;
    }

    public Integer getLandPriceId() {
        return landPriceId;
    }

    public void setLandPriceId(Integer landPriceId) {
        this.landPriceId = landPriceId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        hash += (landPriceId != null ? landPriceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DblandPrice)) {
            return false;
        }
        DblandPrice other = (DblandPrice) object;
        if ((this.landPriceId == null && other.landPriceId != null) || (this.landPriceId != null && !this.landPriceId.equals(other.landPriceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DblandPrice[ landPriceId=" + landPriceId + " ]";
    }
    
}
