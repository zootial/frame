package com.zzx.authorization.bo;

import org.springframework.stereotype.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzx.common.bo.CommonBo;
import com.zzx.authorization.domain.UserTeam;
import com.zzx.authorization.dao.UserTeamRepository;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:06
 */
@Service
public class UserTeamBo extends CommonBo<UserTeam, Long>  {
    
	@Autowired
    private UserTeamRepository repo;
    
	@Override
	protected JpaRepository<UserTeam, Long> getJpaRepository() {
	   return this.repo;
	}
	
}
