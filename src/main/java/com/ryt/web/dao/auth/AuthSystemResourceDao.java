package com.ryt.web.dao.auth;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.auth.AuthSystemResource;

public interface AuthSystemResourceDao extends BaseDao<AuthSystemResource> {
	List<AuthSystemResource> findUserMenu(@Param("userName") String userName);
}