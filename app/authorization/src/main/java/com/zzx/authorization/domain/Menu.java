package com.zzx.authorization.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.zzx.common.dao.domain.PO;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-04-04 05:29:09
 */
@Entity
@Table(name = "menu", schema = "auth")
public class Menu extends PO {

    
    /**
     * 菜单编码
     */
    @Column(name = "code", nullable = false)
    private java.lang.String code;
    
    /**
     * 菜单名称
     */
    @Column(name = "name", nullable = false)
    private java.lang.String name;
    
    /**
     * 应用标识
     */
    @Column(name = "app", nullable = false)
    private java.lang.String app;
    
    /**
     * 父级菜单编码
     */
    @Column(name = "parent_code")
    private java.lang.String parentCode;
    
    /**
     * 链接URI
     */
    @Column(name = "link_uri")
    private java.lang.String linkUri;
    
    /**
     * 图标
     */
    @Column(name = "icon")
    private java.lang.String icon;
    
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private java.util.Date creationDate;
    
    @Column(name = "creator_code")
    private java.lang.String creatorCode;
    
    @Column(name = "creator_name")
    private java.lang.String creatorName;
    
     
    public void setCode (java.lang.String code) {
        this.code = code;
    }
    
    public java.lang.String getCode () {
        return this.code;
    }
    
    public void setName (java.lang.String name) {
        this.name = name;
    }
    
    public java.lang.String getName () {
        return this.name;
    }
    
    public java.lang.String getApp() {
        return app;
    }

    public void setApp(java.lang.String app) {
        this.app = app;
    }

    public void setParentCode (java.lang.String parentCode) {
        this.parentCode = parentCode;
    }
    
    public java.lang.String getParentCode () {
        return this.parentCode;
    }
    
    public void setLinkUri (java.lang.String linkUri) {
        this.linkUri = linkUri;
    }
    
    public java.lang.String getLinkUri () {
        return this.linkUri;
    }
    
    public void setIcon (java.lang.String icon) {
        this.icon = icon;
    }
    
    public java.lang.String getIcon () {
        return this.icon;
    }
    
    public void setCreationDate (java.util.Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public java.util.Date getCreationDate () {
        return this.creationDate;
    }
    
    public void setCreatorCode (java.lang.String creatorCode) {
        this.creatorCode = creatorCode;
    }
    
    public java.lang.String getCreatorCode () {
        return this.creatorCode;
    }
    
    public void setCreatorName (java.lang.String creatorName) {
        this.creatorName = creatorName;
    }
    
    public java.lang.String getCreatorName () {
        return this.creatorName;
    }
    
}
