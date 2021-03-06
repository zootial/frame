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
@Table(name = "team", schema = "auth")
public class Team extends PO {

    
    /**
     * 组编码
     */
    @Column(name = "code")
    private java.lang.String code;
    
    /**
     * 组名
     */
    @Column(name = "name")
    private java.lang.String name;
    
     
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
    
}
