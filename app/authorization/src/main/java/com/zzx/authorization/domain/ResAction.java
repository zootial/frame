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
@Table(name = "res_action", schema = "auth")
public class ResAction extends PO {

    
    /**
     * 资源编码
     */
    @Column(name = "res_code", nullable = false)
    private java.lang.String resCode;
    
    /**
     * 子索引,可为子URI
     */
    @Column(name = "sub_index", nullable = false)
    private java.lang.String subIndex;
    
    /**
     * 动作码值
     */
    @Column(name = "act_value", nullable = false)
    private java.lang.Integer actValue;
    
    @Column(name = "creator_code")
    private java.lang.String creatorCode;
    
    @Column(name = "creator_name")
    private java.lang.String creatorName;
    
     
    public void setResCode (java.lang.String resCode) {
        this.resCode = resCode;
    }
    
    public java.lang.String getResCode () {
        return this.resCode;
    }
    
    public void setSubIndex (java.lang.String subIndex) {
        this.subIndex = subIndex;
    }
    
    public java.lang.String getSubIndex () {
        return this.subIndex;
    }
    
    public void setActValue (java.lang.Integer actValue) {
        this.actValue = actValue;
    }
    
    public java.lang.Integer getActValue () {
        return this.actValue;
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
