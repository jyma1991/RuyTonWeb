package com.ryt.web.dao.sc;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.sc.ScSchool;

public interface ScSchoolDao extends BaseDao<ScSchool> {
	
	ScSchool getSchoolByUserId(Integer userId);
	 public abstract List<ScSchool> chatFindSchoolList(@Param("userName") String paramString);
}