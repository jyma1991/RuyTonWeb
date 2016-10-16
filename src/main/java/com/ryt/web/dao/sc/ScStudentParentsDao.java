package com.ryt.web.dao.sc;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.sc.ScClassSchedule;
import com.ryt.web.entity.sc.ScSignInOut;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sc.ScStudentParentsSch;

public interface ScStudentParentsDao extends BaseDao<ScStudentParents> {
	List<ScStudentParents> getByCardNo(ScSignInOut scSignInOut);
	List<ScStudentParents> getParentsByCondition(ScStudentParentsSch sch);
	List<ScStudentParents> getParentsByAgentId(Integer agentId);
}