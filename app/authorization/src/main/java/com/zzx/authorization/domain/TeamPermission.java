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
@Table(name = "team_permission", schema = "auth")
public class TeamPermission extends PO {

    
    /**
     * 组编码
     */
    @Column(name = "team_code", nullable = false)
    private java.lang.String teamCode;
    
    /**
     * 资源编码
     */
    @Column(name = "res_code", nullable = false)
    private java.lang.String resCode;
    
    /**
     * 动作权限集
     */
    @Column(name = "limit_set", nullable = false)
    private java.lang.Integer limitSet;
    
     
    public void setTeamCode (java.lang.String teamCode) {
        this.teamCode = teamCode;
    }
    
    public java.lang.String getTeamCode () {
        return this.teamCode;
    }
    
    public void setResCode (java.lang.String resCode) {
        this.resCode = resCode;
    }
    
    public java.lang.String getResCode () {
        return this.resCode;
    }
    
    public void setLimitSet (java.lang.Integer limitSet) {
        this.limitSet = limitSet;
    }
    
    public java.lang.Integer getLimitSet () {
        return this.limitSet;
    }
    
}
