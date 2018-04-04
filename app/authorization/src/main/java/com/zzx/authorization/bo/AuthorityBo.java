package com.zzx.authorization.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.zzx.authorization.dao.PermissionRepository;
import com.zzx.authorization.dao.UserRepository;
import com.zzx.authorization.domain.User;
import com.zzx.common.bo.BaseBo;
import com.zzx.common.entity.authority.AuthorityVo;
import com.zzx.common.entity.authority.PermissionVo;

public class AuthorityBo extends BaseBo {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PermissionRepository permissionRepo;

    public AuthorityVo loadUserAuthority(String account, String app) {
        User user = new User();
        user.setAccount(account);
        user = userRepo.findOne(Example.of(user));
        
        AuthorityVo auth = new AuthorityVo();
        auth.setAccount(account);
        auth.setUserCode(user.getCode());
        
        List<PermissionVo> tpermissions = permissionRepo.queryTeamPermission(user.getCode(), app);
        List<PermissionVo> upermissions = permissionRepo.queryUserPermission(user.getCode(), app);
        for(PermissionVo p : tpermissions) {
            auth.putPermission(p.getResCode(), p.getLimitSet());
        }
        for(PermissionVo p : upermissions) {
            auth.addPermission(p.getResCode(), p.getLimitSet());
        }
        
        //TODO PermissionUtil
        
        return auth;
    }
    
    public void loadResMap() {
        
    }
}
