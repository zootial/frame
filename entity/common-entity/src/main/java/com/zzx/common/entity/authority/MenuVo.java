package com.zzx.common.entity.authority;

import java.util.List;

import com.zzx.common.entity.Vo;

public class MenuVo extends Vo {
    /**
     * 
     */
    private static final long serialVersionUID = 6403764376962579509L;

    private List<MenuItemVo> items;

    public List<MenuItemVo> getItems() {
        return items;
    }

    public void setItems(List<MenuItemVo> items) {
        this.items = items;
    }
}
