package com.zzx.authorization.bo;

import org.springframework.stereotype.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzx.common.bo.CommonBo;
import com.zzx.authorization.domain.TeamPermission;
import com.zzx.authorization.dao.TeamPermissionRepository;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-02-01 12:14:31
 */
@Service
public class TeamPermissionBo extends CommonBo<TeamPermission, Long>  {
    
	@Autowired
    private TeamPermissionRepository repo;
    
	@Override
	protected JpaRepository<TeamPermission, Long> getJpaRepository() {
	   return this.repo;
	}
	
}
