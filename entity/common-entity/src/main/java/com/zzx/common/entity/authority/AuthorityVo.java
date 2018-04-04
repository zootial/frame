package com.zzx.common.entity.authority;

import java.util.HashMap;
import java.util.Map;

import com.zzx.common.entity.Vo;

public class AuthorityVo extends Vo {
    /**
     * 
     */
    private static final long serialVersionUID = -4710617148848309045L;
    
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 账号
     */
    private String account;
    /**
     * 菜单
     */
    private MenuVo menu;

    /**
     * 权限Map
     */
    private final Map<String, Integer> permissionMap = new HashMap<String, Integer>();

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public MenuVo getMenu() {
        return menu;
    }

    public void setMenu(MenuVo menu) {
        this.menu = menu;
    }

    public Map<String, Integer> getPermissionMap() {
        return permissionMap;
    }

    public void putPermission(Map<String, Integer> permissionMap) {
        this.permissionMap.putAll(permissionMap);
    }

    public void putPermission(String resCode, Integer limitSet) {
        this.permissionMap.put(resCode, limitSet);
    }

    public void addPermission(String resCode, Integer limitSet) {
        Integer existLimitSet = this.permissionMap.get(resCode);
        if (existLimitSet != null) {
            limitSet = existLimitSet | limitSet;
        }
        this.putPermission(resCode, limitSet);
    }
}
