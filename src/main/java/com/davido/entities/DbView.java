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
@Table(name = "db_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbView.findAll", query = "SELECT d FROM DbView d")
    , @NamedQuery(name = "DbView.findByViewId", query = "SELECT d FROM DbView d WHERE d.viewId = :viewId")
    , @NamedQuery(name = "DbView.findByViewPage", query = "SELECT d FROM DbView d WHERE d.viewPage = :viewPage")
    , @NamedQuery(name = "DbView.findByViewPageSection", query = "SELECT d FROM DbView d WHERE d.viewPageSection = :viewPageSection")
    , @NamedQuery(name = "DbView.findByViewFieldName", query = "SELECT d FROM DbView d WHERE d.viewFieldName = :viewFieldName")
    , @NamedQuery(name = "DbView.findByViewFieldContent", query = "SELECT d FROM DbView d WHERE d.viewFieldContent = :viewFieldContent")})
public class DbView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "viewId")
    private Integer viewId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "viewPage")
    private String viewPage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "viewPageSection")
    private String viewPageSection;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "viewFieldName")
    private String viewFieldName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "viewFieldContent")
    private String viewFieldContent;

    public DbView() {
    }

    public DbView(Integer viewId) {
        this.viewId = viewId;
    }

    public DbView(Integer viewId, String viewPage, String viewPageSection, String viewFieldName, String viewFieldContent) {
        this.viewId = viewId;
        this.viewPage = viewPage;
        this.viewPageSection = viewPageSection;
        this.viewFieldName = viewFieldName;
        this.viewFieldContent = viewFieldContent;
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public String getViewPage() {
        return viewPage;
    }

    public void setViewPage(String viewPage) {
        this.viewPage = viewPage;
    }

    public String getViewPageSection() {
        return viewPageSection;
    }

    public void setViewPageSection(String viewPageSection) {
        this.viewPageSection = viewPageSection;
    }

    public String getViewFieldName() {
        return viewFieldName;
    }

    public void setViewFieldName(String viewFieldName) {
        this.viewFieldName = viewFieldName;
    }

    public String getViewFieldContent() {
        return viewFieldContent;
    }

    public void setViewFieldContent(String viewFieldContent) {
        this.viewFieldContent = viewFieldContent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (viewId != null ? viewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbView)) {
            return false;
        }
        DbView other = (DbView) object;
        if ((this.viewId == null && other.viewId != null) || (this.viewId != null && !this.viewId.equals(other.viewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davido.entities.DbView[ viewId=" + viewId + " ]";
    }
    
}
