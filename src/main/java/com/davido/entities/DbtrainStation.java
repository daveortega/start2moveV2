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
@Table(name = "db_trainStation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbtrainStation.findAll", query = "SELECT d FROM DbtrainStation d")
    , @NamedQuery(name = "DbtrainStation.findByTrainStationId", query = "SELECT d FROM DbtrainStation d WHERE d.trainStationId = :trainStationId")
    , @NamedQuery(name = "DbtrainStation.findByStationName", query = "SELECT d FROM DbtrainStation d WHERE d.stationName = :stationName")
    , @NamedQuery(name = "DbtrainStation.findByX", query = "SELECT d FROM DbtrainStation d WHERE d.x = :x")
    , @NamedQuery(name = "DbtrainStation.findByY", query = "SELECT d FROM DbtrainStation d WHERE d.y = :y")})
public class DbtrainStation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trainStationId")
    private Integer trainStationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "stationName")
    private String stationName;
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

    public DbtrainStation() {
    }

    public DbtrainStation(Integer trainStationId) {
        this.trainStationId = trainStationId;
    }

    public DbtrainStation(Integer trainStationId, String stationName, BigDecimal x, BigDecimal y) {
        this.trainStationId = trainStationId;
        this.stationName = stationName;
        this.x = x;
        this.y = y;
    }

    public Integer getTrainStationId() {
        return trainStationId;
    }

    public void setTrainStationId(Integer trainStationId) {
        this.trainStationId = trainStationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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
        hash += (trainStationId != null ? trainStationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbtrainStation)) {
            return false;
        }
        DbtrainStation other = (DbtrainStation) object;
        if ((this.trainStationId == null && other.trainStationId != null) || (this.trainStationId != null && !this.trainStationId.equals(other.trainStationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbtrainStation[ trainStationId=" + trainStationId + " ]";
    }
    
}
