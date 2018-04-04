package com.zzx.authorization.bo;

import org.springframework.stereotype.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzx.common.bo.CommonBo;
import com.zzx.authorization.domain.Menu;
import com.zzx.authorization.dao.MenuRepository;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-04-04 05:29:09
 */
@Service
public class MenuBo extends CommonBo<Menu, Long>  {
    
	@Autowired
    private MenuRepository repo;
    
	@Override
	protected JpaRepository<Menu, Long> getJpaRepository() {
	   return this.repo;
	}
	
}
