package com.zzx.authorization.bo;

import org.springframework.stereotype.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzx.common.bo.CommonBo;
import com.zzx.authorization.domain.Resource;
import com.zzx.authorization.dao.ResourceRepository;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-02-01 12:14:31
 */
@Service
public class ResourceBo extends CommonBo<Resource, Long>  {
    
	@Autowired
    private ResourceRepository repo;
    
	@Override
	protected JpaRepository<Resource, Long> getJpaRepository() {
	   return this.repo;
	}
	
}
