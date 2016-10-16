package com.ryt.web.dao.auth;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.auth.AuthRole;

public interface AuthRoleDao extends BaseDao<AuthRole> {
    List<AuthRole> findRoleByFunction(@Param("sysFuncId") int sysFuncId);
    
    void delByRoleId(@Param("roleId") int roleId);
}