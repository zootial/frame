package com.zzx.authorization.bo;

import org.springframework.stereotype.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzx.common.bo.CommonBo;
import com.zzx.authorization.domain.UserPermission;
import com.zzx.authorization.dao.UserPermissionRepository;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:06
 */
@Service
public class UserPermissionBo extends CommonBo<UserPermission, Long>  {
    
	@Autowired
    private UserPermissionRepository repo;
    
	@Override
	protected JpaRepository<UserPermission, Long> getJpaRepository() {
	   return this.repo;
	}
	
}
