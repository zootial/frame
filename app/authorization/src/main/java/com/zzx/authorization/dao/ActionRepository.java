package com.zzx.authorization.dao;

import com.zzx.authorization.domain.Action;
import org.springframework.transaction.annotation.Transactional;
import com.zzx.common.dao.CommonDao;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:06
 */
@Transactional(readOnly = true)
public interface ActionRepository extends CommonDao<Action, Long> {

}
