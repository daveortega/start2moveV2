/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davido.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author davidortega
 */
@Embeddable
public class DbpostCodePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "postCodeId")
    private int postCodeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "postCodeLine")
    private int postCodeLine;

    public DbpostCodePK() {
    }

    public DbpostCodePK(int postCodeId, int postCodeLine) {
        this.postCodeId = postCodeId;
        this.postCodeLine = postCodeLine;
    }

    public int getPostCodeId() {
        return postCodeId;
    }

    public void setPostCodeId(int postCodeId) {
        this.postCodeId = postCodeId;
    }

    public int getPostCodeLine() {
        return postCodeLine;
    }

    public void setPostCodeLine(int postCodeLine) {
        this.postCodeLine = postCodeLine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) postCodeId;
        hash += (int) postCodeLine;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbpostCodePK)) {
            return false;
        }
        DbpostCodePK other = (DbpostCodePK) object;
        if (this.postCodeId != other.postCodeId) {
            return false;
        }
        if (this.postCodeLine != other.postCodeLine) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbpostCodePK[ postCodeId=" + postCodeId + ", postCodeLine=" + postCodeLine + " ]";
    }
    
}
