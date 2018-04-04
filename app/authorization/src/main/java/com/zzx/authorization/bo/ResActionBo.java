package com.zzx.authorization.bo;

import org.springframework.stereotype.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzx.common.bo.CommonBo;
import com.zzx.authorization.domain.ResAction;
import com.zzx.authorization.dao.ResActionRepository;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:06
 */
@Service
public class ResActionBo extends CommonBo<ResAction, Long>  {
    
	@Autowired
    private ResActionRepository repo;
    
	@Override
	protected JpaRepository<ResAction, Long> getJpaRepository() {
	   return this.repo;
	}
	
}
