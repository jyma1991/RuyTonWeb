package com.ryt.web.dao.sc;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sc.ScTeacher;
import com.ryt.web.entity.user.User;

public interface ScTeacherDao extends BaseDao<ScTeacher> {

	List<ScTeacher> findTeacherBytrueName(@Param("trueName") String trueName,@Param("beginDate") String beginDate,@Param("endDate") String endDate);

	List<ScTeacher> findTeacherListByCondition(@Param("agentId") Integer agentId,@Param("schoolId") Integer schoolId,@Param("trueName") String trueName,@Param("beginDate") String beginDate,@Param("endDate") String endDate);

}