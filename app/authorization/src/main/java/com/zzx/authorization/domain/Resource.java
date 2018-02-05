package com.zzx.authorization.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.zzx.common.dao.domain.PO;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-02-01 12:14:31
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
     * 父级资源编码
     */
    @Column(name = "parent_code")
    private java.lang.String parentCode;
    
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
    
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private java.util.Date creationDate;
    
     
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
    
    public void setParentCode (java.lang.String parentCode) {
        this.parentCode = parentCode;
    }
    
    public java.lang.String getParentCode () {
        return this.parentCode;
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
    
    public void setCreationDate (java.util.Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public java.util.Date getCreationDate () {
        return this.creationDate;
    }
    
}
