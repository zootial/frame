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
@Table(name = "user_permission", schema = "auth")
public class UserPermission extends PO {

    
    /**
     * 用户编码
     */
    @Column(name = "user_code", nullable = false)
    private java.lang.String userCode;
    
    /**
     * 资源编码
     */
    @Column(name = "res_code", nullable = false)
    private java.lang.String resCode;
    
    /**
     * 动作权限集
     */
    @Column(name = "limit_set", nullable = false)
    private java.lang.String limitSet;
    
     
    public void setUserCode (java.lang.String userCode) {
        this.userCode = userCode;
    }
    
    public java.lang.String getUserCode () {
        return this.userCode;
    }
    
    public void setResCode (java.lang.String resCode) {
        this.resCode = resCode;
    }
    
    public java.lang.String getResCode () {
        return this.resCode;
    }
    
    public void setLimitSet (java.lang.String limitSet) {
        this.limitSet = limitSet;
    }
    
    public java.lang.String getLimitSet () {
        return this.limitSet;
    }
    
}
