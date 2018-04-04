package com.zzx.authorization.dao;

import com.zzx.authorization.domain.Menu;
import org.springframework.transaction.annotation.Transactional;
import com.zzx.common.dao.CommonDao;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-04-04 05:29:09
 */
@Transactional(readOnly = true)
public interface MenuRepository extends CommonDao<Menu, Long> {

}
