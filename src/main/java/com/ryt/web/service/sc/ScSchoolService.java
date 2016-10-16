package com.ryt.web.service.sc;

import java.util.List;

import org.durcframework.core.UserContext;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.sc.ScSchoolDao;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.user.User;

import org.springframework.stereotype.Service;

@Service
public class ScSchoolService extends CrudService<ScSchool, ScSchoolDao> {

	/**
	 * 通过登录的用户查询该用户的幼儿园用户信息
	 * 
	 * @return
	 */
	public ScSchool getSchoolUserBySchoolId() {
		int schoolId = UserContext.getInstance().getUser().getId();
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("schoolId", schoolId));
		query.setQueryAll(true);
		List<ScSchool> list = this.find(query);
		ScSchool school = null;
		if (list != null && list.size() > 0) {
			school = list.get(0);
		}
		// System.out.println(JSONObject.fromObject(school).toString());
		return school;
	}

	/**
	 * 通过用户id查找其所在的学校
	 * 
	 * @param userId
	 * @return
	 */
	public ScSchool getSchoolByUserId(Integer userId) {
		return getDao().getSchoolByUserId(userId);
	}

	/**
	 * 根据学校编号查询学校
	 * 
	 * @param schoolId
	 * @return
	 */
	public ScSchool getSchoolBySchoolId(int schoolId) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("schoolId", schoolId));
		List<ScSchool> list = this.find(query);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<ScSchool> chatScSchoolInf(String userName) {
		return this.getDao().chatFindSchoolList(userName);
	}

	
}