package com.ryt.web.dao.auth;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.auth.AuthSystemFunction;

public interface AuthSystemFunctionDao extends BaseDao<AuthSystemFunction> {
    /**
     * 获取用户系统功能 modify by wyp
     * @param username
     * @return
     */
    List<AuthSystemFunction> findUserSysFunction(@Param("userName") String userName);
	
}