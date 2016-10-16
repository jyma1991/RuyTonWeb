package com.ryt.web.service.sc;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.sc.ScStudentDao;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.user.User;

import org.springframework.stereotype.Service;

@Service
public class ScStudentService extends CrudService<ScStudent, ScStudentDao> {
	public void updateStudentTeacherIdbyClassId(Integer classId,Integer teacherId){
		this.getDao().updateStudentTeacherId(classId,teacherId);
	}

	public ScStudent getSctudentByUserId(Integer userId) {
		return getDao().getSctudentByUserId(userId);
	}
	
	public List<ScStudent> getStudentsByAgentId(Integer agentId,Integer schoolId,Integer classId,String schoolCode,String beginDate,String endDate){
		return this.getDao().getStudentsByAgentId(agentId, schoolId, classId, schoolCode, beginDate, endDate);
	}
	
}