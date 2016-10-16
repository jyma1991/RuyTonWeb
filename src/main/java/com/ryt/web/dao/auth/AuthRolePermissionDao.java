package com.ryt.web.dao.auth;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.auth.AuthRolePermission;

public interface AuthRolePermissionDao extends BaseDao<AuthRolePermission> {
    void delBySfId(@Param("sysFuncId") int sysFuncId);
	/**
     *，先清除现有权限
	 * @author Jyma
	 * @since 2015-10-28 11:19:29
	 * @param authRole
	 */
    void delByRoleId(@Param("roleId") int roleId);
}