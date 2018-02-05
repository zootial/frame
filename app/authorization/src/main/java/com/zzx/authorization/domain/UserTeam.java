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
@Table(name = "user_team", schema = "auth")
public class UserTeam extends PO {

    
    /**
     * 用户编码
     */
    @Column(name = "user_code", nullable = false)
    private java.lang.String userCode;
    
    /**
     * 组编码
     */
    @Column(name = "team_code", nullable = false)
    private java.lang.String teamCode;
    
     
    public void setUserCode (java.lang.String userCode) {
        this.userCode = userCode;
    }
    
    public java.lang.String getUserCode () {
        return this.userCode;
    }
    
    public void setTeamCode (java.lang.String teamCode) {
        this.teamCode = teamCode;
    }
    
    public java.lang.String getTeamCode () {
        return this.teamCode;
    }
    
}
