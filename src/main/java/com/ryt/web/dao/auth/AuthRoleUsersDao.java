package com.ryt.web.dao.auth;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.auth.AuthRoleUsers;

public interface AuthRoleUsersDao extends BaseDao<AuthRoleUsers> {
	 void delAllUserRole(@Param("userName") String userName);
}