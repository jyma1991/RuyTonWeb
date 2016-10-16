package com.ryt.web.service.sc;

import java.util.ArrayList;
import java.util.List;

import org.durcframework.core.expression.Expression;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.dao.sc.ScTeacherCourseDao;
import com.ryt.web.entity.sc.ScClassTeacher;
import com.ryt.web.entity.sc.ScCourse;
import com.ryt.web.entity.sc.ScTeacherCourse;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.sys.SysUploadService;
import com.ryt.web.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScTeacherCourseService extends CrudService<ScTeacherCourse, ScTeacherCourseDao> {

	@Autowired 
	UserService userService;
	@Autowired
	ScClassTeacherService scClassTeacherService;
	@Autowired
	ScCourseService scCourseService;
	@Autowired
	SysUploadService sysUploadService;
	
	public void delByCourseId(Integer courseId){
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("courseId", courseId));
		expressions.add(new ValueExpression("isDeleted", RuyTonAppContants.ARTICLE_DELETE_FALSE));
		query.addAll(expressions);
		List<ScTeacherCourse> scTeacherCourse = null;
		try {
			scTeacherCourse = this.getDao().find(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (scTeacherCourse!=null) {
			for (ScTeacherCourse scTeacherCourse2 : scTeacherCourse) {
				 this.getDao().del(scTeacherCourse2);
			}
		}
		
		
	}
	
	

	public List<ScClassTeacher> findScTecherWithCoursesByClassId(int classId){
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("classId", classId));
		expressions.add(new ValueExpression("isDeleted", RuyTonAppContants.ARTICLE_DELETE_FALSE));
		query.addAll(expressions);
		List<ScClassTeacher> scClassTeachers = null;
		List<ScTeacherCourse> scTeacherCourses = null;
		try {
			scClassTeachers= scClassTeacherService.find(query);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findScTecherWithCoursesByClassId ＝ null");
		}
		if (scClassTeachers!=null) {
			for (ScClassTeacher scClassTeacher : scClassTeachers) {
				if (scClassTeacher!=null) {
					User user = userService.get(scClassTeacher.getTeacherId());
					//获取作者头像
					if(user != null){
						if(user.getAvatar() != null && !user.getAvatar().equals(0)){
							user.setArtHead(sysUploadService.get(user.getAvatar()).getFileFullPath());
						}else{
							if(user.getDefaultAvatar() != null && user.getDefaultAvatar().equals("")){
								user.setDefaultAvatar("");
								user.setArtHead("");
							}else{
								user.setArtHead(user.getDefaultAvatar());
							}
						}
					}
					scClassTeacher.setUser(user);
					scTeacherCourses = getTeacherCoursesByTeacherId(scClassTeacher.getTeacherId());
					if (scTeacherCourses!=null) {
						for (ScTeacherCourse scTeacherCourse : scTeacherCourses) {
							ScCourse course = scCourseService.get(scTeacherCourse.getCourseId());
							if(course.getImg() != null && !course.getImg().equals("")){
								Integer courseImgId = Integer.parseInt(course.getImg());
								course.setImg(sysUploadService.get(courseImgId).getFileFullPath());
							}
							scTeacherCourse.setScCourse(course);
						}
						scClassTeacher.setTeacherCourses(scTeacherCourses);
					}
				}
				
			}
		}

		
		return scClassTeachers;
	}
	
	//根据老师Id查询老师所授课程
	public List<ScTeacherCourse> getTeacherCoursesByTeacherId(Integer teacherId){
		List<ScTeacherCourse> scTeacherCourses = null;
		ExpressionQuery courseQuery = ExpressionQuery.buildQueryAll();
		List<Expression> courseExpressions = new ArrayList<Expression>();
		courseExpressions.add(new ValueExpression("teacherId", teacherId));
		courseExpressions.add(new ValueExpression("isDeleted", RuyTonAppContants.ARTICLE_DELETE_FALSE));
		courseQuery.addAll(courseExpressions);
		scTeacherCourses = this.find(courseQuery);
		if (scTeacherCourses!=null) {
			for (ScTeacherCourse scTeacherCourse : scTeacherCourses) {
				ScCourse course = scCourseService.get(scTeacherCourse.getCourseId());
				if(course.getImg() != null && !course.getImg().equals("")){
					Integer courseImgId = Integer.parseInt(course.getImg());
					course.setImg(sysUploadService.get(courseImgId).getFileFullPath());
				}
				scTeacherCourse.setScCourse(course);
			}
		}
		return scTeacherCourses;
	}
	
}