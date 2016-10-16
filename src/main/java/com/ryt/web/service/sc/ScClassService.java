package com.ryt.web.service.sc;

import java.util.HashMap;
import java.util.List;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.dao.sc.ScClassDao;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScClassSch;
import com.ryt.web.entity.sc.ScClassTeacher;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentSch;
import com.ryt.web.entity.user.User;

@Service
public class ScClassService extends CrudService<ScClass, ScClassDao> {
	@Autowired
	ScStudentService scStudentService;
	@Autowired
	ScClassTeacherService scClassTeacherService;

	public void updateStudentTeacherId(ScClass entity) {
		if (entity.getManagerId() == null || entity.getManagerId() == 0) {
			return;
		}
		scStudentService.updateStudentTeacherIdbyClassId(entity.getId(), entity.getManagerId());
	}

	public List<ScClass> getClassListBySchoolId(int schoolId,int placeTypeId) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("schoolId", schoolId));
		query.add(new ValueExpression("placeTypeId", placeTypeId));
		query.setQueryAll(true);
		List<ScClass> cList = this.find(query);
		return cList;
	}

	/**
	 * 获取家长的家长默认的班级及公共场地
	 * @param scClass.schoolId
	 * @param scClass.classId
	 * @return
	 */
	public HashMap<Integer, ScClass> getCommonStreams(ScClass scClass) {
		HashMap<Integer, ScClass> mapScClass = new HashMap<Integer, ScClass>();
		for (ScClass c : this.getDao().getCommonStreams(scClass)) {
			mapScClass.put(c.getId(), c);
		}
		return mapScClass;
	}
	
	public HashMap<Integer, ScClass> getTeacherStreams(Integer teacherId,Integer schoolId) {
		HashMap<Integer, ScClass> mapScClass = new HashMap<Integer, ScClass>();
		
		//老师所在班级
		List<ScClassTeacher> listScClassTeacher =scClassTeacherService.findScClassTechersByTeacherId(teacherId);
		for (ScClassTeacher scClassTeacher : listScClassTeacher) {
			ScClass scClass = this.get(scClassTeacher.getClassId());
			mapScClass.put(scClass.getId(), scClass);
		}
		//公共场地
		ScClassSch scClassSch = new ScClassSch();
		scClassSch.setSchoolIdSch(schoolId);
		scClassSch.setPlaceTypeIdSch(70);
		for (ScClass c : this.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(scClassSch))) {
			mapScClass.put(c.getId(), c);
		}
		return mapScClass;	
	}
	
	/**
	 * 获取园的场地
	 * @param scClass.schoolId
	 * @return
	 */
	public HashMap<Integer, ScClass> getSchoolStreams(Integer schoolId) {
		HashMap<Integer, ScClass> mapScClass = new HashMap<Integer, ScClass>();
		ScClassSch scClassSch = new ScClassSch();
		scClassSch.setSchoolIdSch(schoolId);
		for (ScClass c : this.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(scClassSch))) {
			mapScClass.put(c.getId(), c);
		}
		return mapScClass;
	}
	
	/**
	 * 获取某个班级下的联系人数量
	 */
	public Integer getClassContactCount(int classId) {
		return getDao().getClassContactCount(classId);
	}

	/**
	 * 获得班级中学生数量
	 */
	public Integer getScStudentCountByClassId(int classId){
		ScStudentSch scStudentSch = new ScStudentSch();
		scStudentSch.setClassIdSch(classId);
		return scStudentService.findTotalCount(ExpressionQuery.buildQueryAll().addAnnotionExpression(scStudentSch));
	}
	/**
	 * 暂时只根据开始时间或者结束时间来查询
	 * 
	 * @param scClassSch
	 * @return
	 */
	public List<ScClass> getClassList(ScClassSch scClassSch) {
		ExpressionQuery query = new ExpressionQuery();
		if (scClassSch.getVideoStart() != null) {
			query.add(new ValueExpression("videoStart", scClassSch.getVideoStart()));
		}
		if (scClassSch.getVideoStop() != null) {
			query.add(new ValueExpression("videoStop", scClassSch.getVideoStop()));
		}
		query.setQueryAll(true);
		List<ScClass> list = this.find(query);
		return list;
	}

	/**
	 * 查询所有全天开放的视频班级
	 * 
	 * @param str
	 * @return
	 */
	public List<ScClass> getAllDayVedio(String str) {
		return this.getDao().getAllDayVedio(str);
	}
	
	/**
	 * 查询所有需要关闭的视频
	 * @param videoTime
	 * @return
	 */
	public List<ScClass> getSurpassStopVedio(String videoTime){
		return this.getDao().getSurpassStopVedio(videoTime);
	}
	
	/**
	 * 查询在该时间段已经开放视频的班级
	 * @param videoTime
	 * @return
	 */
	public List<ScClass> getBetweenStartAndStopVedio(String videoTime){
		return this.getDao().getBetweenStartAndStopVedio(videoTime);
	}

	public List<ScClass> getAllScClassBySchoolId(User newUser) {
		//select * from scClass where schoolId=453 and placeTypeId = 69 and isDeleted = 0
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("schoolId", newUser.getId()));
		query.add(new ValueExpression("placeTypeId", RuyTonAppContants.CLASS_TYPE_CLASSROOM));
		query.add(new ValueExpression("isDeleted", RuyTonAppContants.ARTICLE_DELETE_FALSE));
		return getDao().find(query);
	}
}