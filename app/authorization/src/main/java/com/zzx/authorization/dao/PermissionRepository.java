package com.zzx.authorization.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zzx.common.entity.authority.PermissionVo;

@Repository
public interface PermissionRepository {
    @Query("select up.resCode, up.limitSet from Resource r, UserPermission up where up.resCode = r.code and r.app = ?1 and up.userCode = ?0")
    List<PermissionVo> queryUserPermission(String userCode, String app);

    @Query("select tp.resCode, tp.limitSet from Resource r, TeamPermission tp, UserTeam ut where tp.resCode = r.code and r.app = ?1 and ut.teamCode = tp.teamCode and ut.userCode = ?0")
    List<PermissionVo> queryTeamPermission(String userCode, String app);
}
