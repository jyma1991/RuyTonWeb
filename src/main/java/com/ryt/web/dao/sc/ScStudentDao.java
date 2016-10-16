package com.ryt.web.dao.sc;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.user.User;

public interface ScStudentDao extends BaseDao<ScStudent> {
	void updateStudentTeacherId(@Param("classId") Integer classId,@Param("teacherId") Integer teacherId);

	ScStudent getSctudentByUserId(@Param("userId")Integer userId);
	
	List<ScStudent> getStudentsByAgentId(@Param("agentId")Integer agentId,@Param("schoolId") Integer schoolId,@Param("classId") Integer classId,@Param("studentCode") String studentCode,@Param("beginDate") String beginDate,@Param("endDate") String endDate);
}