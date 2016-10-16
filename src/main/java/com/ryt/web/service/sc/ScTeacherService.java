package com.ryt.web.service.sc;

import java.util.ArrayList;
import java.util.List;

import org.durcframework.core.expression.Expression;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.sc.ScTeacherDao;
import com.ryt.web.entity.sc.ScClassSchedule;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.sc.ScTeacher;
import com.ryt.web.entity.sc.ScTeacherSch;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScTeacherService extends CrudService<ScTeacher, ScTeacherDao> {

	@Autowired
	UserService userService;
	@Autowired
	ScSchoolService scSchool;
	
	public List<User> findTeachersBySchoolId(ScTeacherSch searchEntitySch){
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("schoolId", searchEntitySch.getSchoolIdSch()));
		query.addAll(expressions);
		List<ScTeacher> listTeacher= this.getDao().find(query);
		List<User> listUser = new ArrayList();
		for (ScTeacher scTeacher : listTeacher) {
			if (userService.get(scTeacher.getTeacherId()) != null) {
				listUser.add(userService.get(scTeacher.getTeacherId()));
			}
		}
		return listUser;
	}
	
	public ScTeacher findTechersByTeacherId(int teacherId){
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("teacherId", teacherId));
		query.addAll(expressions);
		List<ScTeacher> listTeacher= this.getDao().find(query);
		
		return listTeacher.get(0);
	}

	
	public void saveScAgentIdBySchoolId (ScTeacher scTeacher ){
		
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("schoolId", scTeacher.getSchoolId()));
		query.addAll(expressions);
		List<ScSchool> listSchool= scSchool.getDao().find(query);
		scTeacher.setAgentId(listSchool.get(0).getAgentId());
	}
	
	/*
	*service User,ScTeacher,userName
	*/
	@Autowired
	UserService User;

	public List<User>  findUserListByUserName(ScTeacher ScTeacher)
	{
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("userName", ScTeacher.getUser().getUserName()));
		expressions.add(new ValueExpression("isDeleted", 0));
		query.addAll(expressions);
		return User.getDao().find(query);
	}

	public List<ScTeacher> findTeacherBytrueName(String trueName,String beginDate,String endDate){
		return this.getDao().findTeacherBytrueName(trueName,beginDate,endDate);
	};
	
	public List<ScTeacher> findTeacherListByCondition(Integer agentId,Integer schoolId,String trueName,String beginDate,String endDate){
		return this.getDao().findTeacherListByCondition(agentId,schoolId,trueName,beginDate,endDate);
	};

	
}