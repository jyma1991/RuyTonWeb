package com.ryt.web.service.sc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.service.CrudService;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.dao.sc.ScClassScheduleDao;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScClassCourse;
import com.ryt.web.entity.sc.ScClassSch;
import com.ryt.web.entity.sc.ScClassSchedule;
import com.ryt.web.entity.sc.ScClassTeacher;
import com.ryt.web.entity.sc.ScCourse;
import com.ryt.web.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScClassScheduleService extends CrudService<ScClassSchedule, ScClassScheduleDao> {
	@Autowired
	ScClassService scClassService;
	@Autowired
	UserService userService;
	@Autowired
	ScClassTeacherService scClassTeacherService;
	/**
	 * 根据班级名称模糊查询课程表
	 * @param className
	 * @return
	 */
	public List<ScClassSchedule> findClassScheduleByClassName(String className,String beginDate,String endDate,int schoolId){
		return this.getDao().findClassScheduleByClassName(className,beginDate,endDate,schoolId);
	};
	
	public List<ScClassCourse> getScClassScheduleByWeekDay(Integer userId,Integer classId){
		HashMap<String, Object> classSchedule = new HashMap<String, Object>();
		HashMap<String, Object> classCourse = new HashMap<String, Object>();
		List<ScClassCourse> resultList = new ArrayList<ScClassCourse>();
		List<ScClassSchedule> list =  null;

		if(4==userService.get(userId).getRoleProperty()){
			ScClassSch scClassSch = new ScClassSch();
			scClassSch.setSchoolIdSch(userId);
			scClassSch.setPlaceTypeIdSch(RuyTonAppContants.CLASS_TYPE_CLASSROOM);
			List<ScClass> listScClass = scClassService.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(scClassSch));
			for (ScClass scClass : listScClass) {	
//				if(scClass.getPlaceTypeId() == RuyTonAppContants.CLASS_TYPE_CLASSROOM){
					list = this.getDao().getScClassScheduleByWeekDay(scClass.getId());
					String className =  scClass.getClassName();
					ScClassCourse scClassCourse = new ScClassCourse(className,list);
					resultList.add(scClassCourse);
//				}
			

			}
		}else if(2==userService.get(userId).getRoleProperty()){
			List<ScClassTeacher> listScClassTeacher =scClassTeacherService.findScClassTechersByTeacherId(userId);
			for (ScClassTeacher scClassTeacher : listScClassTeacher) {
				ScClass scClass =  scClassService.get(scClassTeacher.getClassId());
				if(scClass.getPlaceTypeId() == RuyTonAppContants.CLASS_TYPE_CLASSROOM){
					list = this.getDao().getScClassScheduleByWeekDay(scClassTeacher.getClassId());
					String className = scClass.getClassName();
					ScClassCourse scClassCourse = new ScClassCourse(className,list);
					resultList.add(scClassCourse);
				}
			
			}
			
		}else if(1==userService.get(userId).getRoleProperty()){
			list = this.getDao().getScClassScheduleByWeekDay(classId);
			String className =  scClassService.get(classId).getClassName();
			ScClassCourse scClassCourse = new ScClassCourse(className,list);
			resultList.add(scClassCourse);
			
		}
		return resultList ;
	}
}