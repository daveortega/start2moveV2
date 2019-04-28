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
@Table(name = "db_ward")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbWard.findAll", query = "SELECT d FROM DbWard d")
    , @NamedQuery(name = "DbWard.findByWardId", query = "SELECT d FROM DbWard d WHERE d.wardId = :wardId")
    , @NamedQuery(name = "DbWard.findByWardDescription", query = "SELECT d FROM DbWard d WHERE d.wardDescription = :wardDescription")})
public class DbWard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "wardId")
    private Integer wardId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "wardDescription")
    private String wardDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warId")
    private List<DbLocality> dbLocalityList;

    public DbWard() {
    }

    public DbWard(Integer wardId) {
        this.wardId = wardId;
    }

    public DbWard(Integer wardId, String wardDescription) {
        this.wardId = wardId;
        this.wardDescription = wardDescription;
    }

    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public String getWardDescription() {
        return wardDescription;
    }

    public void setWardDescription(String wardDescription) {
        this.wardDescription = wardDescription;
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
        hash += (wardId != null ? wardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbWard)) {
            return false;
        }
        DbWard other = (DbWard) object;
        if ((this.wardId == null && other.wardId != null) || (this.wardId != null && !this.wardId.equals(other.wardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbWard[ wardId=" + wardId + " ]";
    }
    
}
