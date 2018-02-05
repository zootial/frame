package com.zzx.authorization.dao;

import com.zzx.authorization.domain.TeamPermission;
import org.springframework.transaction.annotation.Transactional;
import com.zzx.common.dao.CommonDao;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-02-01 12:14:31
 */
@Transactional(readOnly = true)
public interface TeamPermissionRepository extends CommonDao<TeamPermission, Long> {

}
