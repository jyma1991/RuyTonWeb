package com.ryt.web.service.sc;

import java.util.ArrayList;
import java.util.List;

import org.durcframework.core.expression.Expression;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.dao.sc.ScClassTeacherDao;
import com.ryt.web.entity.sc.ScClassTeacher;
import com.ryt.web.entity.sc.ScTeacher;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScClassTeacherService extends CrudService<ScClassTeacher, ScClassTeacherDao> {

	@Autowired
	UserService userService;
	public List<User> findTechersByClassId(int classId){
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("classId", classId));
		query.addAll(expressions);
		
		List<ScClassTeacher> listTeacher= this.getDao().find(query);
		List<User> listUser = new ArrayList();
		for (ScClassTeacher scTeacher : listTeacher) {
			if(userService.get(scTeacher.getTeacherId())!=null)
			{
				listUser.add(userService.get(scTeacher.getTeacherId()));
			}
			else
			{
				System.out.println("teacher user is null"+" TeacherId:"+scTeacher.getTeacherId());
			}
			
		}
		return listUser;
	}
	
	public List<User> findTechersBySchoolId(int schoolId ){
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("schoolId", schoolId));
		query.addAll(expressions);
		
		List<ScClassTeacher> listTeacher= this.getDao().find(query);
		List<User> listUser = new ArrayList();
		for (ScClassTeacher scTeacher : listTeacher) {
			if(userService.get(scTeacher.getTeacherId())!=null)
			{
				listUser.add(userService.get(scTeacher.getTeacherId()));
			}
			else
			{
				System.out.println("teacher user is null"+" TeacherId:"+scTeacher.getTeacherId());
			}
			
		}
		return listUser;
	}
	
	public List<ScClassTeacher> findScClassTechersByTeacherId(int teacherId){
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("teacherId", teacherId));
		expressions.add(new ValueExpression("isDeleted", RuyTonAppContants.ARTICLE_DELETE_FALSE));
		query.addAll(expressions);
		List<ScClassTeacher> scClassTeacher= this.getDao().find(query);
		return scClassTeacher;
	}
	
	
	
}