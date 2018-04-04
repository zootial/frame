package com.zzx.authorization.bo;

import org.springframework.stereotype.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzx.common.bo.CommonBo;
import com.zzx.authorization.domain.Action;
import com.zzx.authorization.dao.ActionRepository;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:06
 */
@Service
public class ActionBo extends CommonBo<Action, Long>  {
    
	@Autowired
    private ActionRepository repo;
    
	@Override
	protected JpaRepository<Action, Long> getJpaRepository() {
	   return this.repo;
	}
	
}
