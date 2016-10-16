package com.ryt.web.service.sc;

import java.util.ArrayList;
import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.expression.Expression;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.dao.sc.ScCourseDao;
import com.ryt.web.entity.sc.ScClassTeacher;
import com.ryt.web.entity.sc.ScCourse;
import com.ryt.web.entity.sc.ScCourseSch;
import com.ryt.web.entity.sc.ScTeacher;
import com.ryt.web.entity.sc.ScTeacherCourse;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScCourseService extends CrudService<ScCourse, ScCourseDao> {
	/**
	 * 鏍规嵁瀛︽牎缂栧彿鏌ヨ鏁翠釜瀛︽牎鐨勮绋嬩俊鎭�
	 * @param schoolId
	 * @return
	 */
	@Autowired 
	ScTeacherCourseService scTeacherCourseService;
	
	public List<ScCourse> getCoursesbySchoolId(int schoolId){
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("schoolId", schoolId));
		query.setQueryAll(true);
		List<ScCourse> courses = new ArrayList<ScCourse>();
		ScCourse course = new ScCourse();
		course.setId(0);
		course.setCourseName("空");
		courses.add(course);
		List<ScCourse>  list = this.find(query);
		for(int i = 0; i<list.size(); i++){
			courses.add(list.get(i));
		}
		return courses;
	}
	
	public List<ScTeacherCourse> findScTecherCourseByTeacherId(int courseId){
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("courseId", courseId));
		expressions.add(new ValueExpression("isDeleted", RuyTonAppContants.ARTICLE_DELETE_FALSE));
		query.addAll(expressions);
		List<ScTeacherCourse> scTeacherCourse = null;
		try {
			scTeacherCourse= scTeacherCourseService.find(query);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findScTecherCourseByTeacherId ＝ null");
		}
		
		return scTeacherCourse;
	}
	
	
	

	
	
	
	
	
}