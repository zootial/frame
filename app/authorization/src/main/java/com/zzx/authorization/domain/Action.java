package com.zzx.authorization.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.zzx.common.dao.domain.PO;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:05
 */
@Entity
@Table(name = "action", schema = "auth")
public class Action extends PO {

    
    /**
     * 动作码值
     */
    @Column(name = "act_value", nullable = false)
    private java.lang.Integer actValue;
    
    /**
     * 动作名称
     */
    @Column(name = "act_name", nullable = false)
    private java.lang.String actName;
    
    @Column(name = "creator_code")
    private java.lang.String creatorCode;
    
    @Column(name = "creator_name")
    private java.lang.String creatorName;
    
     
    public void setActValue (java.lang.Integer actValue) {
        this.actValue = actValue;
    }
    
    public java.lang.Integer getActValue () {
        return this.actValue;
    }
    
    public void setActName (java.lang.String actName) {
        this.actName = actName;
    }
    
    public java.lang.String getActName () {
        return this.actName;
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
