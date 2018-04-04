package com.zzx.common.entity.authority;

import com.zzx.common.entity.Vo;

public class PermissionVo extends Vo {
    /**
     * 
     */
    private static final long serialVersionUID = -4122944946493168729L;
    
    private String resCode;
    private Integer limitSet;
    
    public String getResCode() {
        return resCode;
    }
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    public Integer getLimitSet() {
        return limitSet;
    }
    public void setLimitSet(Integer limitSet) {
        this.limitSet = limitSet;
    }
}
