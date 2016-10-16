package com.ryt.web.dao.sc;

import java.util.List;

import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.sc.ScAuthUserStream;

public interface ScAuthUserStreamDao extends BaseDao<ScAuthUserStream> {
	
	public List<ScAuthUserStream> listAuthTeacherStream(ScAuthUserStream scAuthUserStream);
}