package com.zzx.authorization.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.zzx.common.dao.domain.PO;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:06
 */
@Entity
@Table(name = "resource", schema = "auth")
public class Resource extends PO {

    
    /**
     * 资源编码
     */
    @Column(name = "code", nullable = false)
    private java.lang.String code;
    
    /**
     * 资源名称
     */
    @Column(name = "name", nullable = false)
    private java.lang.String name;
    
    /**
     * 应用标识
     */
    @Column(name = "app", nullable = false)
    private java.lang.String app;
    
    /**
     * 父级资源编码
     */
    @Column(name = "parent_code")
    private java.lang.String parentCode;
    
    /**
     * 资源索引,可为URI
     */
    @Column(name = "res_index", nullable = false)
    private java.lang.String resIndex;
    
    /**
     * 对象编码
     */
    @Column(name = "obj_code")
    private java.lang.String objCode;
    
    /**
     * 对象类型[ENTITY,ELEMENT,FUNCTION,MENU,FILE]
     */
    @Column(name = "obj_type", nullable = false)
    private java.lang.String objType;
    
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private java.util.Date creationDate;
    
    /**
     * 创建人编码
     */
    @Column(name = "creator_code")
    private java.lang.String creatorCode;
    
    /**
     * 创建人
     */
    @Column(name = "creator_name")
    private java.lang.String creatorName;
    
    /**
     * 修改人
     */
    @Column(name = "reviser_name")
    private java.lang.String reviserName;
    
     
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
    
    public void setResIndex (java.lang.String resIndex) {
        this.resIndex = resIndex;
    }
    
    public java.lang.String getResIndex () {
        return this.resIndex;
    }
    
    public void setObjCode (java.lang.String objCode) {
        this.objCode = objCode;
    }
    
    public java.lang.String getObjCode () {
        return this.objCode;
    }
    
    public void setObjType (java.lang.String objType) {
        this.objType = objType;
    }
    
    public java.lang.String getObjType () {
        return this.objType;
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
    
    public void setReviserName (java.lang.String reviserName) {
        this.reviserName = reviserName;
    }
    
    public java.lang.String getReviserName () {
        return this.reviserName;
    }
    
}
