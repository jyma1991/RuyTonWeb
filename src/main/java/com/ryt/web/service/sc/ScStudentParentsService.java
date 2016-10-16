package com.ryt.web.service.sc;

import java.util.ArrayList;
import java.util.List;

import org.durcframework.core.expression.Expression;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.dao.sc.ScStudentParentsDao;
import com.ryt.web.entity.sc.ScSignInOut;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sc.ScStudentParentsSch;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScStudentParentsService extends CrudService<ScStudentParents, ScStudentParentsDao> {

	public List<ScStudentParents> getStudentParentsBySId(Integer studentId) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("studentId", studentId));
		query.add(new ValueExpression("isDeleted", 0));
		return this.find(query);
	};

	public List<ScStudentParents> getStudentParentsByPId(Integer parentId) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("parentId", parentId));
		query.add(new ValueExpression("isDeleted", 0));
		return this.find(query);
	};

	/*
	 * service User,ScStudentParents,userName
	 */
	@Autowired
	UserService User;

	public List<User> findUserListByuserName(ScStudentParents ScStudentParents) {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("userName", ScStudentParents.getUser().getUserName()));
		expressions.add(new ValueExpression("isDeleted", 0));
		query.addAll(expressions);
		return User.getDao().find(query);
	}

	/*
	 * service the class to return ScStudentParents originClass :the request
	 * param class type field
	 */

	public boolean IsHaveCardNo(ScStudentParents originClass) {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("CardNo", originClass.getCardNo()));
		expressions.add(new ValueExpression("isDeleted", 0));
		query.addAll(expressions);
		List<ScStudentParents> aa = this.getDao().find(query);
		if (aa.size() == 0 || (originClass.getCardNo().equals(""))) {
			return false;
		} else {
			return true;
		}

	}

	public List<ScStudentParents> findStudentParentsListByuserName(ScStudentParents ScStudentParents) {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("parentId", ScStudentParents.getParentId()));
		expressions.add(new ValueExpression("isDeleted", 0));
		query.addAll(expressions);
		return this.find(query);
	}

	/**
	 * 查询家长和孩子家的关系
	 * 
	 * @param childId
	 *            孩子的id
	 * @param familyId
	 *            家长的id
	 * @param relativeId
	 *            关系
	 * @return
	 */
	public List<ScStudentParents> getScStudentsParents(Integer childId, Integer familyId, Integer relativeId) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("studentId", childId));
		query.add(new ValueExpression("parentId", familyId));
		if (relativeId != 0) {
			query.add(new ValueExpression("relatedTypeId", relativeId));
		}

		return getDao().find(query);
	}

	public List<ScStudentParents> getByCardNo(ScSignInOut scSignInOut) {
		return getDao().getByCardNo(scSignInOut);
	}

	public List<ScStudentParents> getParentsByCondition(ScStudentParentsSch sch) {
		return this.getDao().getParentsByCondition(sch);
	}

	/**
	 * 根据卡号查询对象
	 * 
	 * @param agentId
	 * @return
	 */
	public List<ScStudentParents> getParentsByAgentId(Integer agentId) {
		return this.getDao().getParentsByAgentId(agentId);
	}

	public boolean findCardExsist(ScStudentParents scStudentParents) {
		ExpressionQuery query = new ExpressionQuery();

		query.add(new ValueExpression("cardNo", scStudentParents.getCardNo()));
		query.add(new ValueExpression("isDeleted", RuyTonAppContants.ARTICLE_DELETE_FALSE));
		List<ScStudentParents> list = this.find(query);
		if (list != null && list.size() > 0) {
			// 如果是当前用户
			ScStudentParents parents = list.get(0);
			if (String.valueOf(parents.getStudentId()).equals(String.valueOf(scStudentParents.getStudentId()))
					&& String.valueOf(parents.getParentId()).equals(String.valueOf(scStudentParents.getParentId()))) {
				return false;
			}
			return true;
		}

		return false;
	}

}