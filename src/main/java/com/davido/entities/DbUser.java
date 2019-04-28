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
@Table(name = "db_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbUser.findAll", query = "SELECT d FROM DbUser d")
    , @NamedQuery(name = "DbUser.findByUserId", query = "SELECT d FROM DbUser d WHERE d.userId = :userId")
    , @NamedQuery(name = "DbUser.findByUserName", query = "SELECT d FROM DbUser d WHERE d.userName = :userName")
    , @NamedQuery(name = "DbUser.findByUserSalt", query = "SELECT d FROM DbUser d WHERE d.userSalt = :userSalt")
    , @NamedQuery(name = "DbUser.findByUserPassword", query = "SELECT d FROM DbUser d WHERE d.userPassword = :userPassword")})
public class DbUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "userId")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "userName")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "userSalt")
    private String userSalt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "userPassword")
    private String userPassword;

    public DbUser() {
    }

    public DbUser(String userId) {
        this.userId = userId;
    }

    public DbUser(String userId, String userName, String userSalt, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userSalt = userSalt;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbUser)) {
            return false;
        }
        DbUser other = (DbUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbUser[ userId=" + userId + " ]";
    }
    
}
