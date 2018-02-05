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
@Table(name = "user", schema = "auth")
public class User extends PO {

    
    /**
     * 用户编码
     */
    @Column(name = "code", nullable = false)
    private java.lang.String code;
    
    /**
     * 用户名
     */
    @Column(name = "name", nullable = false)
    private java.lang.String name;
    
    /**
     * 状态[01正常,02锁定,03注销]
     */
    @Column(name = "status", nullable = false)
    private java.lang.String status;
    
    /**
     * 账号
     */
    @Column(name = "account", nullable = false)
    private java.lang.String account;
    
    /**
     * 密码
     */
    @Column(name = "password", nullable = false)
    private java.lang.String password;
    
     
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
    
    public void setStatus (java.lang.String status) {
        this.status = status;
    }
    
    public java.lang.String getStatus () {
        return this.status;
    }
    
    public void setAccount (java.lang.String account) {
        this.account = account;
    }
    
    public java.lang.String getAccount () {
        return this.account;
    }
    
    public void setPassword (java.lang.String password) {
        this.password = password;
    }
    
    public java.lang.String getPassword () {
        return this.password;
    }
    
}
