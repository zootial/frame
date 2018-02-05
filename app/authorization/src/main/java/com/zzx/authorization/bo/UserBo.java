package com.zzx.authorization.bo;

import org.springframework.stereotype.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzx.common.bo.CommonBo;
import com.zzx.authorization.domain.User;
import com.zzx.authorization.dao.UserRepository;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-02-01 12:14:31
 */
@Service
public class UserBo extends CommonBo<User, Long>  {
    
	@Autowired
    private UserRepository repo;
    
	@Override
	protected JpaRepository<User, Long> getJpaRepository() {
	   return this.repo;
	}
	
}
