package com.zzx.authorization.dao;

import com.zzx.authorization.domain.User;
import org.springframework.transaction.annotation.Transactional;
import com.zzx.common.dao.CommonDao;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:06
 */
@Transactional(readOnly = true)
public interface UserRepository extends CommonDao<User, Long> {

}
