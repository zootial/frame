package com.zzx.common.entity.authority;

import java.util.List;

import com.zzx.common.entity.Vo;

public class MenuItemVo extends Vo {
    /**
     * 
     */
    private static final long serialVersionUID = -235378620847251232L;
    private String resCode;
    private String menuCode;
    private String name;
    private String uri;
    private String icon;
    private Integer level;
    private List<MenuItemVo> items;
    
    public String getMenuCode() {
        return menuCode;
    }
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    public String getResCode() {
        return resCode;
    }
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    public List<MenuItemVo> getItems() {
        return items;
    }
    public void setItems(List<MenuItemVo> items) {
        this.items = items;
    }
    public Boolean extendable() {
        return items != null && items.size() > 0;
    }
    
}
