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
@Table(name = "res_action", schema = "auth")
public class ResAction extends PO {

    
    /**
     * 资源编码
     */
    @Column(name = "res_code", nullable = false)
    private java.lang.String resCode;
    
    /**
     * 动作Key,可为URI
     */
    @Column(name = "act_key")
    private java.lang.String actKey;
    
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
    
    public void setActKey (java.lang.String actKey) {
        this.actKey = actKey;
    }
    
    public java.lang.String getActKey () {
        return this.actKey;
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
