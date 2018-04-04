package com.zzx.authorization.bo;

import org.springframework.stereotype.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzx.common.bo.CommonBo;
import com.zzx.authorization.domain.Team;
import com.zzx.authorization.dao.TeamRepository;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:06
 */
@Service
public class TeamBo extends CommonBo<Team, Long>  {
    
	@Autowired
    private TeamRepository repo;
    
	@Override
	protected JpaRepository<Team, Long> getJpaRepository() {
	   return this.repo;
	}
	
}
